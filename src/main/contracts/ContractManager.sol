// SPDX-License-Identifier: Apache-2.0
pragma solidity >=0.6.10 <0.8.20;
pragma experimental ABIEncoderV2;

import "./ContractData.sol";
import "./Auth.sol";

contract ContractManager is Auth {
    ContractData public _contractData;
    constructor() {
        _contractData = new ContractData();
    }
    function initContract(ContractData.ContractItem memory item) public auth returns(int32) {
        return _contractData.insertContract(item);
    }
    function advanceContract(ContractData.AdvanceItem memory item) public auth returns(int32) {
        return _contractData.insertAdvance(item);
    }
    function signContract(uint256 timestamp, string memory contractID) public returns(int32) {
        ContractData.SignItem memory signItem = ContractData.SignItem({
            timestamp: timestamp,
            contractID: contractID,
            signer: msg.sender
        });
        return _contractData.insertSign(signItem);
    }
    function getContractInfo(string memory contractID) public view returns (
        uint256, string memory, bytes32, address, 
        ContractData.SignItem[] memory, 
        ContractData.AdvanceItem[] memory
    ) {
        // 获取基本信息
        (uint256 timestamp, string memory id, bytes32 docHash, address creator) = _contractData.getContractBase(contractID);
        // 获取签名信息
        ContractData.SignItem[] memory signs = _contractData.getSigns(contractID);
        // 获取advance信息
        ContractData.AdvanceItem[] memory advances = _contractData.getAdvances(contractID);
        return (timestamp, id, docHash, creator, signs, advances);
    }
} 