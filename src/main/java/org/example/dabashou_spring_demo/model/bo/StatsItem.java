package org.example.dabashou_spring_demo.model.bo;

import java.math.BigInteger;
import org.fisco.bcos.sdk.v3.codec.datatypes.StaticStruct;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;

public  class StatsItem extends StaticStruct {
  public BigInteger month;

  public BigInteger wasteSum;

  public BigInteger rewardSum;

  public BigInteger contractSum;

  public BigInteger blockNum;

  public StatsItem(Uint256 month, Uint256 wasteSum, Uint256 rewardSum, Uint256 contractSum,
      Uint256 blockNum) {
    super(month,wasteSum,rewardSum,contractSum,blockNum);
    this.month = month.getValue();
    this.wasteSum = wasteSum.getValue();
    this.rewardSum = rewardSum.getValue();
    this.contractSum = contractSum.getValue();
    this.blockNum = blockNum.getValue();
  }

  public StatsItem(BigInteger month, BigInteger wasteSum, BigInteger rewardSum,
      BigInteger contractSum, BigInteger blockNum) {
    super(new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(month),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(wasteSum),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(rewardSum),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(contractSum),new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(blockNum));
    this.month = month;
    this.wasteSum = wasteSum;
    this.rewardSum = rewardSum;
    this.contractSum = contractSum;
    this.blockNum = blockNum;
  }
}
