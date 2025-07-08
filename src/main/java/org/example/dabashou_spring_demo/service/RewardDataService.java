package org.example.dabashou_spring_demo.service;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dabashou_spring_demo.constants.ContractConstants;
import org.example.dabashou_spring_demo.model.bo.RewardDataGetDisposeRewardByOrderIDInputBO;
import org.example.dabashou_spring_demo.model.bo.RewardDataGetPropertyRewardByOrderIDInputBO;
import org.example.dabashou_spring_demo.model.bo.RewardDataInsertDisposeRewardInputBO;
import org.example.dabashou_spring_demo.model.bo.RewardDataInsertPropertyRewardInputBO;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.v3.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.v3.transaction.model.dto.CallResponse;
import org.fisco.bcos.sdk.v3.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
@NoArgsConstructor
@Data
public class RewardDataService {
  @Value("${contract.rewardDataAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public CallResponse getPropertyRewardTable() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.RewardDataAbi, "getPropertyRewardTable", Arrays.asList());
  }

  public CallResponse getDisposeRewardByOrderID(RewardDataGetDisposeRewardByOrderIDInputBO input)
      throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.RewardDataAbi, "getDisposeRewardByOrderID", input.toArgs());
  }

  public CallResponse getDisposeRewardTable() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.RewardDataAbi, "getDisposeRewardTable", Arrays.asList());
  }

  public TransactionResponse insertPropertyReward(RewardDataInsertPropertyRewardInputBO input)
      throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.RewardDataAbi, "insertPropertyReward", input.toArgs());
  }

  public CallResponse getPropertyRewardByOrderID(RewardDataGetPropertyRewardByOrderIDInputBO input)
      throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.RewardDataAbi, "getPropertyRewardByOrderID", input.toArgs());
  }

  public TransactionResponse insertDisposeReward(RewardDataInsertDisposeRewardInputBO input) throws
      Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.RewardDataAbi, "insertDisposeReward", input.toArgs());
  }
}
