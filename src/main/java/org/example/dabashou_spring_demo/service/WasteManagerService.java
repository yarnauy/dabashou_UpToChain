package org.example.dabashou_spring_demo.service;

import java.lang.Exception;
import java.lang.String;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dabashou_spring_demo.constants.ContractConstants;
import org.example.dabashou_spring_demo.model.bo.WasteManagerAllowInputBO;
import org.example.dabashou_spring_demo.model.bo.WasteManagerDenyInputBO;
import org.example.dabashou_spring_demo.model.bo.WasteManagerDisposeInputBO;
import org.example.dabashou_spring_demo.model.bo.WasteManagerIssueRewardInputBO;
import org.example.dabashou_spring_demo.model.bo.WasteManagerSelect1InputBO;
import org.example.dabashou_spring_demo.model.bo.WasteManagerSelectInputBO;
import org.example.dabashou_spring_demo.model.bo.WasteManagerTimestampToTotalMonthInputBO;
import org.example.dabashou_spring_demo.model.bo.WasteManagerUpdateInputBO;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.v3.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.v3.transaction.model.dto.CallResponse;
import org.fisco.bcos.sdk.v3.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Data
public class WasteManagerService {
  @Value("${contract.wasteManagerAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public CallResponse select(WasteManagerSelectInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.WasteManagerAbi, "select", input.toArgs());
  }

  public CallResponse _owner() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.WasteManagerAbi, "_owner", Arrays.asList());
  }

  public TransactionResponse dispose(WasteManagerDisposeInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.WasteManagerAbi, "dispose", input.toArgs());
  }

  public TransactionResponse update(WasteManagerUpdateInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.WasteManagerAbi, "update", input.toArgs());
  }

  public CallResponse select(WasteManagerSelect1InputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.WasteManagerAbi, "select", input.toArgs());
  }

  public TransactionResponse timestampToTotalMonth(WasteManagerTimestampToTotalMonthInputBO input)
      throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.WasteManagerAbi, "timestampToTotalMonth", input.toArgs());
  }

  public CallResponse _rewardData() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.WasteManagerAbi, "_rewardData", Arrays.asList());
  }

  public TransactionResponse deny(WasteManagerDenyInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.WasteManagerAbi, "deny", input.toArgs());
  }

  public TransactionResponse issueReward(WasteManagerIssueRewardInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.WasteManagerAbi, "issueReward", input.toArgs());
  }

  public TransactionResponse allow(WasteManagerAllowInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.WasteManagerAbi, "allow", input.toArgs());
  }

  public CallResponse _wasteData() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.WasteManagerAbi, "_wasteData", Arrays.asList());
  }

  public CallResponse _statData() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.WasteManagerAbi, "_statData", Arrays.asList());
  }
}
