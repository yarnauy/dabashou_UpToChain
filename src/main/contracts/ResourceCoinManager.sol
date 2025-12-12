// SPDX-License-Identifier: Apache-2.0
pragma solidity >=0.6.10 <0.8.20;
pragma experimental ABIEncoderV2;

import "./Table.sol";
import "./Cast.sol";
import "./Auth.sol";

contract ResourceCoinManager is Auth {
    Cast constant cast = Cast(address(0x100f));
    TableManager constant tm = TableManager(address(0x1002));
    Table accountTable;
    Table balanceTable;
    string constant ACCOUNT_TABLE = "rc_account";
    string constant BALANCE_TABLE = "rc_balance";
    address public withdrawAccount;

    event RegisterAccount(string userID, address addr);
    event UpdateAccount(string userID, address addr);
    event TransferEvidence(address indexed from, address indexed to, uint256 value, string evidence, string comments);
    event WithdrawEvidence(address indexed from, uint256 value, string evidence);
    event IssueEvidence(address indexed to, uint256 value, string evidence);

    struct IssueItem {
        string orderID;
        address toAddress;
        uint256 amount;
    }
    struct TransferItem {
        string orderID;
        string fromUserID;
        string toUserID;
        address fromAddress;
        address toAddress;
        uint256 amount;
        string comments;
    }
    struct WithdrawItem {
        string orderID;
        string userID;
        address userAddress;
        uint256 amount;
        string receiverAccount;
    }

    constructor(address _withdrawAccount) {
        withdrawAccount = _withdrawAccount;
        // 创建账户表
        string[] memory accountColumns = new string[](1);
        accountColumns[0] = "address";
        TableInfo memory accountTf = TableInfo(KeyOrder.Lexicographic, "userID", accountColumns);
        tm.createTable(ACCOUNT_TABLE, accountTf);
        address t1 = tm.openTable(ACCOUNT_TABLE);
        require(t1 != address(0x0), "accountTable error");
        accountTable = Table(t1);
        // 创建余额表
        string[] memory balanceColumns = new string[](1);
        balanceColumns[0] = "balance";
        TableInfo memory balanceTf = TableInfo(KeyOrder.Lexicographic, "address", balanceColumns);
        tm.createTable(BALANCE_TABLE, balanceTf);
        address t2 = tm.openTable(BALANCE_TABLE);
        require(t2 != address(0x0), "balanceTable error");
        balanceTable = Table(t2);
    }

    // 管理员注册用户ID和地址
    function registerAccount(string memory userID, address addr) public onlyOwner {
        Entry memory entry = Entry(userID, new string[](1));
        entry.fields[0] = cast.addrToString(addr);
        accountTable.insert(entry);
        emit RegisterAccount(userID, addr);
        // 新增：余额表插入
        Entry memory balanceEntry = Entry(cast.addrToString(addr), new string[](1));
        balanceEntry.fields[0] = cast.s256ToString(int256(0));
        balanceTable.insert(balanceEntry);
    }

    // 管理员更新用户ID和地址绑定
    function updateAccount(string memory userID, address addr) public onlyOwner {
        UpdateField[] memory fields = new UpdateField[](1);
        fields[0] = UpdateField("address", cast.addrToString(addr));
        accountTable.update(userID, fields);
        emit UpdateAccount(userID, addr);
    }

    // 查询用户地址
    function getAddressByUserID(string memory userID) public view returns(address) {
        Entry memory entry = accountTable.select(userID);
        if(entry.fields.length == 1) {
            return cast.stringToAddr(entry.fields[0]);
        }
        return address(0x0);
    }

    // 查询余额
    function balanceOf(address addr) public view returns(int256) {
        Entry memory entry = balanceTable.select(cast.addrToString(addr));
        if(entry.fields.length == 1) {
            return cast.stringToS256(entry.fields[0]);
        }
        return int256(0);
    }

    // 铸币（管理员）
    function issueEvidence(IssueItem memory item) public onlyOwner {
        Entry memory entry = balanceTable.select(cast.addrToString(item.toAddress));
        if (entry.fields.length == 0) {
            // 不存在则插入
            Entry memory newEntry = Entry(cast.addrToString(item.toAddress), new string[](1));
            newEntry.fields[0] = cast.s256ToString(int256(0));
            balanceTable.insert(newEntry);
        }
        int256 oldBalance = balanceOf(item.toAddress);
        int256 newBalance = oldBalance + int256(item.amount);
        UpdateField[] memory fields = new UpdateField[](1);
        fields[0] = UpdateField("balance", cast.s256ToString(newBalance));
        balanceTable.update(cast.addrToString(item.toAddress), fields);
        emit IssueEvidence(item.toAddress, item.amount, item.orderID);
    }

    // 转账存证
    function transferEvidence(TransferItem memory item) public {
        require(item.toAddress != address(0x0), "to address error");
        int256 fromBalance = balanceOf(item.fromAddress);
        int256 toBalance = balanceOf(item.toAddress);
        // 扣减（允许余额变为负数）
        UpdateField[] memory fieldsFrom = new UpdateField[](1);
        fieldsFrom[0] = UpdateField("balance", cast.s256ToString(fromBalance - int256(item.amount)));
        balanceTable.update(cast.addrToString(item.fromAddress), fieldsFrom);
        // 增加
        UpdateField[] memory fieldsTo = new UpdateField[](1);
        fieldsTo[0] = UpdateField("balance", cast.s256ToString(toBalance + int256(item.amount)));
        balanceTable.update(cast.addrToString(item.toAddress), fieldsTo);
        emit TransferEvidence(item.fromAddress, item.toAddress, item.amount, item.orderID, item.comments);
    }

    // 取款存证（转到指定账户）
    function withdrawEvidence(WithdrawItem memory item) public {
        require(withdrawAccount != address(0x0), "withdrawAccount not set");
        int256 fromBalance = balanceOf(item.userAddress);
        require(fromBalance >= int256(item.amount), "balance not enough");
        int256 toBalance = balanceOf(withdrawAccount);
        // 扣减
        UpdateField[] memory fieldsFrom = new UpdateField[](1);
        fieldsFrom[0] = UpdateField("balance", cast.s256ToString(fromBalance - int256(item.amount)));
        balanceTable.update(cast.addrToString(item.userAddress), fieldsFrom);
        // 增加
        UpdateField[] memory fieldsTo = new UpdateField[](1);
        fieldsTo[0] = UpdateField("balance", cast.s256ToString(toBalance + int256(item.amount)));
        balanceTable.update(cast.addrToString(withdrawAccount), fieldsTo);
        emit WithdrawEvidence(item.userAddress, item.amount, item.orderID);
    }

    // 设置取款账户（管理员）
    function setWithdrawAccount(address _account) public onlyOwner {
        withdrawAccount = _account;
    }
} 