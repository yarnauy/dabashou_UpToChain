package org.example.dabashou_spring_demo.service;

import java.lang.Exception;
import java.lang.String;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dabashou_spring_demo.constants.ContractConstants;
import org.example.dabashou_spring_demo.model.bo.ContractManagerAdvanceContractInputBO;
import org.example.dabashou_spring_demo.model.bo.ContractManagerAllowInputBO;
import org.example.dabashou_spring_demo.model.bo.ContractManagerDenyInputBO;
import org.example.dabashou_spring_demo.model.bo.ContractManagerGetContractInfoInputBO;
import org.example.dabashou_spring_demo.model.bo.ContractManagerInitContractInputBO;
import org.example.dabashou_spring_demo.model.bo.ContractManagerSignContractInputBO;
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
public class ContractManagerService {
  @Value("${contract.contractManagerAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public org.example.dabashou_spring_demo.model.bo.ContractResponse getContractInfo(ContractManagerGetContractInfoInputBO input) throws
      Exception {
    Object result = this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.ContractManagerAbi, "getContractInfo", input.toArgs()).getReturnObject();
    
    // 如果合约返回的是ArrayList，尝试从列表中构造ContractResponse
    if (result instanceof java.util.ArrayList) {
      java.util.ArrayList<?> resultList = (java.util.ArrayList<?>) result;
      return org.example.dabashou_spring_demo.model.bo.ContractResponse.fromArrayList(resultList);
    }
    
    // 否则返回null
    return null;
  }

  public CallResponse _owner() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.ContractManagerAbi, "_owner", Arrays.asList());
  }

  public TransactionResponse signContract(ContractManagerSignContractInputBO input) throws
      Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.ContractManagerAbi, "signContract", input.toArgs());
  }

  public CallResponse _contractData() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.ContractManagerAbi, "_contractData", Arrays.asList());
  }

  public TransactionResponse initContract(ContractManagerInitContractInputBO input) throws
      Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.ContractManagerAbi, "initContract", input.toArgs());
  }

  public TransactionResponse advanceContract(ContractManagerAdvanceContractInputBO input) throws
      Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.ContractManagerAbi, "advanceContract", input.toArgs());
  }

  public TransactionResponse deny(ContractManagerDenyInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.ContractManagerAbi, "deny", input.toArgs());
  }

  public TransactionResponse allow(ContractManagerAllowInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.ContractManagerAbi, "allow", input.toArgs());
  }
}
