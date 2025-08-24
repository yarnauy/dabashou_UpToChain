package org.example.dabashou_spring_demo.model.bo;

import java.lang.String;
import java.math.BigInteger;
import org.fisco.bcos.sdk.v3.codec.datatypes.DynamicStruct;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Int256;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;

public  class UpdateItem extends DynamicStruct {
  public BigInteger timestamp;

  public String disposeOrderID;

  public String orderID;

  public String userID;

  public String deviceID;

  public String binID;

  public String courtID;

  public byte[] imagesHash;

  public byte[] creditParamsHash;

  public BigInteger price;

  public BigInteger reward;

  public String wasteType;

  public BigInteger quantity;

  public BigInteger score;

  public BigInteger resourceCoinChange;

  public String comment;

  public UpdateItem(Uint256 timestamp, Utf8String disposeOrderID, Utf8String orderID,
      Utf8String userID, Utf8String deviceID, Utf8String binID, Utf8String courtID,
      Bytes32 imagesHash, Bytes32 creditParamsHash, Uint256 price, Uint256 reward,
      Utf8String wasteType, Uint256 quantity, Uint256 score, Int256 resourceCoinChange,
      Utf8String comment) {
    super(timestamp,disposeOrderID,orderID,userID,deviceID,binID,courtID,imagesHash,creditParamsHash,price,reward,wasteType,quantity,score,resourceCoinChange,comment);
    this.timestamp = timestamp.getValue();
    this.disposeOrderID = disposeOrderID.getValue();
    this.orderID = orderID.getValue();
    this.userID = userID.getValue();
    this.deviceID = deviceID.getValue();
    this.binID = binID.getValue();
    this.courtID = courtID.getValue();
    this.imagesHash = imagesHash.getValue();
    this.creditParamsHash = creditParamsHash.getValue();
    this.price = price.getValue();
    this.reward = reward.getValue();
    this.wasteType = wasteType.getValue();
    this.quantity = quantity.getValue();
    this.score = score.getValue();
    this.resourceCoinChange = resourceCoinChange.getValue();
    this.comment = comment.getValue();
  }

  public UpdateItem(BigInteger timestamp, String disposeOrderID, String orderID, String userID,
      String deviceID, String binID, String courtID, byte[] imagesHash, byte[] creditParamsHash,
      BigInteger price, BigInteger reward, String wasteType, BigInteger quantity, BigInteger score,
      BigInteger resourceCoinChange, String comment) {
    super(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(timestamp),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(disposeOrderID),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(orderID),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(userID),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(deviceID),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(binID),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(courtID),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32(imagesHash),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32(creditParamsHash),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(price),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(reward),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(wasteType),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(quantity),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(score),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Int256(resourceCoinChange),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(comment));
    this.timestamp = timestamp;
    this.disposeOrderID = disposeOrderID;
    this.orderID = orderID;
    this.userID = userID;
    this.deviceID = deviceID;
    this.binID = binID;
    this.courtID = courtID;
    this.imagesHash = imagesHash;
    this.creditParamsHash = creditParamsHash;
    this.price = price;
    this.reward = reward;
    this.wasteType = wasteType;
    this.quantity = quantity;
    this.score = score;
    this.resourceCoinChange = resourceCoinChange;
    this.comment = comment;
  }

  /**
   * 从ArrayList构造UpdateItem对象
   * @param list 包含UpdateItem字段的ArrayList
   * @return UpdateItem对象，如果构造失败返回null
   */
  public static UpdateItem fromArrayList(java.util.ArrayList<?> list) {
    if (list == null || list.size() < 16) {
      return null;
    }
    
    try {
      return new UpdateItem(
        (BigInteger) list.get(0),  // timestamp
        (String) list.get(1),     // disposeOrderID
        (String) list.get(2),     // orderID
        (String) list.get(3),     // userID
        (String) list.get(4),     // deviceID
        (String) list.get(5),     // binID
        (String) list.get(6),     // courtID
        (byte[]) list.get(7),     // imagesHash
        (byte[]) list.get(8),     // creditParamsHash
        (BigInteger) list.get(9), // price
        (BigInteger) list.get(10), // reward
        (String) list.get(11),    // wasteType
        (BigInteger) list.get(12), // quantity
        (BigInteger) list.get(13), // score
        (BigInteger) list.get(14), // resourceCoinChange
        (String) list.get(15)     // comment
      );
    } catch (Exception e) {
      return null;
    }
  }
}
