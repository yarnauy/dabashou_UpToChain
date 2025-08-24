package org.example.dabashou_spring_demo.model.bo;

import java.lang.String;
import java.math.BigInteger;
import org.fisco.bcos.sdk.v3.codec.datatypes.DynamicStruct;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;

public class PropertyRewardItem extends DynamicStruct {
  public BigInteger timestamp;

  public String orderID;

  public String courtID;

  public BigInteger houseHoldNum;

  public BigInteger score;

  public BigInteger unit;

  public BigInteger ratio;

  public BigInteger amount;

  public String comments;

  public PropertyRewardItem(Uint256 timestamp, Utf8String orderID, Utf8String courtID,
      Uint256 houseHoldNum, Uint256 score, Uint256 unit, Uint256 ratio, Uint256 amount,
      Utf8String comments) {
    super(timestamp,orderID,courtID,houseHoldNum,score,unit,ratio,amount,comments);
    this.timestamp = timestamp.getValue();
    this.orderID = orderID.getValue();
    this.courtID = courtID.getValue();
    this.houseHoldNum = houseHoldNum.getValue();
    this.score = score.getValue();
    this.unit = unit.getValue();
    this.ratio = ratio.getValue();
    this.amount = amount.getValue();
    this.comments = comments.getValue();
  }

  public PropertyRewardItem(BigInteger timestamp, String orderID, String courtID,
      BigInteger houseHoldNum, BigInteger score, BigInteger unit, BigInteger ratio,
      BigInteger amount, String comments) {
    super(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(timestamp),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(orderID),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(courtID),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(houseHoldNum),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(score),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(unit),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(ratio),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(amount),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(comments));
    this.timestamp = timestamp;
    this.orderID = orderID;
    this.courtID = courtID;
    this.houseHoldNum = houseHoldNum;
    this.score = score;
    this.unit = unit;
    this.ratio = ratio;
    this.amount = amount;
    this.comments = comments;
  }

  /**
   * 从ArrayList构造PropertyRewardItem对象
   * @param list 包含PropertyRewardItem字段的ArrayList
   * @return PropertyRewardItem对象，如果构造失败返回null
   */
  public static PropertyRewardItem fromArrayList(java.util.ArrayList<?> list) {
    if (list == null || list.size() < 9) {
      return null;
    }
    
    try {
      return new PropertyRewardItem(
        (BigInteger) list.get(0),  // timestamp
        (String) list.get(1),     // orderID
        (String) list.get(2),     // courtID
        (BigInteger) list.get(3), // houseHoldNum
        (BigInteger) list.get(4), // score
        (BigInteger) list.get(5), // unit
        (BigInteger) list.get(6), // ratio
        (BigInteger) list.get(7), // amount
        (String) list.get(8)      // comments
      );
    } catch (Exception e) {
      return null;
    }
  }
}
