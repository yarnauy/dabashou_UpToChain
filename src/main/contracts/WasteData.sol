// SPDX-License-Identifier: Apache-2.0
pragma solidity >=0.6.10 <0.8.20;
pragma experimental ABIEncoderV2;

import "./Table.sol";
import "./Cast.sol";



contract WasteData {

    

    // event InsertWasteRecord(string indexed orderID, uint256 timestamp, string indexed userID, string deviceID, string binID,
    //     string courtID, bytes32 imagesHash, bytes32 creditParamsHash, string creditAlgoID, string  wasteType, uint256 quantity,
    //     uint256 score, uint256 resourceCoin, uint256 version, string comments);


    struct DisposeItem{
        uint256 timestamp; 
        string  orderID;
        string  userID; 
        string  deviceID;
        string  binID;
        string  courtID;
        bytes32 imagesHash;
        bytes32 creditParamsHash;
        string  creditAlgoID;
        string  wasteType;
        uint256 quantity;
        uint256 score;
        uint256 resourceCoin;
        address receiverAddress;
    }


    struct UpdateItem{
        uint256 timestamp; 
        string  disposeOrderID;
        string  orderID;
        string  userID; 
        string  deviceID;
        string  binID;
        string  courtID;
        bytes32 imagesHash;
        bytes32 creditParamsHash;
        string  creditAlgoID;
        string  wasteType;
        uint256 quantity;
        uint256 score;
        int256 resourceCoinChange;
        string  comment;
    }


    Cast constant cast =  Cast(address(0x100f));
    TableManager constant tm =  TableManager(address(0x1002));
    Table order_table;
    string constant WASTE_ORDER_TABLE_NAME = "waste_order_v2";

    constructor () {
        // create table
        string[] memory columnNames = new string[](15);
        columnNames[0] = "timestamp";
        columnNames[1] = "user_id";
        columnNames[2] = "device_id";
        columnNames[3] = "bin_id";
        columnNames[4] = "court_id";
        columnNames[5] = "images_hash";
        columnNames[6] = "credit_params_hash";
        columnNames[7] = "credit_algo_id";
        columnNames[8] = "waste_type";
        columnNames[9] = "quantity";
        columnNames[10] = "score";
        columnNames[11] = "resource_coin";
        columnNames[12] = "receiver_address";
        columnNames[13] = "version";
        columnNames[14] = "comment";

        TableInfo memory order_tf = TableInfo(KeyOrder.Lexicographic ,"order_id", columnNames);

        tm.createTable(WASTE_ORDER_TABLE_NAME, order_tf);
        address t_address = tm.openTable(WASTE_ORDER_TABLE_NAME);
        require(t_address!=address(0x0),"");
        order_table = Table(t_address);

    }
   

    function insert(DisposeItem memory item)
    public returns(int32){

        Entry memory entry = Entry(item.orderID, new string[](15));
        entry.fields[0] = cast.u256ToString(item.timestamp);
        entry.fields[1] = item.userID;
        entry.fields[2] = item.deviceID;
        entry.fields[3] = item.binID;
        entry.fields[4] = item.courtID;
        entry.fields[5] = cast.bytes32ToString(item.imagesHash);
        entry.fields[6] = cast.bytes32ToString(item.creditParamsHash);
        entry.fields[7] = item.creditAlgoID;
        entry.fields[8] = item.wasteType;
        entry.fields[9] = cast.u256ToString(item.quantity);
        entry.fields[10] = cast.u256ToString(item.score);
        entry.fields[11] = cast.u256ToString(item.resourceCoin);
        entry.fields[12] = cast.addrToString(item.receiverAddress);
        entry.fields[13] = cast.u256ToString(0);
        entry.fields[14] = "init";

        // emit InsertWasteRecord(item.orderID, item.timestamp, item.userID, item.deviceID, item.binID,
        // item.courtID, item.imagesHash, item.creditParamsHash, item.creditAlgoID, item.wasteType, item.quantity,
        // item.score, item.resourceCoin, 0, "init");
        return order_table.insert(entry);
            
    }
    
    function update(UpdateItem memory item)
    public
    returns (uint256)
    {
        // 通过disposeOrderID查找老order
        Entry memory entry = order_table.select(item.disposeOrderID);
        uint256  oldResourceCoin;
        if(entry.fields.length != 15){
            return 0;
        }
        oldResourceCoin = cast.stringToU256(entry.fields[11]);
        int256 newResourceCoin = int256(oldResourceCoin) + item.resourceCoinChange;
        UpdateField[] memory updateFields = new UpdateField[](14);
        updateFields[0] = UpdateField("timestamp", cast.u256ToString(item.timestamp));
        updateFields[1] = UpdateField("user_id",item.userID);
        updateFields[2] = UpdateField("device_id",item.deviceID);
        updateFields[3] = UpdateField("bin_id", item.binID);
        updateFields[4] = UpdateField("court_id", item.courtID);
        updateFields[5] = UpdateField("images_hash", cast.bytes32ToString(item.imagesHash));
        updateFields[6] = UpdateField("credit_params_hash", cast.bytes32ToString(item.creditParamsHash));
        updateFields[7] = UpdateField("credit_algo_id", item.creditAlgoID);
        updateFields[8] = UpdateField("waste_type", item.wasteType);
        updateFields[9] = UpdateField("quantity", cast.u256ToString(item.quantity));
        updateFields[10] = UpdateField("score", cast.u256ToString(item.score));
        updateFields[11] = UpdateField("resource_coin", cast.u256ToString(uint256(newResourceCoin)));
        updateFields[12] = UpdateField("receiver_address", entry.fields[12]);
        updateFields[13] = UpdateField("comment", item.comment);
        int32 result = order_table.update(item.disposeOrderID, updateFields);
        if (result != 0){
            return oldResourceCoin;
        }
        return 0;
    }

    // 按orderID查询DisposeItem
    function selectDisposeByOrderID(string memory orderID) public view returns (DisposeItem memory) {
        Entry memory entry = order_table.select(orderID);
        require(entry.fields.length == 15, "not found");
        DisposeItem memory item;
        item.timestamp = cast.stringToU256(entry.fields[0]);
        item.orderID = orderID;
        item.userID = entry.fields[1];
        item.deviceID = entry.fields[2];
        item.binID = entry.fields[3];
        item.courtID = entry.fields[4];
        item.imagesHash = cast.stringToBytes32(entry.fields[5]);
        item.creditParamsHash = cast.stringToBytes32(entry.fields[6]);
        item.creditAlgoID = entry.fields[7];
        item.wasteType = entry.fields[8];
        item.quantity = cast.stringToU256(entry.fields[9]);
        item.score = cast.stringToU256(entry.fields[10]);
        item.resourceCoin = cast.stringToU256(entry.fields[11]);
        item.receiverAddress = cast.stringToAddr(entry.fields[12]);
        return item;
    }

}