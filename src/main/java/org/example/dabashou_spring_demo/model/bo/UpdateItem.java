package org.example.dabashou_spring_demo.model.bo;

import org.fisco.bcos.sdk.v3.codec.datatypes.DynamicStruct;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Int256;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;

import java.math.BigInteger;

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

  public String creditAlgoID;

  public String wasteType;

  public BigInteger quantity;

  public BigInteger score;

  public BigInteger resourceCoinChange;

  public String comment;

  public UpdateItem(Uint256 timestamp, Utf8String disposeOrderID, Utf8String orderID,
      Utf8String userID, Utf8String deviceID, Utf8String binID, Utf8String courtID,
      Bytes32 imagesHash, Bytes32 creditParamsHash, Utf8String creditAlgoID, Utf8String wasteType,
      Uint256 quantity, Uint256 score, Int256 resourceCoinChange, Utf8String comment) {
    super(timestamp,disposeOrderID,orderID,userID,deviceID,binID,courtID,imagesHash,creditParamsHash,creditAlgoID,wasteType,quantity,score,resourceCoinChange,comment);
    this.timestamp = timestamp.getValue();
    this.disposeOrderID = disposeOrderID.getValue();
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
    this.resourceCoinChange = resourceCoinChange.getValue();
    this.comment = comment.getValue();
  }

  public UpdateItem(BigInteger timestamp, String disposeOrderID, String orderID, String userID,
      String deviceID, String binID, String courtID, byte[] imagesHash, byte[] creditParamsHash,
      String creditAlgoID, String wasteType, BigInteger quantity, BigInteger score,
      BigInteger resourceCoinChange, String comment) {
    super(new Uint256(timestamp),new Utf8String(disposeOrderID),new Utf8String(orderID),new Utf8String(userID),new Utf8String(deviceID),new Utf8String(binID),new Utf8String(courtID),new Bytes32(imagesHash),new Bytes32(creditParamsHash),new Utf8String(creditAlgoID),new Utf8String(wasteType),new Uint256(quantity),new Uint256(score),new Int256(resourceCoinChange),new Utf8String(comment));
    this.timestamp = timestamp;
    this.disposeOrderID = disposeOrderID;
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
    this.resourceCoinChange = resourceCoinChange;
    this.comment = comment;
  }
}
