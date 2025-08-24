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
public class WasteManagerSettleInputBO {
  private BigInteger timestamp;

  private String targerOrderID;

  private String orderID;

  private String toAddress;

  private BigInteger amount;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(timestamp);
    args.add(targerOrderID);
    args.add(orderID);
    args.add(toAddress);
    args.add(amount);
    return args;
  }
}
