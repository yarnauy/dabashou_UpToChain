pragma solidity >=0.6.10 <0.8.20;
pragma experimental ABIEncoderV2;

import "./Table.sol";
import "./Cast.sol";


contract RewardData {

    struct RewardItem{
        uint256 timestamp;
        string  orderID;
        string  courtID;
        uint256 houseHoldNum;
        uint256 score;
        uint256 ratio;
        uint256 resourceCoin;
    }


    event InsertReward(string indexed order_id,string court_id, uint256 house_hold_num, uint256 score, uint256 ratio,uint256 resource_coin,uint256 timestamp);


    Cast constant cast =  Cast(address(0x100f));
    TableManager constant tm =  TableManager(address(0x1002));
    Table table;
    string constant TABLE_NAME = "reward";
    constructor ()  {
        // create table
        string[] memory columnNames = new string[](6);
        columnNames[0] = "timestamp";
        columnNames[1] = "court_id";
        columnNames[2] = "house_hold_num";
        columnNames[3] = "score";
        columnNames[4] = "ratio";
        columnNames[5] = "resource_coin";
        TableInfo memory tf = TableInfo(KeyOrder.Lexicographic ,"order_id", columnNames);

        tm.createTable(TABLE_NAME, tf);
        address t_address = tm.openTable(TABLE_NAME);
        require(t_address!=address(0x0),"");
        table = Table(t_address);
    }

    function getTableAddress() public view returns (address){
        return address(table);
    }

    function select(string memory orderID) public view returns (uint256,string memory,uint256,uint256,uint256,uint256)
    {
        Entry memory entry = table.select(orderID);
        uint256  timestamp;
        string memory courtID;
        uint256  houseHoldNum;
        uint256  score;
        uint256  ratio;
        uint256  resourceCoin;

        if(entry.fields.length == 6){
            timestamp = cast.stringToU256(entry.fields[0]);
            courtID = entry.fields[1];
            houseHoldNum = cast.stringToU256(entry.fields[2]);
            score = cast.stringToU256(entry.fields[3]);
            ratio = cast.stringToU256(entry.fields[4]);
            resourceCoin = cast.stringToU256(entry.fields[5]);
        }
        return (timestamp, courtID, houseHoldNum, score, ratio, resourceCoin);
    }

    function insert(RewardItem memory item)
        public returns (int32){
        Entry memory entry = Entry(item.orderID, new string[](6));
        entry.fields[0] = cast.u256ToString(item.timestamp);
        entry.fields[1] = item.courtID;
        entry.fields[2] = cast.u256ToString(item.houseHoldNum);
        entry.fields[3] = cast.u256ToString(item.score);
        entry.fields[4] = cast.u256ToString(item.ratio);
        entry.fields[5] = cast.u256ToString(item.resourceCoin);

        int32 result = table.insert(entry);
        // emit InsertReward(orderID, courtID, houseHoldNum, score, ratio,resourceCoin,timestamp);
        return result;
    }




}