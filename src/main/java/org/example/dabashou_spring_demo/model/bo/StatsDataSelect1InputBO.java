package org.example.dabashou_spring_demo.model.bo;

import java.lang.Object;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatsDataSelect1InputBO {
  private String month;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(month);
    return args;
  }
}
