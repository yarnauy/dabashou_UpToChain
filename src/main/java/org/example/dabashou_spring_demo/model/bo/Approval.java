package org.example.dabashou_spring_demo.model.bo;

import java.lang.String;
import java.math.BigInteger;
import org.fisco.bcos.sdk.v3.codec.datatypes.Address;
import org.fisco.bcos.sdk.v3.codec.datatypes.DynamicStruct;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;

public class Approval extends DynamicStruct {
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
    super(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(timestamp),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(evidenceID),new org.fisco.bcos.sdk.v3.codec.datatypes.Address(approver));
    this.timestamp = timestamp;
    this.evidenceID = evidenceID;
    this.approver = approver;
  }
}
