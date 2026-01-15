package org.example.dabashou_spring_demo.controller;

import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.client.exceptions.ClientException;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("chain")
public class ChainController {
    
    @Autowired
    private Client client;

    /**
     * 测试方法：使用交易哈希获取回执
     *
     * @param withProof 是否返回默克尔树证明
     * @return 交易回执信息
     */
    @GetMapping("getTransactionReceiptTest")
    public TransactionReceipt getTransactionReceiptTest(
            @RequestParam(required = false, defaultValue = "false") Boolean withProof) throws Exception {
        String txHash = "0x3e2b4025812d2adebe94508b7fca1454f4af756e8384926cf8bc5139e51d3f6c";

        try {
            TransactionReceipt receipt = client.getTransactionReceipt(txHash, withProof).getTransactionReceipt();
            if (receipt == null) {
                throw new Exception("交易回执为空，交易哈希: " + txHash);
            }
            return receipt;
        } catch (ClientException e) {
            throw new Exception("获取交易回执失败: " + e.getMessage() + "，交易哈希: " + txHash);
        }
    }
}

