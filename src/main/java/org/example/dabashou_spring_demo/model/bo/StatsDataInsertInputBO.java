package org.example.dabashou_spring_demo.model.bo;

import java.lang.Object;
import java.lang.String;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatsDataInsertInputBO {
  private String month;

  private BigInteger wasteSum;

  private BigInteger rewardSum;

  private BigInteger contractSum;

  private BigInteger blockNum;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(month);
    args.add(wasteSum);
    args.add(rewardSum);
    args.add(contractSum);
    args.add(blockNum);
    return args;
  }
}
