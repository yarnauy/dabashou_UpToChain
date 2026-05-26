package org.example.dabashou_spring_demo.model.bo;

import java.lang.String;
import java.math.BigInteger;
import org.fisco.bcos.sdk.v3.codec.datatypes.DynamicStruct;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Int256;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;

public class ActivityRewardItem extends DynamicStruct {
  public BigInteger timestamp;

  public String orderID;

  public String userID;

  public String activityID;

  public BigInteger resourceCoin;

  public ActivityRewardItem(Uint256 timestamp, Utf8String orderID, Utf8String userID,
      Utf8String activityID, Int256 resourceCoin) {
    super(timestamp,orderID,userID,activityID,resourceCoin);
    this.timestamp = timestamp.getValue();
    this.orderID = orderID.getValue();
    this.userID = userID.getValue();
    this.activityID = activityID.getValue();
    this.resourceCoin = resourceCoin.getValue();
  }

  public ActivityRewardItem(BigInteger timestamp, String orderID, String userID, String activityID,
      BigInteger resourceCoin) {
    super(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(timestamp),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(orderID),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(userID),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(activityID),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Int256(resourceCoin));
    this.timestamp = timestamp;
    this.orderID = orderID;
    this.userID = userID;
    this.activityID = activityID;
    this.resourceCoin = resourceCoin;
  }

  public static ActivityRewardItem fromArrayList(java.util.ArrayList<?> list) {
    if (list == null || list.size() < 5) {
      return null;
    }
    try {
      return new ActivityRewardItem(
          (BigInteger) list.get(0),
          (String) list.get(1),
          (String) list.get(2),
          (String) list.get(3),
          (BigInteger) list.get(4));
    } catch (Exception e) {
      return null;
    }
  }
}
