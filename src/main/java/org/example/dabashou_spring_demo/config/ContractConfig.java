package org.example.dabashou_spring_demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(
    prefix = "contract"
)
public class ContractConfig {
  private String contractDataAddress;

  private String rewardDataAddress;

  private String evidenceDataAddress;

  private String algoDataAddress;

  private String wasteDataAddress;

  private String resourceCoinManagerAddress;

  private String wasteManagerAddress;

  private String evidenceManagerAddress;

  private String contractManagerAddress;
}
