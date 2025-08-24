package org.example.dabashou_spring_demo.model.bo;

import java.lang.String;
import java.math.BigInteger;
import org.fisco.bcos.sdk.v3.codec.datatypes.Address;
import org.fisco.bcos.sdk.v3.codec.datatypes.DynamicStruct;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;

public  class WithdrawItem extends DynamicStruct {
  public String orderID;

  public String userID;

  public String userAddress;

  public BigInteger amount;

  public String receiverAccount;

  public WithdrawItem(Utf8String orderID, Utf8String userID, Address userAddress, Uint256 amount,
      Utf8String receiverAccount) {
    super(orderID,userID,userAddress,amount,receiverAccount);
    this.orderID = orderID.getValue();
    this.userID = userID.getValue();
    this.userAddress = userAddress.getValue();
    this.amount = amount.getValue();
    this.receiverAccount = receiverAccount.getValue();
  }

  public WithdrawItem(String orderID, String userID, String userAddress, BigInteger amount,
      String receiverAccount) {
    super(new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(orderID),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(userID),new org.fisco.bcos.sdk.v3.codec.datatypes.Address(userAddress),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(amount),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(receiverAccount));
    this.orderID = orderID;
    this.userID = userID;
    this.userAddress = userAddress;
    this.amount = amount;
    this.receiverAccount = receiverAccount;
  }
}
