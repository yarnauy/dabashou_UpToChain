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
public class EvidenceDataApproveEvidenceInputBO {
  private BigInteger timestamp;

  private String evidenceID;

  private String approver;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(timestamp);
    args.add(evidenceID);
    args.add(approver);
    return args;
  }
}
