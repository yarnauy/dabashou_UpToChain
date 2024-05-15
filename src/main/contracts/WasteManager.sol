// SPDX-License-Identifier: Apache-2.0
pragma solidity >=0.6.10 <0.8.20;
pragma experimental ABIEncoderV2;

import "./WasteData.sol";
import "./RewardData.sol";
import "./StatsData.sol";
import "./Auth.sol";


contract WasteManager is Auth{


    WasteData public _wasteData;
    RewardData public _rewardData;
    StatsData public _statData;

    uint256 constant SECONDS_PER_DAY = 24 * 60 * 60;
    int256 constant OFFSET19700101 = 2440588;

    function _daysToDate(uint256 _days) internal pure returns (uint256 year, uint256 month, uint256 day) {
    unchecked {
        int256 __days = int256(_days);

        int256 L = __days + 68569 + OFFSET19700101;
        int256 N = (4 * L) / 146097;
        L = L - (146097 * N + 3) / 4;
        int256 _year = (4000 * (L + 1)) / 1461001;
        L = L - (1461 * _year) / 4 + 31;
        int256 _month = (80 * L) / 2447;
        int256 _day = L - (2447 * _month) / 80;
        L = _month / 11;
        _month = _month + 2 - 12 * L;
        _year = 100 * (N - 49) + _year + L;

        year = uint256(_year);
        month = uint256(_month);
        day = uint256(_day);
    }
    }

    function timestampToTotalMonth(uint256 timestamp) public pure returns (uint256 totalMonth) {
    unchecked {
        //offset of UTC+8 Timezone
        uint256 newTimestamp = timestamp + 28800;
        (uint256 year, uint256 month, ) = _daysToDate(newTimestamp / SECONDS_PER_DAY);
        totalMonth = year*12 + month;
    }
    }


    constructor() {
        _wasteData = new WasteData();
        _rewardData = new RewardData();
        _statData = new StatsData(true);
    }

    function dispose(WasteData.DisposeItem memory item) public auth returns(int32){


        int32 insertRes =  _wasteData.insert( item );
        if(insertRes >0 ){
            uint256 month = timestampToTotalMonth(item.timestamp);
            insertRes = _statData.add_waste(month, item.resourceCoin);
        }else{
            return 0;
        }

        return insertRes;

    }

    function update(WasteData.UpdateItem memory item) public auth returns(int32 res){

        //TODO require exist
        uint256 old = _wasteData.update(item);
        uint256 month = timestampToTotalMonth(item.timestamp);

        if (item.resourceCoin < old){
            res = _statData.update_waste(month, old - item.resourceCoin, false);
        }else{
            res = _statData.update_waste(month, item.resourceCoin - old, true);
        }

    }

    function issueReward(RewardData.RewardItem memory item) public auth returns(int32 res){

        int32 insertRes =  _rewardData.insert(item );
        if(insertRes >0 ){
            uint256 month= timestampToTotalMonth(item.timestamp);
            insertRes = _statData.add_reward(month, item.resourceCoin);
        }else{
            return 0;
        }
        return insertRes;
    }

    function select(StatsData.StatsItem memory test) public view  returns(uint256)
    {
        return 0;
    }


    function select(uint256 monthLow, uint256 monthHigh) public view returns (StatsData.StatsItem[] memory)
    {
        return _statData.select(monthLow,monthHigh);
    }


}