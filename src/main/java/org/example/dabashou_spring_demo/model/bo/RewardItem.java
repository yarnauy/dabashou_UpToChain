package org.example.dabashou_spring_demo.model.bo;

import java.lang.String;
import java.math.BigInteger;
import org.fisco.bcos.sdk.v3.codec.datatypes.DynamicStruct;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;

public class RewardItem extends DynamicStruct {
  public BigInteger timestamp;

  public String orderID;

  public String courtID;

  public BigInteger houseHoldNum;

  public BigInteger score;

  public BigInteger ratio;

  public BigInteger resourceCoin;

  public RewardItem(Uint256 timestamp, Utf8String orderID, Utf8String courtID, Uint256 houseHoldNum,
      Uint256 score, Uint256 ratio, Uint256 resourceCoin) {
    super(timestamp,orderID,courtID,houseHoldNum,score,ratio,resourceCoin);
    this.timestamp = timestamp.getValue();
    this.orderID = orderID.getValue();
    this.courtID = courtID.getValue();
    this.houseHoldNum = houseHoldNum.getValue();
    this.score = score.getValue();
    this.ratio = ratio.getValue();
    this.resourceCoin = resourceCoin.getValue();
  }

  public RewardItem(BigInteger timestamp, String orderID, String courtID, BigInteger houseHoldNum,
      BigInteger score, BigInteger ratio, BigInteger resourceCoin) {
    super(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(timestamp),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(orderID),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(courtID),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(houseHoldNum),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(score),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(ratio),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(resourceCoin));
    this.timestamp = timestamp;
    this.orderID = orderID;
    this.courtID = courtID;
    this.houseHoldNum = houseHoldNum;
    this.score = score;
    this.ratio = ratio;
    this.resourceCoin = resourceCoin;
  }
}
