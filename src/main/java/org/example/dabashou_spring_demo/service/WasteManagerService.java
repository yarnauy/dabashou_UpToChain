package org.example.dabashou_spring_demo.service;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dabashou_spring_demo.constants.ContractConstants;
import org.example.dabashou_spring_demo.model.bo.*;
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

  public CallResponse _owner() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.WasteManagerAbi, "_owner", Arrays.asList());
  }

  public CallResponse getDisposeRewardByOrderID(WasteManagerGetDisposeRewardByOrderIDInputBO input)
      throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.WasteManagerAbi, "getDisposeRewardByOrderID", input.toArgs());
  }

  public TransactionResponse timestampToTotalMonth(WasteManagerTimestampToTotalMonthInputBO input)
      throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.WasteManagerAbi, "timestampToTotalMonth", input.toArgs());
  }

  public TransactionResponse deny(WasteManagerDenyInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.WasteManagerAbi, "deny", input.toArgs());
  }

  public TransactionResponse settle(WasteManagerSettleInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.WasteManagerAbi, "settle", input.toArgs());
  }

  public CallResponse getDispose(WasteManagerGetDisposeInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.WasteManagerAbi, "getDispose", input.toArgs());
  }

  public TransactionResponse propertyReward(WasteManagerPropertyRewardInputBO input) throws
      Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.WasteManagerAbi, "propertyReward", input.toArgs());
  }

  public CallResponse _wasteData() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.WasteManagerAbi, "_wasteData", Arrays.asList());
  }

  public TransactionResponse register(WasteManagerRegisterInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.WasteManagerAbi, "register", input.toArgs());
  }

  public TransactionResponse dispose(WasteManagerDisposeInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.WasteManagerAbi, "dispose", input.toArgs());
  }

  public TransactionResponse disposeReward(WasteManagerDisposeRewardInputBO input) throws
      Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.WasteManagerAbi, "disposeReward", input.toArgs());
  }

  public CallResponse _rewardData() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.WasteManagerAbi, "_rewardData", Arrays.asList());
  }

  public CallResponse _resouceCoin() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.WasteManagerAbi, "_resouceCoin", Arrays.asList());
  }

  public TransactionResponse allow(WasteManagerAllowInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.WasteManagerAbi, "allow", input.toArgs());
  }

  public CallResponse _algoData() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.WasteManagerAbi, "_algoData", Arrays.asList());
  }

  public CallResponse selectAlgo(WasteManagerSelectAlgoInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.WasteManagerAbi, "selectAlgo", input.toArgs());
  }

  public CallResponse getPropertyRewardByOrderID(
      WasteManagerGetPropertyRewardByOrderIDInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.WasteManagerAbi, "getPropertyRewardByOrderID", input.toArgs());
  }

  public TransactionResponse update(WasteManagerUpdateInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.WasteManagerAbi, "update", input.toArgs());
  }

  public CallResponse rewardAccount() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.WasteManagerAbi, "rewardAccount", Arrays.asList());
  }
}
