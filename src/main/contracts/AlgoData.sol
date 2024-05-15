// SPDX-License-Identifier: Apache-2.0
pragma solidity >=0.6.10 <0.8.20;
pragma experimental ABIEncoderV2;

import "./Table.sol";
import "./Cast.sol";


contract AlgoData {

    event InsertAlgo(string indexed algoID, bytes32 indexed algoHash, string comments, uint256 timestamp,int256 result);



    Cast constant cast =  Cast(address(0x100f));
    TableManager constant tm =  TableManager(address(0x1002));
    Table table;
    string constant TABLE_NAME = "waste_algo";
    constructor ()  {
        // create table
        string[] memory columnNames = new string[](3);
        columnNames[0] = "timestamp";
        columnNames[1] = "algo_hash";
        columnNames[2] = "comments";
        TableInfo memory tf = TableInfo(KeyOrder.Lexicographic ,"id", columnNames);

        tm.createTable(TABLE_NAME, tf);
        address t_address = tm.openTable(TABLE_NAME);
        require(t_address!=address(0x0),"");
        table = Table(t_address);
    }

    function getTableAddress() public view returns (address){
        return address(table);
    }

    function select(string memory algoID) public view returns (uint256 , bytes32, string memory)
    {
        Entry memory entry = table.select(algoID);
        uint256  timestamp;
        bytes32  algoHash;
        string memory comments;
        if(entry.fields.length == 3){
            timestamp = cast.stringToU256(entry.fields[0]);
            algoHash = cast.stringToBytes32(entry.fields[1]);
            comments = entry.fields[2];
        }
        return (timestamp, algoHash, comments);
    }


    function exist(string memory algo_id)
    public
    view
    returns (bool)
    {
        //TODO: 如果不存在是否会有报错
        Condition[] memory cond = new Condition[](1);
        cond[0] = Condition(ConditionOP.EQ, "id", algo_id);
        return table.count(cond)>0;

    }


    function insert(uint256 timestamp ,string memory algoID, bytes32 algoHash,  string memory comments)
        public returns (int32){
        Entry memory entry = Entry(algoID, new string[](3));
        entry.fields[0] = cast.u256ToString(timestamp);
        entry.fields[1] = cast.bytes32ToString(algoHash);
        entry.fields[2] = comments;
        int32 result = table.insert(entry);
        emit InsertAlgo(algoID, algoHash, comments, timestamp, result);
        return result;
    }




}