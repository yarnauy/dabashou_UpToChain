package org.example.dabashou_spring_demo.model.bo;

import org.fisco.bcos.sdk.v3.codec.datatypes.DynamicStruct;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;

import java.math.BigInteger;

public  class DisposeRewardItem extends DynamicStruct {
  public BigInteger timestamp;

  public String orderID;

  public String category;

  public BigInteger amount;

  public BigInteger price;

  public BigInteger accruedReward;

  public BigInteger deductedReward;

  public BigInteger actualReward;

  public String comments;

  public DisposeRewardItem(Uint256 timestamp, Utf8String orderID, Utf8String category,
      Uint256 amount, Uint256 price, Uint256 accruedReward, Uint256 deductedReward,
      Uint256 actualReward, Utf8String comments) {
    super(timestamp,orderID,category,amount,price,accruedReward,deductedReward,actualReward,comments);
    this.timestamp = timestamp.getValue();
    this.orderID = orderID.getValue();
    this.category = category.getValue();
    this.amount = amount.getValue();
    this.price = price.getValue();
    this.accruedReward = accruedReward.getValue();
    this.deductedReward = deductedReward.getValue();
    this.actualReward = actualReward.getValue();
    this.comments = comments.getValue();
  }

  public DisposeRewardItem(BigInteger timestamp, String orderID, String category, BigInteger amount,
      BigInteger price, BigInteger accruedReward, BigInteger deductedReward,
      BigInteger actualReward, String comments) {
    super(new Uint256(timestamp),new Utf8String(orderID),new Utf8String(category),new Uint256(amount),new Uint256(price),new Uint256(accruedReward),new Uint256(deductedReward),new Uint256(actualReward),new Utf8String(comments));
    this.timestamp = timestamp;
    this.orderID = orderID;
    this.category = category;
    this.amount = amount;
    this.price = price;
    this.accruedReward = accruedReward;
    this.deductedReward = deductedReward;
    this.actualReward = actualReward;
    this.comments = comments;
  }
}
