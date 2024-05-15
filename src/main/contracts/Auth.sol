// SPDX-License-Identifier: Apache-2.0
pragma solidity >=0.6.10 <0.8.20;
pragma experimental ABIEncoderV2;


contract Auth{
    address public _owner;
    mapping(address=>bool) private _acl;

    constructor() public{
        _owner = msg.sender;
    }

    modifier onlyOwner(){
        require(msg.sender == _owner, "Not admin");
        _;
    }

    modifier auth(){
        require(msg.sender == _owner || _acl[msg.sender]==true, "Not authenticated");
        _;
    }

    function allow(address addr) public onlyOwner{
        _acl[addr] = true;
    }

    function deny(address addr) public onlyOwner{
        _acl[addr] = false;
    }
}