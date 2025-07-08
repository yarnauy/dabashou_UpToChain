package org.example.dabashou_spring_demo.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractManagerGetContractInfoInputBO {
  private String contractID;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(contractID);
    return args;
  }
}
