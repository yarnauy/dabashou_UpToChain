package org.example.dabashou_spring_demo.model.bo;

import java.lang.String;
import java.math.BigInteger;
import org.fisco.bcos.sdk.v3.codec.datatypes.Address;
import org.fisco.bcos.sdk.v3.codec.datatypes.DynamicStruct;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;

public class TransferItem extends DynamicStruct {
  public String orderID;

  public String fromUserID;

  public String toUserID;

  public String fromAddress;

  public String toAddress;

  public BigInteger amount;

  public String comments;

  public TransferItem(Utf8String orderID, Utf8String fromUserID, Utf8String toUserID,
      Address fromAddress, Address toAddress, Uint256 amount, Utf8String comments) {
    super(orderID,fromUserID,toUserID,fromAddress,toAddress,amount,comments);
    this.orderID = orderID.getValue();
    this.fromUserID = fromUserID.getValue();
    this.toUserID = toUserID.getValue();
    this.fromAddress = fromAddress.getValue();
    this.toAddress = toAddress.getValue();
    this.amount = amount.getValue();
    this.comments = comments.getValue();
  }

  public TransferItem(String orderID, String fromUserID, String toUserID, String fromAddress,
      String toAddress, BigInteger amount, String comments) {
    super(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(orderID),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(fromUserID),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(toUserID),new org.fisco.bcos.sdk.v3.codec.datatypes.Address(fromAddress),new org.fisco.bcos.sdk.v3.codec.datatypes.Address(toAddress),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(amount),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(comments));
    this.orderID = orderID;
    this.fromUserID = fromUserID;
    this.toUserID = toUserID;
    this.fromAddress = fromAddress;
    this.toAddress = toAddress;
    this.amount = amount;
    this.comments = comments;
  }
}
