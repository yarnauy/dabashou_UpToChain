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
public class EvidenceManagerService {
  @Value("${contract.evidenceManagerAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public CallResponse getEvidence(EvidenceManagerGetEvidenceInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.EvidenceManagerAbi, "getEvidence", input.toArgs());
  }

  public TransactionResponse approveEvidence(EvidenceManagerApproveEvidenceInputBO input) throws
      Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.EvidenceManagerAbi, "approveEvidence", input.toArgs());
  }

  public CallResponse verifyEvidence(EvidenceManagerVerifyEvidenceInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.EvidenceManagerAbi, "verifyEvidence", input.toArgs());
  }

  public CallResponse _evidenceData() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.EvidenceManagerAbi, "_evidenceData", Arrays.asList());
  }

  public CallResponse getApprovals(EvidenceManagerGetApprovalsInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.EvidenceManagerAbi, "getApprovals", input.toArgs());
  }

  public TransactionResponse createEvidence(EvidenceManagerCreateEvidenceInputBO input) throws
      Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.EvidenceManagerAbi, "createEvidence", input.toArgs());
  }
}
