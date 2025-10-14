// SPDX-License-Identifier: Apache-2.0
pragma solidity >=0.6.10 <0.8.20;
pragma experimental ABIEncoderV2;

import "./Table.sol";
import "./Cast.sol";

contract RewardData {
    struct DisposeRewardItem {
        string timestamp;
        string orderID;
        string category;
        uint256 amount;
        uint256 price;
        uint256 accruedReward;
        uint256 deductedReward;
        uint256 actualReward;
        string comments;
    }
    struct PropertyRewardItem {
        string timestamp;
        string orderID;
        string courtID;
        uint256 houseHoldNum;
        uint256 score;
        uint256 unit;
        uint256 ratio;
        uint256 amount;
        string comments;
    }
    Cast constant cast =  Cast(address(0x100f));
    TableManager constant tm =  TableManager(address(0x1002));
    Table disposeRewardTable;
    Table propertyRewardTable;
    string constant DISPOSE_REWARD_TABLE = "dispose_reward_v2";
    string constant PROPERTY_REWARD_TABLE = "property_reward_v2";

    constructor() {
        // create dispose reward table
        string[] memory disposeColumns = new string[](8);
        disposeColumns[0] = "timestamp";
        disposeColumns[1] = "category";
        disposeColumns[2] = "amount";
        disposeColumns[3] = "price";
        disposeColumns[4] = "accrued_reward";
        disposeColumns[5] = "deducted_reward";
        disposeColumns[6] = "actual_reward";
        disposeColumns[7] = "comments";
        TableInfo memory disposeTf = TableInfo(KeyOrder.Lexicographic, "order_id", disposeColumns);
        tm.createTable(DISPOSE_REWARD_TABLE, disposeTf);
        address t1 = tm.openTable(DISPOSE_REWARD_TABLE);
        require(t1 != address(0x0), "disposeRewardTable error");
        disposeRewardTable = Table(t1);
        // create property reward table
        string[] memory propertyColumns = new string[](8);
        propertyColumns[0] = "timestamp";
        propertyColumns[1] = "court_id";
        propertyColumns[2] = "house_hold_num";
        propertyColumns[3] = "score";
        propertyColumns[4] = "unit";
        propertyColumns[5] = "ratio";
        propertyColumns[6] = "amount";
        propertyColumns[7] = "comments";
        TableInfo memory propertyTf = TableInfo(KeyOrder.Lexicographic, "order_id", propertyColumns);
        tm.createTable(PROPERTY_REWARD_TABLE, propertyTf);
        address t2 = tm.openTable(PROPERTY_REWARD_TABLE);
        require(t2 != address(0x0), "propertyRewardTable error");
        propertyRewardTable = Table(t2);
    }
    function insertDisposeReward(DisposeRewardItem memory item) public returns(int32) {
        Entry memory entry = Entry(item.orderID, new string[](8));
        entry.fields[0] = item.timestamp;
        entry.fields[1] = item.category;
        entry.fields[2] = cast.u256ToString(item.amount);
        entry.fields[3] = cast.u256ToString(item.price);
        entry.fields[4] = cast.u256ToString(item.accruedReward);
        entry.fields[5] = cast.u256ToString(item.deductedReward);
        entry.fields[6] = cast.u256ToString(item.actualReward);
        entry.fields[7] = item.comments;
        return disposeRewardTable.insert(entry);
    }
    function insertPropertyReward(PropertyRewardItem memory item) public returns(int32) {
        Entry memory entry = Entry(item.orderID, new string[](8));
        entry.fields[0] = item.timestamp;
        entry.fields[1] = item.courtID;
        entry.fields[2] = cast.u256ToString(item.houseHoldNum);
        entry.fields[3] = cast.u256ToString(item.score);
        entry.fields[4] = cast.u256ToString(item.unit);
        entry.fields[5] = cast.u256ToString(item.ratio);
        entry.fields[6] = cast.u256ToString(item.amount);
        entry.fields[7] = item.comments;
        return propertyRewardTable.insert(entry);
    }
    function getDisposeRewardTable() public view returns (Table) {
        return disposeRewardTable;
    }
    function getPropertyRewardTable() public view returns (Table) {
        return propertyRewardTable;
    }
    // 按orderID查找DisposeRewardItem
    function getDisposeRewardByOrderID(string memory orderID) public view returns (DisposeRewardItem memory) {
        Entry memory entry = disposeRewardTable.select(orderID);
        require(entry.fields.length == 8, "not found");
        DisposeRewardItem memory item;
        item.timestamp = entry.fields[0];
        item.orderID = orderID;
        item.category = entry.fields[1];
        item.amount = cast.stringToU256(entry.fields[2]);
        item.price = cast.stringToU256(entry.fields[3]);
        item.accruedReward = cast.stringToU256(entry.fields[4]);
        item.deductedReward = cast.stringToU256(entry.fields[5]);
        item.actualReward = cast.stringToU256(entry.fields[6]);
        item.comments = entry.fields[7];
        return item;
    }
    // 按orderID查找PropertyRewardItem
    function getPropertyRewardByOrderID(string memory orderID) public view returns (PropertyRewardItem memory) {
        Entry memory entry = propertyRewardTable.select(orderID);
        require(entry.fields.length == 8, "not found");
        PropertyRewardItem memory item;
        item.timestamp = entry.fields[0];
        item.orderID = orderID;
        item.courtID = entry.fields[1];
        item.houseHoldNum = cast.stringToU256(entry.fields[2]);
        item.score = cast.stringToU256(entry.fields[3]);
        item.unit = cast.stringToU256(entry.fields[4]);
        item.ratio = cast.stringToU256(entry.fields[5]);
        item.amount = cast.stringToU256(entry.fields[6]);
        item.comments = entry.fields[7];
        return item;
    }
}