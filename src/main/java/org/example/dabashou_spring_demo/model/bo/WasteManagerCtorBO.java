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
public class WasteManagerCtorBO {
  private String resouceCoinAddr;

  private String _rewardAccount;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(resouceCoinAddr);
    args.add(_rewardAccount);
    return args;
  }
}
