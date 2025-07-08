package org.example.dabashou_spring_demo.model.bo;

import org.fisco.bcos.sdk.v3.codec.datatypes.DynamicStruct;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;

import java.math.BigInteger;

public  class AdvanceItem extends DynamicStruct {
  public BigInteger timestamp;

  public String contractID;

  public byte[] appendHash;

  public String comments;

  public AdvanceItem(Uint256 timestamp, Utf8String contractID, Bytes32 appendHash,
      Utf8String comments) {
    super(timestamp,contractID,appendHash,comments);
    this.timestamp = timestamp.getValue();
    this.contractID = contractID.getValue();
    this.appendHash = appendHash.getValue();
    this.comments = comments.getValue();
  }

  public AdvanceItem(BigInteger timestamp, String contractID, byte[] appendHash, String comments) {
    super(new Uint256(timestamp),new Utf8String(contractID),new Bytes32(appendHash),new Utf8String(comments));
    this.timestamp = timestamp;
    this.contractID = contractID;
    this.appendHash = appendHash;
    this.comments = comments;
  }
}
