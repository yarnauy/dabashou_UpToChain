package org.example.dabashou_spring_demo.service;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dabashou_spring_demo.constants.ContractConstants;
import org.example.dabashou_spring_demo.model.bo.WasteDataInsertInputBO;
import org.example.dabashou_spring_demo.model.bo.WasteDataSelectDisposeByOrderIDInputBO;
import org.example.dabashou_spring_demo.model.bo.WasteDataUpdateInputBO;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.v3.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.v3.transaction.model.dto.CallResponse;
import org.fisco.bcos.sdk.v3.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@NoArgsConstructor
@Data
public class WasteDataService {
  @Value("${contract.wasteDataAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public CallResponse selectDisposeByOrderID(WasteDataSelectDisposeByOrderIDInputBO input) throws
      Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ContractConstants.WasteDataAbi, "selectDisposeByOrderID", input.toArgs());
  }

  public TransactionResponse insert(WasteDataInsertInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.WasteDataAbi, "insert", input.toArgs());
  }

  public TransactionResponse update(WasteDataUpdateInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ContractConstants.WasteDataAbi, "update", input.toArgs());
  }
}
