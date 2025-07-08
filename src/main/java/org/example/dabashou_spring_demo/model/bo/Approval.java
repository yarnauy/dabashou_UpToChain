package org.example.dabashou_spring_demo.model.bo;

import org.fisco.bcos.sdk.v3.codec.datatypes.Address;
import org.fisco.bcos.sdk.v3.codec.datatypes.DynamicStruct;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;

import java.math.BigInteger;

public  class Approval extends DynamicStruct {
  public BigInteger timestamp;

  public String evidenceID;

  public String approver;

  public Approval(Uint256 timestamp, Utf8String evidenceID, Address approver) {
    super(timestamp,evidenceID,approver);
    this.timestamp = timestamp.getValue();
    this.evidenceID = evidenceID.getValue();
    this.approver = approver.getValue();
  }

  public Approval(BigInteger timestamp, String evidenceID, String approver) {
    super(new Uint256(timestamp),new Utf8String(evidenceID),new Address(approver));
    this.timestamp = timestamp;
    this.evidenceID = evidenceID;
    this.approver = approver;
  }
}
