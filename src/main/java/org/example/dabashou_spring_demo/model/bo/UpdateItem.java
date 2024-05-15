package org.example.dabashou_spring_demo.model.bo;

import java.lang.String;
import java.math.BigInteger;
import org.fisco.bcos.sdk.v3.codec.datatypes.DynamicStruct;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;

public class UpdateItem extends DynamicStruct {
  public BigInteger timestamp;

  public String orderID;

  public String userID;

  public String deviceID;

  public String binID;

  public String courtID;

  public byte[] imagesHash;

  public byte[] creditParamsHash;

  public String creditAlgoID;

  public String wasteType;

  public BigInteger quantity;

  public BigInteger score;

  public BigInteger resourceCoin;

  public BigInteger version;

  public String comment;

  public UpdateItem(Uint256 timestamp, Utf8String orderID, Utf8String userID, Utf8String deviceID,
      Utf8String binID, Utf8String courtID, Bytes32 imagesHash, Bytes32 creditParamsHash,
      Utf8String creditAlgoID, Utf8String wasteType, Uint256 quantity, Uint256 score,
      Uint256 resourceCoin, Uint256 version, Utf8String comment) {
    super(timestamp,orderID,userID,deviceID,binID,courtID,imagesHash,creditParamsHash,creditAlgoID,wasteType,quantity,score,resourceCoin,version,comment);
    this.timestamp = timestamp.getValue();
    this.orderID = orderID.getValue();
    this.userID = userID.getValue();
    this.deviceID = deviceID.getValue();
    this.binID = binID.getValue();
    this.courtID = courtID.getValue();
    this.imagesHash = imagesHash.getValue();
    this.creditParamsHash = creditParamsHash.getValue();
    this.creditAlgoID = creditAlgoID.getValue();
    this.wasteType = wasteType.getValue();
    this.quantity = quantity.getValue();
    this.score = score.getValue();
    this.resourceCoin = resourceCoin.getValue();
    this.version = version.getValue();
    this.comment = comment.getValue();
  }

  public UpdateItem(BigInteger timestamp, String orderID, String userID, String deviceID,
      String binID, String courtID, byte[] imagesHash, byte[] creditParamsHash, String creditAlgoID,
      String wasteType, BigInteger quantity, BigInteger score, BigInteger resourceCoin,
      BigInteger version, String comment) {
    super(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(timestamp),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(orderID),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(userID),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(deviceID),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(binID),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(courtID),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32(imagesHash),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32(creditParamsHash),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(creditAlgoID),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(wasteType),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(quantity),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(score),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(resourceCoin),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(version),new org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String(comment));
    this.timestamp = timestamp;
    this.orderID = orderID;
    this.userID = userID;
    this.deviceID = deviceID;
    this.binID = binID;
    this.courtID = courtID;
    this.imagesHash = imagesHash;
    this.creditParamsHash = creditParamsHash;
    this.creditAlgoID = creditAlgoID;
    this.wasteType = wasteType;
    this.quantity = quantity;
    this.score = score;
    this.resourceCoin = resourceCoin;
    this.version = version;
    this.comment = comment;
  }
}
