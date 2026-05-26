package org.example.dabashou_spring_demo.model.bo;

import java.lang.String;
import java.math.BigInteger;
import org.fisco.bcos.sdk.v3.codec.datatypes.Address;
import org.fisco.bcos.sdk.v3.codec.datatypes.DynamicStruct;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Int256;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;

public class DisposeItem extends DynamicStruct {
  public BigInteger timestamp;

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

  public BigInteger resourceCoin;

  public String receiverAddress;

  public DisposeItem(Uint256 timestamp, Utf8String orderID, Utf8String userID, Utf8String deviceID,
      Utf8String binID, Utf8String courtID, Bytes32 imagesHash, Bytes32 creditParamsHash,
      Uint256 price, Uint256 reward, Utf8String wasteType, Uint256 quantity, Uint256 score,
      Int256 resourceCoin, Address receiverAddress) {
    super(timestamp,orderID,userID,deviceID,binID,courtID,imagesHash,creditParamsHash,price,reward,wasteType,quantity,score,resourceCoin,receiverAddress);
    this.timestamp = timestamp.getValue();
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
    this.resourceCoin = resourceCoin.getValue();
    this.receiverAddress = receiverAddress.getValue();
  }

  public DisposeItem(BigInteger timestamp, String orderID, String userID, String deviceID,
      String binID, String courtID, byte[] imagesHash, byte[] creditParamsHash, BigInteger price,
      BigInteger reward, String wasteType, BigInteger quantity, BigInteger score,
      BigInteger resourceCoin, String receiverAddress) {
    super(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(timestamp),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(orderID),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(userID),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(deviceID),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(binID),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(courtID),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32(imagesHash),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32(creditParamsHash),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(price),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(reward),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(wasteType),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(quantity),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(score),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Int256(resourceCoin),new org.fisco.bcos.sdk.v3.codec.datatypes.Address(receiverAddress));
    this.timestamp = timestamp;
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
    this.resourceCoin = resourceCoin;
    this.receiverAddress = receiverAddress;
  }

  public static DisposeItem fromArrayList(java.util.ArrayList<?> list) {
    if (list == null || list.size() < 15) {
      return null;
    }

    try {
      return new DisposeItem(
        (BigInteger) list.get(0),
        (String) list.get(1),
        (String) list.get(2),
        (String) list.get(3),
        (String) list.get(4),
        (String) list.get(5),
        (byte[]) list.get(6),
        (byte[]) list.get(7),
        (BigInteger) list.get(8),
        (BigInteger) list.get(9),
        (String) list.get(10),
        (BigInteger) list.get(11),
        (BigInteger) list.get(12),
        (BigInteger) list.get(13),
        (String) list.get(14)
      );
    } catch (Exception e) {
      return null;
    }
  }
}
