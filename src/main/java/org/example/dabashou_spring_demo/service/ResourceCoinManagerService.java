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
public class ResourceCoinManagerService {
  @Value("${contract.resourceCoinManagerAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public CallResponse _owner() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.ResourceCoinManagerAbi, "_owner", Arrays.asList());
  }

  public TransactionResponse updateAccount(ResourceCoinManagerUpdateAccountInputBO input) throws
      Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.ResourceCoinManagerAbi, "updateAccount", input.toArgs());
  }

  public TransactionResponse setWithdrawAccount(ResourceCoinManagerSetWithdrawAccountInputBO input)
      throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.ResourceCoinManagerAbi, "setWithdrawAccount", input.toArgs());
  }

  public CallResponse withdrawAccount() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.ResourceCoinManagerAbi, "withdrawAccount", Arrays.asList());
  }

  public TransactionResponse registerAccount(ResourceCoinManagerRegisterAccountInputBO input) throws
      Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.ResourceCoinManagerAbi, "registerAccount", input.toArgs());
  }

  public TransactionResponse deny(ResourceCoinManagerDenyInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.ResourceCoinManagerAbi, "deny", input.toArgs());
  }

  public TransactionResponse issueEvidence(ResourceCoinManagerIssueEvidenceInputBO input) throws
      Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.ResourceCoinManagerAbi, "issueEvidence", input.toArgs());
  }

  public CallResponse getAddressByUserID(ResourceCoinManagerGetAddressByUserIDInputBO input) throws
      Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.ResourceCoinManagerAbi, "getAddressByUserID", input.toArgs());
  }

  public TransactionResponse allow(ResourceCoinManagerAllowInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.ResourceCoinManagerAbi, "allow", input.toArgs());
  }

  public CallResponse balanceOf(ResourceCoinManagerBalanceOfInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.ResourceCoinManagerAbi, "balanceOf", input.toArgs());
  }

  public TransactionResponse withdrawEvidence(ResourceCoinManagerWithdrawEvidenceInputBO input)
      throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.ResourceCoinManagerAbi, "withdrawEvidence", input.toArgs());
  }

  public TransactionResponse transferEvidence(ResourceCoinManagerTransferEvidenceInputBO input)
      throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.ResourceCoinManagerAbi, "transferEvidence", input.toArgs());
  }

  public static class Keypair {
    public String address;
    public String privateKey;
    public Keypair(String address, String privateKey) {
      this.address = address;
      this.privateKey = privateKey;
    }
  }

  public Keypair generateKeypair() {
    org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair keyPair = this.client.getCryptoSuite().generateRandomKeyPair();
    return new Keypair(keyPair.getAddress(), keyPair.getHexPrivateKey());
  }
}
