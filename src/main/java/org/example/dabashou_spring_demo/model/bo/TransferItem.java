package org.example.dabashou_spring_demo.model.bo;

import org.fisco.bcos.sdk.v3.codec.datatypes.Address;
import org.fisco.bcos.sdk.v3.codec.datatypes.DynamicStruct;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;

import java.math.BigInteger;

public  class TransferItem extends DynamicStruct {
  public String orderID;

  public String fromUserID;

  public String toUserID;

  public String fromAddress;

  public String toAddress;

  public BigInteger amount;

  public TransferItem(Utf8String orderID, Utf8String fromUserID, Utf8String toUserID,
      Address fromAddress, Address toAddress, Uint256 amount) {
    super(orderID,fromUserID,toUserID,fromAddress,toAddress,amount);
    this.orderID = orderID.getValue();
    this.fromUserID = fromUserID.getValue();
    this.toUserID = toUserID.getValue();
    this.fromAddress = fromAddress.getValue();
    this.toAddress = toAddress.getValue();
    this.amount = amount.getValue();
  }

  public TransferItem(String orderID, String fromUserID, String toUserID, String fromAddress,
      String toAddress, BigInteger amount) {
    super(new Utf8String(orderID),new Utf8String(fromUserID),new Utf8String(toUserID),new Address(fromAddress),new Address(toAddress),new Uint256(amount));
    this.orderID = orderID;
    this.fromUserID = fromUserID;
    this.toUserID = toUserID;
    this.fromAddress = fromAddress;
    this.toAddress = toAddress;
    this.amount = amount;
  }
}
