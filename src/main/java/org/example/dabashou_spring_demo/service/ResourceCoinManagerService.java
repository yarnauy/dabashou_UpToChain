package org.example.dabashou_spring_demo.service;

import java.lang.Exception;
import java.lang.String;
import java.util.Arrays;
import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dabashou_spring_demo.constants.ContractConstants;
import org.example.dabashou_spring_demo.model.bo.ResourceCoinManagerAllowInputBO;
import org.example.dabashou_spring_demo.model.bo.ResourceCoinManagerBalanceOfInputBO;
import org.example.dabashou_spring_demo.model.bo.ResourceCoinManagerDenyInputBO;
import org.example.dabashou_spring_demo.model.bo.ResourceCoinManagerGetAddressByUserIDInputBO;
import org.example.dabashou_spring_demo.model.bo.ResourceCoinManagerIssueEvidenceInputBO;
import org.example.dabashou_spring_demo.model.bo.ResourceCoinManagerRegisterAccountInputBO;
import org.example.dabashou_spring_demo.model.bo.ResourceCoinManagerSetWithdrawAccountInputBO;
import org.example.dabashou_spring_demo.model.bo.ResourceCoinManagerTransferEvidenceInputBO;
import org.example.dabashou_spring_demo.model.bo.ResourceCoinManagerUpdateAccountInputBO;
import org.example.dabashou_spring_demo.model.bo.ResourceCoinManagerWithdrawEvidenceInputBO;
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

  public TransactionResponse transferEvidence(ResourceCoinManagerTransferEvidenceInputBO input)
      throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.ResourceCoinManagerAbi, "transferEvidence", input.toArgs());
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

  public java.math.BigInteger balanceOf(ResourceCoinManagerBalanceOfInputBO input) throws Exception {
    Object result = this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.ResourceCoinManagerAbi, "balanceOf", input.toArgs()).getReturnObject();

    // 如果合约返回的是ArrayList，尝试从嵌套列表中获取BigInteger
    if (result instanceof java.util.ArrayList) {
      java.util.ArrayList<?> resultList = (java.util.ArrayList<?>) result;
      if (!resultList.isEmpty()) {
        Object firstElement = resultList.get(0);

        // 如果第一个元素也是ArrayList，取第一个元素
        if (firstElement instanceof java.util.ArrayList) {
          java.util.ArrayList<?> nestedList = (java.util.ArrayList<?>) firstElement;
          if (!nestedList.isEmpty()) {
            Object nestedFirstElement = nestedList.get(0);
            if (nestedFirstElement instanceof java.math.BigInteger) {
              return (java.math.BigInteger) nestedFirstElement;
            }
          }
        }
        // 如果第一个元素直接是BigInteger
        else if (firstElement instanceof java.math.BigInteger) {
          return (java.math.BigInteger) firstElement;
        }
      }
    }
    // 如果合约返回的是BigInteger对象，直接返回
    else if (result instanceof java.math.BigInteger) {
      return (java.math.BigInteger) result;
    }

    // 否则返回0
    return java.math.BigInteger.ZERO;
  }

  public TransactionResponse withdrawEvidence(ResourceCoinManagerWithdrawEvidenceInputBO input)
      throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.ResourceCoinManagerAbi, "withdrawEvidence", input.toArgs());
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
