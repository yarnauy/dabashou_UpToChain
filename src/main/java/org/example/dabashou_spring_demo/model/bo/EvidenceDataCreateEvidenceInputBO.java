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
public class EvidenceDataCreateEvidenceInputBO {
  private BigInteger timestamp;

  private String evidenceID;

  private byte[] contentHash;

  private BigInteger mode;

  private byte[] reserved1;

  private List<String> reserved2;

  private String creator;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(timestamp);
    args.add(evidenceID);
    args.add(contentHash);
    args.add(mode);
    args.add(reserved1);
    args.add(reserved2);
    args.add(creator);
    return args;
  }
}
