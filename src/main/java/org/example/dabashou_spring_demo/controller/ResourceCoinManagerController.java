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
    public java.math.BigInteger balanceOf() throws Exception {
        ResourceCoinManagerBalanceOfInputBO input = new ResourceCoinManagerBalanceOfInputBO("0x1c7fa1c2858dec136f4951a9f4d4e9c95afe5025");
        return service.balanceOf(input);
    }

    @GetMapping("issueEvidence")
    public Object issueEvidence() throws Exception {
        IssueItem item = new IssueItem("order001", "0x0000000000000000000000000000000000000001", java.math.BigInteger.valueOf(100L));
        ResourceCoinManagerIssueEvidenceInputBO input = new ResourceCoinManagerIssueEvidenceInputBO(item);
        return service.issueEvidence(input).getTransactionReceipt().toString();
    }

    @GetMapping("transferEvidence")
    public Object transferEvidence() throws Exception {
        TransferItem item = new TransferItem("order002", "user001", "user002", "0x0000000000000000000000000000000000000001", "0x0000000000000000000000000000000000000002", java.math.BigInteger.valueOf(50L),"备注信息123");
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

    /**
     * 测试负数余额接口
     * 步骤：
     * 1. 给地址A发行100个币
     * 2. 从地址A转账150个币给地址B（超过余额，允许负数）
     * 3. 查询地址A的余额，应该返回-50
     * 
     * @return 测试结果，包含各步骤的执行结果和最终余额
     */
    @GetMapping("testNegativeBalance")
    public Object testNegativeBalance() throws Exception {
        String addressA = "0x0000000000000000000000000000000000000101";
        String addressB = "0x0000000000000000000000000000000000000102";
        String addressC = "0x0000000000000000000000000000000000000103";
        java.math.BigInteger issueAmount = java.math.BigInteger.valueOf(100L);
        java.math.BigInteger transferAmount = java.math.BigInteger.valueOf(150L);
        
        StringBuilder result = new StringBuilder();
        result.append("=== 负数余额测试 ===\n\n");
        
        // 查询测试地址的初始余额
        result.append("=== 初始余额检查 ===\n");
        ResourceCoinManagerBalanceOfInputBO balanceInputA = new ResourceCoinManagerBalanceOfInputBO(addressA);
        ResourceCoinManagerBalanceOfInputBO balanceInputB = new ResourceCoinManagerBalanceOfInputBO(addressB);
        ResourceCoinManagerBalanceOfInputBO balanceInputC = new ResourceCoinManagerBalanceOfInputBO(addressC);
        
        java.math.BigInteger initialBalanceA = service.balanceOf(balanceInputA);
        java.math.BigInteger initialBalanceB = service.balanceOf(balanceInputB);
        java.math.BigInteger initialBalanceC = service.balanceOf(balanceInputC);
        
        result.append("地址A初始余额: ").append(initialBalanceA).append("\n");
        result.append("地址B初始余额: ").append(initialBalanceB).append("\n");
        result.append("地址C初始余额: ").append(initialBalanceC).append("\n");
        
        // 验证初始余额是否为0
        boolean allZero = initialBalanceA.equals(java.math.BigInteger.ZERO) 
                       && initialBalanceB.equals(java.math.BigInteger.ZERO)
                       && initialBalanceC.equals(java.math.BigInteger.ZERO);
        
        if (allZero) {
            result.append("✓ 所有测试地址初始余额为0，可以开始测试\n\n");
        } else {
            result.append("⚠ 警告：部分测试地址初始余额不为0，可能影响测试结果\n");
            result.append("  地址A: ").append(initialBalanceA.equals(java.math.BigInteger.ZERO) ? "✓" : "✗").append("\n");
            result.append("  地址B: ").append(initialBalanceB.equals(java.math.BigInteger.ZERO) ? "✓" : "✗").append("\n");
            result.append("  地址C: ").append(initialBalanceC.equals(java.math.BigInteger.ZERO) ? "✓" : "✗").append("\n\n");
        }
        
        // 步骤1: 给地址A发行币
        result.append("步骤1: 给地址A发行 ").append(issueAmount).append(" 个币\n");
        IssueItem issueItem = new IssueItem("test_issue_101", addressA, issueAmount);
        ResourceCoinManagerIssueEvidenceInputBO issueInput = new ResourceCoinManagerIssueEvidenceInputBO(issueItem);
        String issueTxHash = service.issueEvidence(issueInput).getTransactionReceipt().getTransactionHash();
        result.append("发行交易哈希: ").append(issueTxHash).append("\n");
        
        // 查询地址A的余额（应该是100）
        java.math.BigInteger balanceAfterIssue = service.balanceOf(balanceInputA);
        result.append("发行后地址A余额: ").append(balanceAfterIssue).append("\n\n");
        
        // 步骤2: 从地址A转账超过余额的金额给地址B
        result.append("步骤2: 从地址A转账 ").append(transferAmount).append(" 个币给地址B（超过余额）\n");
        TransferItem transferItem = new TransferItem(
            "test_transfer_001", 
            "userA", 
            "userB", 
            addressA, 
            addressB, 
            transferAmount, 
            "测试负数余额转账"
        );
        ResourceCoinManagerTransferEvidenceInputBO transferInput = new ResourceCoinManagerTransferEvidenceInputBO(transferItem);
        String transferTxHash = service.transferEvidence(transferInput).getTransactionReceipt().getTransactionHash();
        result.append("转账交易哈希: ").append(transferTxHash).append("\n");
        
        // 步骤3: 查询地址A的余额（应该是-50）
        java.math.BigInteger balanceAfterTransfer = service.balanceOf(balanceInputA);
        result.append("转账后地址A余额: ").append(balanceAfterTransfer).append("\n");
        
        // 查询地址B的余额（应该是150）
        java.math.BigInteger balanceB = service.balanceOf(balanceInputB);
        result.append("转账后地址B余额: ").append(balanceB).append("\n\n");
        
        // 验证第一步测试结果
        result.append("=== 第一步测试结果 ===\n");
        if (balanceAfterTransfer.compareTo(java.math.BigInteger.ZERO) < 0) {
            result.append("✓ 测试成功！地址A余额为负数: ").append(balanceAfterTransfer).append("\n");
            result.append("✓ 合约支持负数余额功能正常\n\n");
        } else {
            result.append("✗ 测试失败！地址A余额不是负数: ").append(balanceAfterTransfer).append("\n\n");
        }
        
        // 步骤4: 从已经是负数的地址A继续转出
        java.math.BigInteger additionalTransferAmount = java.math.BigInteger.valueOf(30L);
        result.append("步骤4: 从负数余额的地址A继续转出 ").append(additionalTransferAmount).append(" 个币给地址C\n");
        result.append("当前地址A余额: ").append(balanceAfterTransfer).append("（负数）\n");
        
        TransferItem additionalTransferItem = new TransferItem(
            "test_transfer_102", 
            "userA", 
            "userC", 
            addressA, 
            addressC, 
            additionalTransferAmount, 
            "测试负数账户继续转出"
        );
        ResourceCoinManagerTransferEvidenceInputBO additionalTransferInput = new ResourceCoinManagerTransferEvidenceInputBO(additionalTransferItem);
        String additionalTransferTxHash = service.transferEvidence(additionalTransferInput).getTransactionReceipt().getTransactionHash();
        result.append("继续转出交易哈希: ").append(additionalTransferTxHash).append("\n");
        
        // 步骤5: 查询转出后的余额（应该继续减少，从-50变成-80）
        java.math.BigInteger balanceAfterAdditionalTransfer = service.balanceOf(balanceInputA);
        result.append("继续转出后地址A余额: ").append(balanceAfterAdditionalTransfer).append("\n");
        
        // 查询地址C的余额（应该是30）
        java.math.BigInteger balanceC = service.balanceOf(balanceInputC);
        result.append("继续转出后地址C余额: ").append(balanceC).append("\n\n");
        
        // 验证第二步测试结果
        result.append("=== 第二步测试结果 ===\n");
        java.math.BigInteger expectedBalance = balanceAfterTransfer.subtract(additionalTransferAmount);
        if (balanceAfterAdditionalTransfer.equals(expectedBalance)) {
            result.append("✓ 测试成功！负数账户可以继续转出\n");
            result.append("✓ 余额正确减少: ").append(balanceAfterTransfer).append(" -> ").append(balanceAfterAdditionalTransfer).append("\n");
            result.append("✓ 负数余额继续减少功能正常\n");
        } else {
            result.append("✗ 测试失败！余额计算不正确\n");
            result.append("  期望余额: ").append(expectedBalance).append("\n");
            result.append("  实际余额: ").append(balanceAfterAdditionalTransfer).append("\n");
        }
        
        // 总结
        result.append("\n=== 完整测试总结 ===\n");
        boolean step1Success = balanceAfterTransfer.compareTo(java.math.BigInteger.ZERO) < 0;
        boolean step2Success = balanceAfterAdditionalTransfer.equals(expectedBalance);
        if (step1Success && step2Success) {
            result.append("✓ 所有测试通过！负数余额功能完全正常\n");
            result.append("  - 支持余额变为负数\n");
            result.append("  - 负数账户可以继续转出\n");
            result.append("  - 负数余额可以继续减少\n");
        } else {
            result.append("✗ 部分测试失败，请检查合约实现\n");
        }
        
        return result.toString();
    }
} 