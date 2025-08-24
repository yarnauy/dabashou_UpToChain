// SPDX-License-Identifier: Apache-2.0
pragma solidity >=0.6.10 <0.8.20;
pragma experimental ABIEncoderV2;

contract EvidenceData {
    struct Evidence {
        uint256 timestamp;
        string evidenceID;
        bytes32 contentHash;
        uint256 mode;
        bytes reserved1;
        string[] reserved2;
        address creator;
    }
    struct Approval {
        uint256 timestamp;
        string evidenceID;
        address approver;
    }
    mapping(string => Evidence) private evidences;
    mapping(string => Approval[]) private evidenceApprovals;
    mapping(string => mapping(address => bool)) private approvalCheck;

    function createEvidence(
        uint256 timestamp,
        string memory evidenceID,
        bytes32 contentHash,
        uint256 mode,
        bytes memory reserved1,
        string[] memory reserved2,
        address creator
    ) public {
        require(bytes(evidenceID).length > 0, "evidenceID required");
        require(evidences[evidenceID].timestamp == 0, "evidence already exists");
        evidences[evidenceID] = Evidence({
            timestamp: timestamp,
            evidenceID: evidenceID,
            contentHash: contentHash,
            mode: mode,
            reserved1: reserved1,
            reserved2: reserved2,
            creator: creator
        });
    }

    function approveEvidence(uint256 timestamp, string memory evidenceID, address approver) public {
        require(evidences[evidenceID].timestamp != 0, "evidence not found");
        require(evidences[evidenceID].mode == 1, "not multisig mode");
        require(!approvalCheck[evidenceID][approver], "already approved");
        evidenceApprovals[evidenceID].push(Approval({timestamp: timestamp, evidenceID: evidenceID, approver: approver}));
        approvalCheck[evidenceID][approver] = true;
    }

    function verifyEvidence(string memory evidenceID, bytes32 expectedHash) public view returns (bool isValid, string memory message) {
        Evidence storage ev = evidences[evidenceID];
        if (ev.timestamp == 0) {
            return (false, "evidence not found");
        }
        if (ev.contentHash != expectedHash) {
            return (false, "hash not match");
        }
        return (true, "evidence valid");
    }

    function getEvidence(string memory evidenceID) public view returns (
        uint256, string memory, bytes32, uint256, bytes memory, string[] memory, address
    ) {
        Evidence storage ev = evidences[evidenceID];
        return (
            ev.timestamp,
            ev.evidenceID,
            ev.contentHash,
            ev.mode,
            ev.reserved1,
            ev.reserved2,
            ev.creator
        );
    }
    function getApprovals(string memory evidenceID) public view returns (Approval[] memory) {
        return evidenceApprovals[evidenceID];
    }
    function isApproved(string memory evidenceID, address approver) public view returns (bool) {
        return approvalCheck[evidenceID][approver];
    }
    function isExist(string memory evidenceID) public view returns (bool) {
        return evidences[evidenceID].timestamp != 0;
    }
    function getMode(string memory evidenceID) public view returns (uint256) {
        return evidences[evidenceID].mode;
    }
    function getHash(string memory evidenceID) public view returns (bytes32) {
        return evidences[evidenceID].contentHash;
    }
} 