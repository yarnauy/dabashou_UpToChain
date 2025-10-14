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
        uint256 price;
        uint256 reward;
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
        uint256 price;
        uint256 reward;
        string  wasteType;
        uint256 quantity;
        uint256 score;
        int256 resourceCoinChange;
        string  comment;
    }


    Cast constant cast =  Cast(address(0x100f));
    TableManager constant tm =  TableManager(address(0x1002));
    Table order_table;
    Table update_table;
    string constant WASTE_ORDER_TABLE_NAME = "waste_order_v3";
    string constant WASTE_UPDATE_TABLE_NAME = "waste_update_v3";

    constructor () {
        // create order table
        string[] memory columnNames = new string[](16);
        columnNames[0] = "timestamp";
        columnNames[1] = "user_id";
        columnNames[2] = "device_id";
        columnNames[3] = "bin_id";
        columnNames[4] = "court_id";
        columnNames[5] = "images_hash";
        columnNames[6] = "credit_params_hash";
        columnNames[7] = "price";
        columnNames[8] = "reward";
        columnNames[9] = "waste_type";
        columnNames[10] = "quantity";
        columnNames[11] = "score";
        columnNames[12] = "resource_coin";
        columnNames[13] = "receiver_address";
        columnNames[14] = "version";
        columnNames[15] = "comment";

        TableInfo memory order_tf = TableInfo(KeyOrder.Lexicographic ,"order_id", columnNames);

        tm.createTable(WASTE_ORDER_TABLE_NAME, order_tf);
        address t_address = tm.openTable(WASTE_ORDER_TABLE_NAME);
        require(t_address!=address(0x0),"");
        order_table = Table(t_address);

        // create update table
        string[] memory updateColumnNames = new string[](16);
        updateColumnNames[0] = "timestamp";
        updateColumnNames[1] = "dispose_order_id";
        updateColumnNames[2] = "user_id";
        updateColumnNames[3] = "device_id";
        updateColumnNames[4] = "bin_id";
        updateColumnNames[5] = "court_id";
        updateColumnNames[6] = "images_hash";
        updateColumnNames[7] = "credit_params_hash";
        updateColumnNames[8] = "price";
        updateColumnNames[9] = "reward";
        updateColumnNames[10] = "waste_type";
        updateColumnNames[11] = "quantity";
        updateColumnNames[12] = "score";
        updateColumnNames[13] = "resource_coin_change";
        updateColumnNames[14] = "comment";
        updateColumnNames[15] = "update_order_id";

        TableInfo memory update_tf = TableInfo(KeyOrder.Lexicographic ,"update_order_id", updateColumnNames);

        tm.createTable(WASTE_UPDATE_TABLE_NAME, update_tf);
        address update_t_address = tm.openTable(WASTE_UPDATE_TABLE_NAME);
        require(update_t_address!=address(0x0),"");
        update_table = Table(update_t_address);

    }
   

    function insert(DisposeItem memory item)
    public returns(int32){

        Entry memory entry = Entry(item.orderID, new string[](16));
        entry.fields[0] = cast.u256ToString(item.timestamp);
        entry.fields[1] = item.userID;
        entry.fields[2] = item.deviceID;
        entry.fields[3] = item.binID;
        entry.fields[4] = item.courtID;
        entry.fields[5] = cast.bytes32ToString(item.imagesHash);
        entry.fields[6] = cast.bytes32ToString(item.creditParamsHash);
        entry.fields[7] = cast.u256ToString(item.price);
        entry.fields[8] = cast.u256ToString(item.reward);
        entry.fields[9] = item.wasteType;
        entry.fields[10] = cast.u256ToString(item.quantity);
        entry.fields[11] = cast.u256ToString(item.score);
        entry.fields[12] = cast.u256ToString(item.resourceCoin);
        entry.fields[13] = cast.addrToString(item.receiverAddress);
        entry.fields[14] = cast.u256ToString(0);
        entry.fields[15] = "init";

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
        if(entry.fields.length != 16){
            return 0;
        }
        oldResourceCoin = cast.stringToU256(entry.fields[11]);
        int256 newResourceCoin = int256(oldResourceCoin) + item.resourceCoinChange;
        UpdateField[] memory updateFields = new UpdateField[](15);
        updateFields[0] = UpdateField("timestamp", cast.u256ToString(item.timestamp));
        updateFields[1] = UpdateField("user_id",item.userID);
        updateFields[2] = UpdateField("device_id",item.deviceID);
        updateFields[3] = UpdateField("bin_id", item.binID);
        updateFields[4] = UpdateField("court_id", item.courtID);
        updateFields[5] = UpdateField("images_hash", cast.bytes32ToString(item.imagesHash));
        updateFields[6] = UpdateField("credit_params_hash", cast.bytes32ToString(item.creditParamsHash));
        updateFields[7] = UpdateField("price", cast.u256ToString(item.price));
        updateFields[8] = UpdateField("reward", cast.u256ToString(item.reward));
        updateFields[9] = UpdateField("waste_type", item.wasteType);
        updateFields[10] = UpdateField("quantity", cast.u256ToString(item.quantity));
        updateFields[11] = UpdateField("score", cast.u256ToString(item.score));
        updateFields[12] = UpdateField("resource_coin", cast.u256ToString(uint256(newResourceCoin)));
        updateFields[13] = UpdateField("receiver_address", entry.fields[13]);
        updateFields[14] = UpdateField("comment", item.comment);
        int32 result = order_table.update(item.disposeOrderID, updateFields);
        if (result != 0){
            // 保存update记录
            Entry memory updateEntry = Entry(item.orderID, new string[](16));
            updateEntry.fields[0] = cast.u256ToString(item.timestamp);
            updateEntry.fields[1] = item.disposeOrderID;
            updateEntry.fields[2] = item.userID;
            updateEntry.fields[3] = item.deviceID;
            updateEntry.fields[4] = item.binID;
            updateEntry.fields[5] = item.courtID;
            updateEntry.fields[6] = cast.bytes32ToString(item.imagesHash);
            updateEntry.fields[7] = cast.bytes32ToString(item.creditParamsHash);
            updateEntry.fields[8] = cast.u256ToString(item.price);
            updateEntry.fields[9] = cast.u256ToString(item.reward);
            updateEntry.fields[10] = item.wasteType;
            updateEntry.fields[11] = cast.u256ToString(item.quantity);
            updateEntry.fields[12] = cast.u256ToString(item.score);
            updateEntry.fields[13] = cast.s256ToString(item.resourceCoinChange);
            updateEntry.fields[14] = item.comment;
            updateEntry.fields[15] = item.orderID;
            
            update_table.insert(updateEntry);
            
            return oldResourceCoin;
        }
        return 0;
    }

    // 按orderID查询DisposeItem
    function selectDisposeByOrderID(string memory orderID) public view returns (DisposeItem memory) {
        Entry memory entry = order_table.select(orderID);
        require(entry.fields.length == 16, "not found");
        DisposeItem memory item;
        item.timestamp = cast.stringToU256(entry.fields[0]);
        item.orderID = orderID;
        item.userID = entry.fields[1];
        item.deviceID = entry.fields[2];
        item.binID = entry.fields[3];
        item.courtID = entry.fields[4];
        item.imagesHash = cast.stringToBytes32(entry.fields[5]);
        item.creditParamsHash = cast.stringToBytes32(entry.fields[6]);
        item.price = cast.stringToU256(entry.fields[7]);
        item.reward = cast.stringToU256(entry.fields[8]);
        item.wasteType = entry.fields[9];
        item.quantity = cast.stringToU256(entry.fields[10]);
        item.score = cast.stringToU256(entry.fields[11]);
        item.resourceCoin = cast.stringToU256(entry.fields[12]);
        item.receiverAddress = cast.stringToAddr(entry.fields[13]);
        return item;
    }

    // 按updateOrderID查询UpdateItem
    function selectUpdateByOrderID(string memory updateOrderID) public view returns (UpdateItem memory) {
        Entry memory entry = update_table.select(updateOrderID);
        require(entry.fields.length == 16, "update record not found");
        UpdateItem memory item;
        item.timestamp = cast.stringToU256(entry.fields[0]);
        item.disposeOrderID = entry.fields[1];
        item.orderID = updateOrderID;
        item.userID = entry.fields[2];
        item.deviceID = entry.fields[3];
        item.binID = entry.fields[4];
        item.courtID = entry.fields[5];
        item.imagesHash = cast.stringToBytes32(entry.fields[6]);
        item.creditParamsHash = cast.stringToBytes32(entry.fields[7]);
        item.price = cast.stringToU256(entry.fields[8]);
        item.reward = cast.stringToU256(entry.fields[9]);
        item.wasteType = entry.fields[10];
        item.quantity = cast.stringToU256(entry.fields[11]);
        item.score = cast.stringToU256(entry.fields[12]);
        item.resourceCoinChange = cast.stringToS256(entry.fields[13]);
        item.comment = entry.fields[14];
        return item;
    }

}