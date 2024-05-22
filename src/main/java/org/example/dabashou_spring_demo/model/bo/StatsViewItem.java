package org.example.dabashou_spring_demo.model.bo;

import org.fisco.bcos.sdk.v3.codec.datatypes.StaticStruct;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;

import java.math.BigInteger;

public  class StatsViewItem  {
  public String date;

  public BigInteger wasteSum;

  public BigInteger rewardSum;

  public BigInteger contractSum;

  public BigInteger total;

  public String  blockHash;

  public StatsViewItem(String date, Uint256 wasteSum, Uint256 rewardSum, Uint256 contractSum,
                       String blockHash) {
    this.date = date;
    this.wasteSum = wasteSum.getValue();
    this.rewardSum = rewardSum.getValue();
    this.contractSum = contractSum.getValue();
    this.total = this.wasteSum.add(this.rewardSum.add(this.contractSum));
    this.blockHash = blockHash;
  }

  public StatsViewItem(String date, BigInteger wasteSum, BigInteger rewardSum,
                       BigInteger contractSum, String blockHash) {

    this.date = date;
    this.wasteSum = wasteSum;
    this.rewardSum = rewardSum;
    this.contractSum = contractSum;
    this.total = this.wasteSum.add(this.rewardSum.add(this.contractSum));
    this.blockHash = blockHash;
  }
}
