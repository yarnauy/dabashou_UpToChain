package org.example.dabashou_spring_demo.model.bo;

import java.lang.String;
import java.math.BigInteger;
import org.fisco.bcos.sdk.v3.codec.datatypes.DynamicStruct;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;

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
    super(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(timestamp),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(orderID),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(category),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(amount),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(price),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(accruedReward),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(deductedReward),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(actualReward),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(comments));
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

  /**
   * 从ArrayList构造DisposeRewardItem对象
   * @param list 包含DisposeRewardItem字段的ArrayList
   * @return DisposeRewardItem对象，如果构造失败返回null
   */
  public static DisposeRewardItem fromArrayList(java.util.ArrayList<?> list) {
    if (list == null || list.size() < 9) {
      return null;
    }
    
    try {
      return new DisposeRewardItem(
        (BigInteger) list.get(0),  // timestamp
        (String) list.get(1),     // orderID
        (String) list.get(2),     // category
        (BigInteger) list.get(3), // amount
        (BigInteger) list.get(4), // price
        (BigInteger) list.get(5), // accruedReward
        (BigInteger) list.get(6), // deductedReward
        (BigInteger) list.get(7), // actualReward
        (String) list.get(8)      // comments
      );
    } catch (Exception e) {
      return null;
    }
  }
}
