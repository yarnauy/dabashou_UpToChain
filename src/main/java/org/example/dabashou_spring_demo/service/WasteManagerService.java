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
import org.example.dabashou_spring_demo.model.bo.WasteManagerDisposeRewardInputBO;
import org.example.dabashou_spring_demo.model.bo.WasteManagerGetDisposeInputBO;
import org.example.dabashou_spring_demo.model.bo.WasteManagerGetDisposeRewardByOrderIDInputBO;
import org.example.dabashou_spring_demo.model.bo.WasteManagerGetPropertyRewardByOrderIDInputBO;
import org.example.dabashou_spring_demo.model.bo.WasteManagerGetUpdateInputBO;
import org.example.dabashou_spring_demo.model.bo.WasteManagerPropertyRewardInputBO;
import org.example.dabashou_spring_demo.model.bo.WasteManagerRegisterInputBO;
import org.example.dabashou_spring_demo.model.bo.WasteManagerSelectAlgoInputBO;
import org.example.dabashou_spring_demo.model.bo.WasteManagerSettleInputBO;
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

  public CallResponse _owner() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.WasteManagerAbi, "_owner", Arrays.asList());
  }

  public org.example.dabashou_spring_demo.model.bo.DisposeRewardItem getDisposeRewardByOrderID(WasteManagerGetDisposeRewardByOrderIDInputBO input)
      throws Exception {
    Object result = this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.WasteManagerAbi, "getDisposeRewardByOrderID", input.toArgs()).getReturnObject();
    
    // 如果合约返回的是ArrayList，尝试从嵌套列表中构造DisposeRewardItem
    if (result instanceof java.util.ArrayList) {
      java.util.ArrayList<?> resultList = (java.util.ArrayList<?>) result;
      if (!resultList.isEmpty()) {
        Object firstElement = resultList.get(0);
        
        // 如果第一个元素也是ArrayList，使用静态方法构造DisposeRewardItem
        if (firstElement instanceof java.util.ArrayList) {
          java.util.ArrayList<?> nestedList = (java.util.ArrayList<?>) firstElement;
          return org.example.dabashou_spring_demo.model.bo.DisposeRewardItem.fromArrayList(nestedList);
        }
        // 如果第一个元素直接是DisposeRewardItem
        else if (firstElement instanceof org.example.dabashou_spring_demo.model.bo.DisposeRewardItem) {
          return (org.example.dabashou_spring_demo.model.bo.DisposeRewardItem) firstElement;
        }
      }
    }
    // 如果合约返回的是DisposeRewardItem对象，直接返回
    else if (result instanceof org.example.dabashou_spring_demo.model.bo.DisposeRewardItem) {
      return (org.example.dabashou_spring_demo.model.bo.DisposeRewardItem) result;
    }
    
    // 否则返回null
    return null;
  }

  public TransactionResponse dispose(WasteManagerDisposeInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.WasteManagerAbi, "dispose", input.toArgs());
  }

  public TransactionResponse timestampToTotalMonth(WasteManagerTimestampToTotalMonthInputBO input)
      throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.WasteManagerAbi, "timestampToTotalMonth", input.toArgs());
  }

  public org.example.dabashou_spring_demo.model.bo.UpdateItem getUpdate(WasteManagerGetUpdateInputBO input) throws Exception {
    Object result = this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.WasteManagerAbi, "getUpdate", input.toArgs()).getReturnObject();
    
    // 如果合约返回的是ArrayList，尝试从嵌套列表中构造UpdateItem
    if (result instanceof java.util.ArrayList) {
      java.util.ArrayList<?> resultList = (java.util.ArrayList<?>) result;
      if (!resultList.isEmpty()) {
        Object firstElement = resultList.get(0);
        
        // 如果第一个元素也是ArrayList，使用静态方法构造UpdateItem
        if (firstElement instanceof java.util.ArrayList) {
          java.util.ArrayList<?> nestedList = (java.util.ArrayList<?>) firstElement;
          return org.example.dabashou_spring_demo.model.bo.UpdateItem.fromArrayList(nestedList);
        }
        // 如果第一个元素直接是UpdateItem
        else if (firstElement instanceof org.example.dabashou_spring_demo.model.bo.UpdateItem) {
          return (org.example.dabashou_spring_demo.model.bo.UpdateItem) firstElement;
        }
      }
    }
    // 如果合约返回的是UpdateItem对象，直接返回
    else if (result instanceof org.example.dabashou_spring_demo.model.bo.UpdateItem) {
      return (org.example.dabashou_spring_demo.model.bo.UpdateItem) result;
    }
    
    // 否则返回null
    return null;
  }

  public TransactionResponse deny(WasteManagerDenyInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.WasteManagerAbi, "deny", input.toArgs());
  }

  public TransactionResponse settle(WasteManagerSettleInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.WasteManagerAbi, "settle", input.toArgs());
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

  public TransactionResponse update(WasteManagerUpdateInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.WasteManagerAbi, "update", input.toArgs());
  }

  public CallResponse selectAlgo(WasteManagerSelectAlgoInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.WasteManagerAbi, "selectAlgo", input.toArgs());
  }

  public org.example.dabashou_spring_demo.model.bo.DisposeItem getDispose(WasteManagerGetDisposeInputBO input) throws Exception {
    Object result = this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.WasteManagerAbi, "getDispose", input.toArgs()).getReturnObject();
    
    // 如果合约返回的是ArrayList，尝试从嵌套列表中构造DisposeItem
    if (result instanceof java.util.ArrayList) {
      java.util.ArrayList<?> resultList = (java.util.ArrayList<?>) result;
      if (!resultList.isEmpty()) {
        Object firstElement = resultList.get(0);
        
        // 如果第一个元素也是ArrayList，使用静态方法构造DisposeItem
        if (firstElement instanceof java.util.ArrayList) {
          java.util.ArrayList<?> nestedList = (java.util.ArrayList<?>) firstElement;
          return org.example.dabashou_spring_demo.model.bo.DisposeItem.fromArrayList(nestedList);
        }
        // 如果第一个元素直接是DisposeItem
        else if (firstElement instanceof org.example.dabashou_spring_demo.model.bo.DisposeItem) {
          return (org.example.dabashou_spring_demo.model.bo.DisposeItem) firstElement;
        }
      }
    }
    // 如果合约返回的是DisposeItem对象，直接返回
    else if (result instanceof org.example.dabashou_spring_demo.model.bo.DisposeItem) {
      return (org.example.dabashou_spring_demo.model.bo.DisposeItem) result;
    }
    
    // 否则返回null
    return null;
  }

  public org.example.dabashou_spring_demo.model.bo.PropertyRewardItem getPropertyRewardByOrderID(
      WasteManagerGetPropertyRewardByOrderIDInputBO input) throws Exception {
    Object result = this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.WasteManagerAbi, "getPropertyRewardByOrderID", input.toArgs()).getReturnObject();
    
    // 如果合约返回的是ArrayList，尝试从嵌套列表中构造PropertyRewardItem
    if (result instanceof java.util.ArrayList) {
      java.util.ArrayList<?> resultList = (java.util.ArrayList<?>) result;
      if (!resultList.isEmpty()) {
        Object firstElement = resultList.get(0);
        
        // 如果第一个元素也是ArrayList，使用静态方法构造PropertyRewardItem
        if (firstElement instanceof java.util.ArrayList) {
          java.util.ArrayList<?> nestedList = (java.util.ArrayList<?>) firstElement;
          return org.example.dabashou_spring_demo.model.bo.PropertyRewardItem.fromArrayList(nestedList);
        }
        // 如果第一个元素直接是PropertyRewardItem
        else if (firstElement instanceof org.example.dabashou_spring_demo.model.bo.PropertyRewardItem) {
          return (org.example.dabashou_spring_demo.model.bo.PropertyRewardItem) firstElement;
        }
      }
    }
    // 如果合约返回的是PropertyRewardItem对象，直接返回
    else if (result instanceof org.example.dabashou_spring_demo.model.bo.PropertyRewardItem) {
      return (org.example.dabashou_spring_demo.model.bo.PropertyRewardItem) result;
    }
    
    // 否则返回null
    return null;
  }

  public CallResponse rewardAccount() throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.WasteManagerAbi, "rewardAccount", Arrays.asList());
  }
}
