// SPDX-License-Identifier: Apache-2.0
pragma solidity >=0.6.10 <0.8.20;
pragma experimental ABIEncoderV2;

import "./Table.sol";
import "./Cast.sol";


contract StatsData {

    struct StatsItem{

        uint256  month;
        uint256  wasteSum;
        uint256  rewardSum;
        uint256  contractSum;
        uint256  blockNum;
    }


    event InsertStats(uint256 indexed month, uint256 wasteSum, uint256 rewardSum, uint256 contractSum, uint256 blockNum);



    Cast constant cast =  Cast(address(0x100f));
    TableManager constant tm =  TableManager(address(0x1002));
    Table table;
    string constant TABLE_NAME = "stats_month";
    
    constructor (bool  create) {
        // create table

        if(create){
            string[] memory columnNames = new string[](4);
            columnNames[0] = "waste_sum";
            columnNames[1] = "reward_sum";
            columnNames[2] = "contract_sum";
            columnNames[3] = "block_num";
            TableInfo memory tf = TableInfo(KeyOrder.Numerical ,"month", columnNames);
            tm.createTable(TABLE_NAME, tf);
        }
        
        address t_address = tm.openTable(TABLE_NAME);
        require(t_address!=address(0x0),"");
        table = Table(t_address);
    }

    function getTableAddress() public view returns (address){
        return address(table);
    }

    function select(string memory month) public view returns (uint256, uint256, uint256, uint256)
    {
        Entry memory entry = table.select(month);

        uint256  wasteSum;
        uint256  rewardSum;
        uint256  contractSum;
        uint256  blockNum;

        if(entry.fields.length ==4){
            wasteSum = cast.stringToU256(entry.fields[0]);
            rewardSum = cast.stringToU256(entry.fields[1]);
            contractSum = cast.stringToU256(entry.fields[2]);
            blockNum = cast.stringToU256(entry.fields[3]);
        }
        return ( wasteSum, rewardSum, contractSum, blockNum);
    }

    function select(uint256 idLow, uint256 idHigh) public view returns (StatsItem[] memory)
    {
        Limit memory limit = Limit(0, 60);
        Condition[] memory cond = new Condition[](2);
        cond[0] = Condition(ConditionOP.GE, "id", cast.u256ToString(idLow));
        cond[1] = Condition(ConditionOP.LE, "id", cast.u256ToString(idHigh));
        Entry[] memory entries = table.select(cond, limit);
        StatsItem[] memory items = new StatsItem[](entries.length);
        for(uint i = 0; i < items.length; i++)
        {
            items[i].month = cast.stringToU256(entries[i].key);
            items[i].wasteSum =cast.stringToU256(entries[i].fields[0]);
            items[i].rewardSum = cast.stringToU256(entries[i].fields[1]);
            items[i].contractSum = cast.stringToU256(entries[i].fields[2]);
            items[i].blockNum = cast.stringToU256(entries[i].fields[3]);
        }
        return items;
    }


    function count(uint256 month) public view returns (uint32)
    {
        Condition[] memory cond = new Condition[](1);
        cond[0] = Condition(ConditionOP.EQ, "month", cast.u256ToString(month));
        return table.count(cond);
    }


    function insert(string memory month,  uint256 wasteSum, uint256 rewardSum, uint256 contractSum, uint256 blockNum)
        public returns (int32){
        Entry memory entry = Entry(month, new string[](4));
        entry.fields[0] = cast.u256ToString(wasteSum);
        entry.fields[1] = cast.u256ToString(rewardSum);
        entry.fields[2] = cast.u256ToString(contractSum);
        entry.fields[3] = cast.u256ToString(blockNum);
        int32 result = table.insert(entry);
        return result;
    }


    function add_waste(uint256 month , uint256 wasteNum)public returns (int32 result){
        
        string memory monthstr = cast.u256ToString(month);

        if (count(month) == 0){
            result = insert(monthstr, wasteNum, 0, 0, block.number);
        }else{
            result = update_waste(monthstr, wasteNum, true);
        }

    }

    function update_waste(string memory month , uint256 wasteNum, bool add)
    public returns (int32 result){

        ( uint256 old_wasteSum, , , ) = select(month);
        result = _update_num(month, "waste_sum",old_wasteSum, wasteNum, add);

    }

    function update_waste(uint256 month , uint256 wasteNum, bool add)
    public returns (int32 result){

        string memory monthstr = cast.u256ToString(month);
        result = update_waste(monthstr, wasteNum , add);

    }

    function add_reward(uint256 month , uint256 rewardNum)
    public returns (int32 result){
        string memory monthstr = cast.u256ToString(month);

        if (count(month) == 0){
            result = insert(monthstr, 0, rewardNum, 0, block.number);
        }else{
            result = update_reward(monthstr, rewardNum, true);
        }
    }

    function update_reward(string memory month , uint256 rewardNum, bool add)
    public returns (int32 result){

        ( , uint256 old_rewardSum , , ) = select(month);
        result = _update_num(month, "reward_sum",old_rewardSum, rewardNum, add);
    }

    function update_reward(uint256 month , uint256 rewardNum, bool add)
    public returns (int32 result){
        string memory monthstr = cast.u256ToString(month);
        result = update_reward(monthstr ,  rewardNum,  add);
    }


    function add_contract(uint256 month , uint256 contractNum)
    public returns (int32 result){

        string memory monthstr = cast.u256ToString(month);

        if (count(month) == 0){
            result = insert(monthstr, 0, 0, contractNum, block.number);
        }else{
            result = update_contract(monthstr, contractNum, true);
        }
    }

    function update_contract(string memory month , uint256 contractNum, bool add)
    public returns (int32 result){

        ( , , uint256 old_contractSum, ) = select(month);
        result = _update_num(month, "contract_sum",old_contractSum, contractNum, add);

    }


    function _update_num(string memory month , string memory column, uint256 origin, uint256 diff, bool add)
    internal returns (int32 result){


        UpdateField[] memory updateFields = new UpdateField[](2);

        uint256 new_Num;
        if(add){
            new_Num = origin + diff;
        }else{
            require(origin > diff,  "over subtraction error.");
            new_Num = origin - diff;
        }

        updateFields[0] = UpdateField(column,  cast.u256ToString(new_Num));
        updateFields[1] = UpdateField("block_num",  cast.u256ToString(block.number));

        result = table.update(month, updateFields);

    }

}