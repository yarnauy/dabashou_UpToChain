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
public class WasteManagerRegisterInputBO {
  private BigInteger timestamp;

  private String algoID;

  private byte[] algoHash;

  private String comments;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(timestamp);
    args.add(algoID);
    args.add(algoHash);
    args.add(comments);
    return args;
  }
}
