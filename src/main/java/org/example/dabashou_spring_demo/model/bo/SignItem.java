package org.example.dabashou_spring_demo.model.bo;

import org.fisco.bcos.sdk.v3.codec.datatypes.Address;
import org.fisco.bcos.sdk.v3.codec.datatypes.DynamicStruct;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;

import java.math.BigInteger;

public  class SignItem extends DynamicStruct {
  public BigInteger timestamp;

  public String contractID;

  public String signer;

  public SignItem(Uint256 timestamp, Utf8String contractID, Address signer) {
    super(timestamp,contractID,signer);
    this.timestamp = timestamp.getValue();
    this.contractID = contractID.getValue();
    this.signer = signer.getValue();
  }

  public SignItem(BigInteger timestamp, String contractID, String signer) {
    super(new Uint256(timestamp),new Utf8String(contractID),new Address(signer));
    this.timestamp = timestamp;
    this.contractID = contractID;
    this.signer = signer;
  }
}
