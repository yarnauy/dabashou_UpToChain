package org.example.dabashou_spring_demo.service;

import java.lang.Exception;
import java.lang.String;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dabashou_spring_demo.constants.ContractConstants;
import org.example.dabashou_spring_demo.model.bo.EvidenceDataApproveEvidenceInputBO;
import org.example.dabashou_spring_demo.model.bo.EvidenceDataCreateEvidenceInputBO;
import org.example.dabashou_spring_demo.model.bo.EvidenceDataGetApprovalsInputBO;
import org.example.dabashou_spring_demo.model.bo.EvidenceDataGetEvidenceInputBO;
import org.example.dabashou_spring_demo.model.bo.EvidenceDataGetHashInputBO;
import org.example.dabashou_spring_demo.model.bo.EvidenceDataGetModeInputBO;
import org.example.dabashou_spring_demo.model.bo.EvidenceDataIsApprovedInputBO;
import org.example.dabashou_spring_demo.model.bo.EvidenceDataIsExistInputBO;
import org.example.dabashou_spring_demo.model.bo.EvidenceDataVerifyEvidenceInputBO;
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
public class EvidenceDataService {
  @Value("${contract.evidenceDataAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public CallResponse getEvidence(EvidenceDataGetEvidenceInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.EvidenceDataAbi, "getEvidence", input.toArgs());
  }

  public TransactionResponse createEvidence(EvidenceDataCreateEvidenceInputBO input) throws
      Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.EvidenceDataAbi, "createEvidence", input.toArgs());
  }

  public CallResponse getMode(EvidenceDataGetModeInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.EvidenceDataAbi, "getMode", input.toArgs());
  }

  public CallResponse getHash(EvidenceDataGetHashInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.EvidenceDataAbi, "getHash", input.toArgs());
  }

  public CallResponse verifyEvidence(EvidenceDataVerifyEvidenceInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.EvidenceDataAbi, "verifyEvidence", input.toArgs());
  }

  public CallResponse isApproved(EvidenceDataIsApprovedInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.EvidenceDataAbi, "isApproved", input.toArgs());
  }

  public CallResponse isExist(EvidenceDataIsExistInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.EvidenceDataAbi, "isExist", input.toArgs());
  }

  public CallResponse getApprovals(EvidenceDataGetApprovalsInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.EvidenceDataAbi, "getApprovals", input.toArgs());
  }

  public TransactionResponse approveEvidence(EvidenceDataApproveEvidenceInputBO input) throws
      Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.EvidenceDataAbi, "approveEvidence", input.toArgs());
  }
}
