// SPDX-License-Identifier: Apache-2.0
pragma solidity >=0.6.10 <0.8.20;
pragma experimental ABIEncoderV2;

import "./EvidenceData.sol";

contract EvidenceManager {
    EvidenceData public _evidenceData;

    event EvidenceCreated(string evidenceID, address indexed creator, uint256 mode);
    event EvidenceApproved(string evidenceID, address indexed approver);

    constructor() {
        _evidenceData = new EvidenceData();
    }

    function createEvidence(
        uint256 timestamp,
        string memory evidenceID,
        bytes32 contentHash,
        uint256 mode,
        bytes memory reserved1,
        string[] memory reserved2
    ) public {
        require(bytes(evidenceID).length > 0, "evidenceID required");
        require(!_evidenceData.isExist(evidenceID), "evidence already exists");
        _evidenceData.createEvidence(timestamp, evidenceID, contentHash, mode, reserved1, reserved2, msg.sender);
        emit EvidenceCreated(evidenceID, msg.sender, mode);
    }

    function approveEvidence(uint256 timestamp, string memory evidenceID) public {
        require(_evidenceData.isExist(evidenceID), "evidence not found");
        uint256 mode = _evidenceData.getMode(evidenceID);
        require(mode == 1, "not multisig mode");
        require(!_evidenceData.isApproved(evidenceID, msg.sender), "already approved");
        _evidenceData.approveEvidence(timestamp, evidenceID, msg.sender);
        emit EvidenceApproved(evidenceID, msg.sender);
    }

    function verifyEvidence(string memory evidenceID, bytes32 expectedHash) public view returns (bool isValid, string memory message) {
        if (!_evidenceData.isExist(evidenceID)) {
            return (false, "evidence not found");
        }
        bytes32 contentHash = _evidenceData.getHash(evidenceID);
        if (contentHash != expectedHash) {
            return (false, "hash not match");
        }
        return (true, "evidence valid");
    }
    
    function getEvidence(string memory evidenceID) public view returns (
        uint256, string memory, bytes32, uint256, bytes memory, string[] memory, address
    ) {
        return _evidenceData.getEvidence(evidenceID);
    }
    function getApprovals(string memory evidenceID) public view returns (EvidenceData.Approval[] memory) {
        return _evidenceData.getApprovals(evidenceID);
    }
} 