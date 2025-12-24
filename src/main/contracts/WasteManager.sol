// SPDX-License-Identifier: Apache-2.0
pragma solidity >=0.6.10 <0.8.20;
pragma experimental ABIEncoderV2;

import "./WasteData.sol";
import "./RewardData.sol";
import "./Auth.sol";
import "./ResourceCoinManager.sol";
import "./AlgoData.sol";


contract WasteManager is Auth{


    WasteData public _wasteData;
    RewardData public _rewardData;
    ResourceCoinManager public _resouceCoin;
    address public rewardAccount;
    AlgoData public _algoData;

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


    constructor(address resouceCoinAddr, address _rewardAccount) {
        _wasteData = new WasteData();
        _rewardData = new RewardData();
        _resouceCoin = ResourceCoinManager(resouceCoinAddr);
        rewardAccount = _rewardAccount;
        _algoData = new AlgoData();
    }

    function dispose(WasteData.DisposeItem memory item) public auth returns(int32){
        // receiverAddress由业务方传入或在此处赋值
        // 例如：item.receiverAddress = msg.sender; // 如需自动赋值
        int32 insertRes =  _wasteData.insert( item );
        // if(insertRes >0 ){
        //     uint256 month = timestampToTotalMonth(item.timestamp);
        //     insertRes = _statData.add_waste(month, item.resourceCoin);
        // }else{
        //     return 0;
        // }
        // return insertRes;
        return insertRes;
    }

    function update(WasteData.UpdateItem memory item) public auth returns(int32 res){
        //TODO require exist
        uint256 old = _wasteData.update(item);
        
        return 0;
    }

    // function select(StatsData.StatsItem memory test) public view  returns(uint256)
    // {
    //     // return 0;
    //     // return _statData.select(test);
    //     return 0;
    // }


    // function select(uint256 monthLow, uint256 monthHigh) public view returns (StatsData.StatsItem[] memory)
    // {
    //     // return _statData.select(monthLow,monthHigh);
    //     StatsData.StatsItem[] memory empty;
    //     return empty;
    // }

    event Settle(uint256 timestamp, string targerOrderID, string orderID, address toAddress, uint256 amount);

    function settle(uint256 timestamp, string memory targerOrderID, string memory orderID, address toAddress, uint256 amount) public auth {
        require(toAddress != address(0x0), "toAddress is zero");
        require(amount > 0, "amount must be positive");
        int256 rewardBalance = _resouceCoin.balanceOf(rewardAccount);
        require(rewardBalance >= int256(amount), "reward account balance not enough");
        // 构造TransferItem结构体
        ResourceCoinManager.TransferItem memory item = ResourceCoinManager.TransferItem({
            orderID: orderID,
            fromUserID: "admin",
            toUserID: targerOrderID,
            fromAddress: rewardAccount,
            toAddress: toAddress,
            amount: amount,
            comments: "settle transfer"
        });
        _resouceCoin.transferEvidence(item);
        emit Settle(timestamp, targerOrderID, orderID, toAddress, amount);
    }

    function disposeReward(RewardData.DisposeRewardItem memory item) public auth returns(int32) {
        return _rewardData.insertDisposeReward(item);
    }

    function propertyReward(RewardData.PropertyRewardItem memory item) public auth returns(int32) {
        return _rewardData.insertPropertyReward(item);
    }

    // 查询指定orderID的投放记录
    function getDispose(string memory orderID) public view returns (WasteData.DisposeItem memory) {
        return _wasteData.selectDisposeByOrderID(orderID);
    }

    // 查询指定updateOrderID的更新记录
    function getUpdate(string memory updateOrderID) public view returns (WasteData.UpdateItem memory) {
        return _wasteData.selectUpdateByOrderID(updateOrderID);
    }

    // 算法注册
    function register(uint256 timestamp, string memory algoID, bytes32 algoHash, string memory comments) public returns(int32 res){
        require(!_algoData.exist(algoID),"algo exists");
        res =  _algoData.insert(timestamp,algoID,algoHash,comments);
    }
    // 查询算法
    function selectAlgo(string memory algoID) public view returns (uint256 , bytes32, string memory) {
        return _algoData.select(algoID);
    }

    // 按orderID查询DisposeRewardItem
    function getDisposeRewardByOrderID(string memory orderID) public view returns (RewardData.DisposeRewardItem memory) {
        return _rewardData.getDisposeRewardByOrderID(orderID);
    }
    // 按orderID查询PropertyRewardItem
    function getPropertyRewardByOrderID(string memory orderID) public view returns (RewardData.PropertyRewardItem memory) {
        return _rewardData.getPropertyRewardByOrderID(orderID);
    }

}
