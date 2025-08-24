package org.example.dabashou_spring_demo.model.bo;

import java.lang.String;
import java.math.BigInteger;
import org.fisco.bcos.sdk.v3.codec.datatypes.Address;
import org.fisco.bcos.sdk.v3.codec.datatypes.DynamicStruct;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;

public class IssueItem extends DynamicStruct {
  public String orderID;

  public String toAddress;

  public BigInteger amount;

  public IssueItem(Utf8String orderID, Address toAddress, Uint256 amount) {
    super(orderID,toAddress,amount);
    this.orderID = orderID.getValue();
    this.toAddress = toAddress.getValue();
    this.amount = amount.getValue();
  }

  public IssueItem(String orderID, String toAddress, BigInteger amount) {
    super(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(orderID),new org.fisco.bcos.sdk.v3.codec.datatypes.Address(toAddress),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(amount));
    this.orderID = orderID;
    this.toAddress = toAddress;
    this.amount = amount;
  }
}
