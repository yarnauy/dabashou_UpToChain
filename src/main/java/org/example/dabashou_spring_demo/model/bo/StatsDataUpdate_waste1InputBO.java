package org.example.dabashou_spring_demo.model.bo;

import java.lang.Boolean;
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
public class StatsDataUpdate_waste1InputBO {
  private String month;

  private BigInteger wasteNum;

  private Boolean add;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(month);
    args.add(wasteNum);
    args.add(add);
    return args;
  }
}
