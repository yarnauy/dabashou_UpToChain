// SPDX-License-Identifier: Apache-2.0
pragma solidity >=0.6.10 <0.8.20;
pragma experimental ABIEncoderV2;

import "./AlgoData.sol";

contract AlgoRegistry {


    AlgoData public _algoData;

    constructor() {
        _algoData = new AlgoData();
    }

    function register(uint256 timestamp, string memory algoID,
                        bytes32 algoHash, string memory comments) public returns(int32 res){
        //TODO check existence
        require(_algoData.exist(algoID),"algo exists");
        res =  _algoData.insert(timestamp,algoID,algoHash,comments);
    }

    function select(string memory algoID) public view returns (uint256 , bytes32, string memory)
    {
        return _algoData.select(algoID);
    }

}