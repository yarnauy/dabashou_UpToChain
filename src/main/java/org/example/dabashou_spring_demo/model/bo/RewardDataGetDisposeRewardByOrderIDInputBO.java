package org.example.dabashou_spring_demo.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RewardDataGetDisposeRewardByOrderIDInputBO {
  private String orderID;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(orderID);
    return args;
  }
}
