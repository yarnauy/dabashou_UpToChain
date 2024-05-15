package org.example.dabashou_spring_demo.config;

import java.lang.String;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(
    prefix = "system"
)
public class SystemConfig {
  private String groupId = "1";

  private String hexPrivateKey;
}
