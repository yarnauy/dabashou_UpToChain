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
public class EvidenceManagerVerifyEvidenceInputBO {
  private String evidenceID;

  private byte[] expectedHash;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(evidenceID);
    args.add(expectedHash);
    return args;
  }
}
