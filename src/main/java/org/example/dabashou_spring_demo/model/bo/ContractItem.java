package org.example.dabashou_spring_demo.model.bo;

import org.fisco.bcos.sdk.v3.codec.datatypes.DynamicStruct;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;

import java.math.BigInteger;

public  class ContractItem extends DynamicStruct {
  public BigInteger timestamp;

  public String contractID;

  public byte[] docHash;

  public ContractItem(Uint256 timestamp, Utf8String contractID, Bytes32 docHash) {
    super(timestamp,contractID,docHash);
    this.timestamp = timestamp.getValue();
    this.contractID = contractID.getValue();
    this.docHash = docHash.getValue();
  }

  public ContractItem(BigInteger timestamp, String contractID, byte[] docHash) {
    super(new Uint256(timestamp),new Utf8String(contractID),new Bytes32(docHash));
    this.timestamp = timestamp;
    this.contractID = contractID;
    this.docHash = docHash;
  }
}
