package org.example.dabashou_spring_demo.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RewardDataInsertPropertyRewardInputBO {
  private PropertyRewardItem item;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(item);
    return args;
  }
}
