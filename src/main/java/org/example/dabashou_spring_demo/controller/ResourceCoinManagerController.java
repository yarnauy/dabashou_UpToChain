package org.example.dabashou_spring_demo.controller;

import org.example.dabashou_spring_demo.model.bo.*;
import org.example.dabashou_spring_demo.service.ResourceCoinManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("resourcecoinmanager")
public class ResourceCoinManagerController {
    @Autowired
    private ResourceCoinManagerService service;

    @GetMapping("registerAccount")
    public Object registerAccount() throws Exception {
        ResourceCoinManagerRegisterAccountInputBO input = new ResourceCoinManagerRegisterAccountInputBO("user111", "0x0000000000000000000000000000000000000001");
        return service.registerAccount(input).getTransactionReceipt().toString();
    }

    @GetMapping("updateAccount")
    public Object updateAccount() throws Exception {
        ResourceCoinManagerUpdateAccountInputBO input = new ResourceCoinManagerUpdateAccountInputBO("user111", "0x0000000000000000000000000000000000000002");
        return service.updateAccount(input).getTransactionReceipt().toString();
    }

    @GetMapping("getAddressByUserID")
    public Object getAddressByUserID() throws Exception {
        ResourceCoinManagerGetAddressByUserIDInputBO input = new ResourceCoinManagerGetAddressByUserIDInputBO("user111");
        return service.getAddressByUserID(input).getReturnObject();
    }

    @GetMapping("balanceOf")
    public Object balanceOf() throws Exception {
        ResourceCoinManagerBalanceOfInputBO input = new ResourceCoinManagerBalanceOfInputBO("0x0000000000000000000000000000000000000001");
        return service.balanceOf(input).getReturnObject();
    }

    @GetMapping("issueEvidence")
    public Object issueEvidence() throws Exception {
        IssueItem item = new IssueItem("order001", "0x0000000000000000000000000000000000000001", java.math.BigInteger.valueOf(100L));
        ResourceCoinManagerIssueEvidenceInputBO input = new ResourceCoinManagerIssueEvidenceInputBO(item);
        return service.issueEvidence(input).getTransactionReceipt().toString();
    }

    @GetMapping("transferEvidence")
    public Object transferEvidence() throws Exception {
        TransferItem item = new TransferItem("order002", "user001", "user002", "0x0000000000000000000000000000000000000001", "0x0000000000000000000000000000000000000002", java.math.BigInteger.valueOf(50L));
        ResourceCoinManagerTransferEvidenceInputBO input = new ResourceCoinManagerTransferEvidenceInputBO(item);
        return service.transferEvidence(input).getTransactionReceipt().toString();
    }

    @GetMapping("withdrawEvidence")
    public Object withdrawEvidence() throws Exception {
        WithdrawItem item = new WithdrawItem("order003", "user001", "0x0000000000000000000000000000000000000001", java.math.BigInteger.valueOf(30L), "receiverAccount001");
        ResourceCoinManagerWithdrawEvidenceInputBO input = new ResourceCoinManagerWithdrawEvidenceInputBO(item);
        return service.withdrawEvidence(input).getTransactionReceipt().toString();
    }

    @GetMapping("generateKeypair")
    public Object generateKeypair() {
        ResourceCoinManagerService.Keypair pair = service.generateKeypair();
        return String.format("address: %s\nprivateKey: %s", pair.address, pair.privateKey);
    }
} 