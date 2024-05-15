package org.example.dabashou_spring_demo.model.bo;

import java.lang.Object;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatsDataSelectInputBO {
  private BigInteger idLow;

  private BigInteger idHigh;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(idLow);
    args.add(idHigh);
    return args;
  }
}
