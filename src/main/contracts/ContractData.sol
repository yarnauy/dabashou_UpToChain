// SPDX-License-Identifier: Apache-2.0
pragma solidity >=0.6.10 <0.8.20;
pragma experimental ABIEncoderV2;

import "./Table.sol";
import "./Cast.sol";

contract ContractData {
    struct ContractItem {
        uint256 timestamp;
        string contractID;
        bytes32 docHash;
    }
    struct AdvanceItem {
        uint256 timestamp;
        string contractID;
        bytes32 appendHash;
        string comments;
    }
    
    struct SignItem {
        uint256 timestamp;
        string contractID;
        address signer;
    }
    Cast constant cast =  Cast(address(0x100f));
    TableManager constant tm =  TableManager(address(0x1002));
    Table contractTable;
    Table advanceTable;
    Table signTable;
    string constant CONTRACT_TABLE = "contract_v2";
    string constant ADVANCE_TABLE = "contract_advance_v2";
    string constant SIGN_TABLE = "contract_sign_v2";

    constructor() {
        // 合同表
        string[] memory contractColumns = new string[](2);
        contractColumns[0] = "timestamp";
        contractColumns[1] = "doc_hash";
        TableInfo memory contractTf = TableInfo(KeyOrder.Lexicographic, "contract_id", contractColumns);
        tm.createTable(CONTRACT_TABLE, contractTf);
        address t1 = tm.openTable(CONTRACT_TABLE);
        require(t1 != address(0x0), "contractTable error");
        contractTable = Table(t1);
        // 推进表
        string[] memory advanceColumns = new string[](4);
        advanceColumns[0] = "timestamp";
        advanceColumns[1] = "contract_id";
        advanceColumns[2] = "append_hash";
        advanceColumns[3] = "comments";
        TableInfo memory advanceTf = TableInfo(KeyOrder.Lexicographic, "advance_id", advanceColumns);
        tm.createTable(ADVANCE_TABLE, advanceTf);
        address t2 = tm.openTable(ADVANCE_TABLE);
        require(t2 != address(0x0), "advanceTable error");
        advanceTable = Table(t2);
        // 签署表
        string[] memory signColumns = new string[](3);
        signColumns[0] = "timestamp";
        signColumns[1] = "contract_id";
        signColumns[2] = "signer";
        TableInfo memory signTf = TableInfo(KeyOrder.Lexicographic, "sign_id", signColumns);
        tm.createTable(SIGN_TABLE, signTf);
        address t3 = tm.openTable(SIGN_TABLE);
        require(t3 != address(0x0), "signTable error");
        signTable = Table(t3);
    }
    function insertContract(ContractItem memory item) public returns(int32) {
        Entry memory entry = Entry(item.contractID, new string[](2));
        entry.fields[0] = cast.u256ToString(item.timestamp);
        entry.fields[1] = cast.bytes32ToString(item.docHash);
        return contractTable.insert(entry);
    }
    function insertAdvance(AdvanceItem memory item) public returns(int32) {
        // 自动生成advanceID
        bytes32 advanceIDHash = keccak256(abi.encodePacked(item.contractID, item.timestamp, msg.sender));
        string memory advanceID = toHexString(advanceIDHash);
        Entry memory entry = Entry(advanceID, new string[](4));
        entry.fields[0] = cast.u256ToString(item.timestamp);
        entry.fields[1] = item.contractID;
        entry.fields[2] = cast.bytes32ToString(item.appendHash);
        entry.fields[3] = item.comments;
        return advanceTable.insert(entry);
    }
    function insertSign(SignItem memory item) public returns(int32) {
        // 自动生成signID
        bytes32 signIDHash = keccak256(abi.encodePacked(item.contractID, item.timestamp, item.signer));
        string memory signID = toHexString(signIDHash);
        Entry memory entry = Entry(signID, new string[](3));
        entry.fields[0] = cast.u256ToString(item.timestamp);
        entry.fields[1] = item.contractID;
        entry.fields[2] = cast.addrToString(item.signer);
        return signTable.insert(entry);
    }
    // 辅助函数：bytes32转hex字符串
    function toHexString(bytes32 data) internal pure returns (string memory) {
        bytes memory alphabet = "0123456789abcdef";
        bytes memory str = new bytes(64);
        for (uint i = 0; i < 32; i++) {
            str[i*2] = alphabet[uint(uint8(data[i] >> 4))];
            str[1+i*2] = alphabet[uint(uint8(data[i] & 0x0f))];
        }
        return string(str);
    }
    function getContractBase(string memory contractID) public view returns (uint256, string memory, bytes32, address) {
        Entry memory entry = contractTable.select(contractID);
        if(entry.fields.length != 2) {
            return (0, "", 0, address(0));
        }
        // creator信息无法直接获取，返回address(0)
        return (
            cast.stringToU256(entry.fields[0]),
            contractID,
            cast.stringToBytes32(entry.fields[1]),
            address(0)
        );
    }
    function getSigns(string memory contractID) public view returns (SignItem[] memory) {
        // 构造条件按contract_id查询
        Condition[] memory conds = new Condition[](1);
        conds[0] = Condition(ConditionOP.EQ, "contract_id", contractID);
        Limit memory limit = Limit(0, 500);
        Entry[] memory entries = signTable.select(conds, limit);
        SignItem[] memory result = new SignItem[](entries.length);
        for (uint i = 0; i < entries.length; i++) {
            result[i] = SignItem({
                timestamp: cast.stringToU256(entries[i].fields[0]),
                contractID: entries[i].fields[1],
                signer: cast.stringToAddr(entries[i].fields[2])
            });
        }
        return result;
    }
    function getAdvances(string memory contractID) public view returns (AdvanceItem[] memory) {
        // 构造条件按contract_id查询
        Condition[] memory conds = new Condition[](1);
        conds[0] = Condition(ConditionOP.EQ, "contract_id", contractID);
        Limit memory limit = Limit(0, 500);
        Entry[] memory entries = advanceTable.select(conds, limit);
        AdvanceItem[] memory result = new AdvanceItem[](entries.length);
        for (uint i = 0; i < entries.length; i++) {
            result[i] = AdvanceItem({
                timestamp: cast.stringToU256(entries[i].fields[0]),
                contractID: entries[i].fields[1],
                appendHash: cast.stringToBytes32(entries[i].fields[2]),
                comments: entries[i].fields[3]
            });
        }
        return result;
    }
} 