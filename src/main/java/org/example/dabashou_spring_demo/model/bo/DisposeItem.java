package org.example.dabashou_spring_demo.model.bo;

import org.fisco.bcos.sdk.v3.codec.datatypes.Address;
import org.fisco.bcos.sdk.v3.codec.datatypes.DynamicStruct;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;

import java.math.BigInteger;

public  class DisposeItem extends DynamicStruct {
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

  public String receiverAddress;

  public DisposeItem(Uint256 timestamp, Utf8String orderID, Utf8String userID, Utf8String deviceID,
      Utf8String binID, Utf8String courtID, Bytes32 imagesHash, Bytes32 creditParamsHash,
      Utf8String creditAlgoID, Utf8String wasteType, Uint256 quantity, Uint256 score,
      Uint256 resourceCoin, Address receiverAddress) {
    super(timestamp,orderID,userID,deviceID,binID,courtID,imagesHash,creditParamsHash,creditAlgoID,wasteType,quantity,score,resourceCoin,receiverAddress);
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
    this.receiverAddress = receiverAddress.getValue();
  }

  public DisposeItem(BigInteger timestamp, String orderID, String userID, String deviceID,
      String binID, String courtID, byte[] imagesHash, byte[] creditParamsHash, String creditAlgoID,
      String wasteType, BigInteger quantity, BigInteger score, BigInteger resourceCoin,
      String receiverAddress) {
    super(new Uint256(timestamp),new Utf8String(orderID),new Utf8String(userID),new Utf8String(deviceID),new Utf8String(binID),new Utf8String(courtID),new Bytes32(imagesHash),new Bytes32(creditParamsHash),new Utf8String(creditAlgoID),new Utf8String(wasteType),new Uint256(quantity),new Uint256(score),new Uint256(resourceCoin),new Address(receiverAddress));
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
    this.receiverAddress = receiverAddress;
  }
}
