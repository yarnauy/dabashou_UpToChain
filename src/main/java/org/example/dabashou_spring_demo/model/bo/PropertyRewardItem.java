package org.example.dabashou_spring_demo.model.bo;

import org.fisco.bcos.sdk.v3.codec.datatypes.DynamicStruct;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;

import java.math.BigInteger;

public  class PropertyRewardItem extends DynamicStruct {
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
    super(new Uint256(timestamp),new Utf8String(orderID),new Utf8String(courtID),new Uint256(houseHoldNum),new Uint256(score),new Uint256(unit),new Uint256(ratio),new Uint256(amount),new Utf8String(comments));
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
}
