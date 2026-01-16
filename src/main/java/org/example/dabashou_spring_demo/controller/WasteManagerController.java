package org.example.dabashou_spring_demo.controller;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.dabashou_spring_demo.model.bo.*;
import org.example.dabashou_spring_demo.model.bo.DisposeRewardItem;
import org.example.dabashou_spring_demo.model.bo.PropertyRewardItem;
import org.example.dabashou_spring_demo.service.WasteManagerService;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("wastemanager")
public class WasteManagerController {

    @Autowired private WasteManagerService service;


    /**
     * 投放垃圾
     */
    @GetMapping("dispose")
    public String dispose() throws Exception {
        BigInteger timestamp = BigInteger.valueOf(1719802693);
        String orderID = "order126";
        String userID = "user113";
        String deviceID = "device143";
        String binID = "bin123";
        String courtID = "court113";
        byte[] imagesHash = new byte[32];
        byte[] creditParamsHash = new byte[32];
        BigInteger price= BigInteger.valueOf(100);
        BigInteger reward= BigInteger.valueOf(100);
        String wasteType = "干垃圾";
        BigInteger quantity = BigInteger.valueOf(1230);
        BigInteger score = BigInteger.valueOf(100);
        BigInteger resourceCoin = BigInteger.valueOf(300);
        String receiverAddress = "0x0000000000000000000000000000000000000002";
        DisposeItem item = new DisposeItem(timestamp, orderID, userID, deviceID, binID, courtID, imagesHash, creditParamsHash, price,reward, wasteType, quantity, score, resourceCoin, receiverAddress);
        WasteManagerDisposeInputBO input = new WasteManagerDisposeInputBO(item);
        return service.dispose(input).getTransactionReceipt().toString();
    }

    /**
     * 更新垃圾投放记录
     */
    @GetMapping("update")
    public String update() throws Exception {
        BigInteger timestamp = BigInteger.valueOf(1715666453);
        String disposeOrderID = "order126";
        String orderID = "updorder126";
        String userID = "user113";
        String deviceID = "device143";
        String binID = "bin123";
        String courtID = "court113";
        byte[] imagesHash = new byte[32];
        byte[] creditParamsHash = new byte[32];
        BigInteger price= BigInteger.valueOf(100);
        BigInteger reward= BigInteger.valueOf(100);
        String wasteType = "干垃圾";
        BigInteger quantity = BigInteger.valueOf(1230);
        BigInteger score = BigInteger.valueOf(100);
        BigInteger resourceCoinChange = BigInteger.valueOf(50);
        String comment = "申诉说明";
        UpdateItem item = new UpdateItem(timestamp, disposeOrderID, orderID, userID, deviceID, binID, courtID, imagesHash, creditParamsHash, price,reward, wasteType, quantity, score, resourceCoinChange, comment);
        WasteManagerUpdateInputBO input = new WasteManagerUpdateInputBO(item);
        return service.update(input).getTransactionReceipt().toString();
    }

    /**
     * 结算
     */
    @GetMapping("settle")
    public String settle() throws Exception {
        BigInteger timestamp = BigInteger.valueOf(1719803000);
        String targerOrderID = "orderTarget001";
        String orderID = "order125";
        String toAddress = "0x0000000000000000000000000000000000000001";
        BigInteger amount = BigInteger.valueOf(100);
        WasteManagerSettleInputBO input = new WasteManagerSettleInputBO(timestamp, targerOrderID, orderID, toAddress, amount);
        return service.settle(input).getTransactionReceipt().toString();
    }

    /**
     * 算法注册
     */
    @GetMapping("register")
    public String register() throws Exception {
        BigInteger timestamp = BigInteger.valueOf(1719804000);
        String algoID = "algo001";
        byte[] algoHash = new byte[32];
        String comments = "算法注册测试";
        WasteManagerRegisterInputBO input = new WasteManagerRegisterInputBO(timestamp, algoID, algoHash, comments);
        return service.register(input).getTransactionReceipt().toString();
    }

    /**
     * 投放奖励登记
     */
    @GetMapping("disposeReward")
    public String disposeReward() throws Exception {
        String timestamp = "20250101";
        String orderID = "order125";
        String category = "干垃圾";
        BigInteger amount = BigInteger.valueOf(10);
        BigInteger price = BigInteger.valueOf(2);
        BigInteger accruedReward = BigInteger.valueOf(20);
        BigInteger deductedReward = BigInteger.valueOf(1);
        BigInteger actualReward = BigInteger.valueOf(19);
        String comments = "投放奖励登记测试";
        DisposeRewardItem item = new DisposeRewardItem(timestamp, orderID, category, amount, price, accruedReward, deductedReward, actualReward, comments);
        WasteManagerDisposeRewardInputBO input = new WasteManagerDisposeRewardInputBO(item);
        return service.disposeReward(input).getTransactionReceipt().toString();
    }

    /**
     * 物业奖励登记
     */
    @GetMapping("propertyReward")
    public String propertyReward() throws Exception {
        String timestamp = "20250101";
        String orderID = "order200";
        String courtID = "court113";
        BigInteger houseHoldNum = BigInteger.valueOf(100);
        BigInteger score = BigInteger.valueOf(80);
        BigInteger unit = BigInteger.valueOf(1);
        BigInteger ratio = BigInteger.valueOf(10);
        BigInteger amount = BigInteger.valueOf(1000);
        String comments = "物业奖励登记测试";
        PropertyRewardItem item = new PropertyRewardItem(timestamp, orderID, courtID, houseHoldNum, score, unit, ratio, amount, comments);
        WasteManagerPropertyRewardInputBO input = new WasteManagerPropertyRewardInputBO(item);
        return service.propertyReward(input).getTransactionReceipt().toString();
    }

    /**
     * 查询投放记录
     */
    @GetMapping("getDispose")
    public DisposeItem getDispose() throws Exception {
        String orderID = "order126";
        WasteManagerGetDisposeInputBO input = new WasteManagerGetDisposeInputBO(orderID);
        return service.getDispose(input);
    }

    /**
     * 查询更新记录
     */
    @GetMapping("getUpdate")
    public UpdateItem getUpdate() throws Exception {
        String updateOrderID = "updorder126";
        WasteManagerGetUpdateInputBO input = new WasteManagerGetUpdateInputBO(updateOrderID);
        return service.getUpdate(input);
    }

    /**
     * 查询算法
     */
    @GetMapping("selectAlgo")
    public Object selectAlgo() throws Exception {
        String algoID = "algo001";
        WasteManagerSelectAlgoInputBO input = new WasteManagerSelectAlgoInputBO(algoID);
        return service.selectAlgo(input).getReturnObject();
    }

    /**
     * 按orderID查询投放奖励
     */
    @GetMapping("getDisposeRewardByOrderID")
    public DisposeRewardItem getDisposeRewardByOrderID() throws Exception {
        String orderID = "RB20251208091249-349";
        WasteManagerGetDisposeRewardByOrderIDInputBO input = new WasteManagerGetDisposeRewardByOrderIDInputBO(orderID);
        return service.getDisposeRewardByOrderID(input);
    }

    /**
     * 按orderID查询物业奖励
     */
    @GetMapping("getPropertyRewardByOrderID")
    public PropertyRewardItem getPropertyRewardByOrderID() throws Exception {
        String orderID = "order200";
        WasteManagerGetPropertyRewardByOrderIDInputBO input = new WasteManagerGetPropertyRewardByOrderIDInputBO(orderID);
        return service.getPropertyRewardByOrderID(input);
    }

    /**
     * 测试用例：连续调用两次dispose，使用相同的orderID但不同的其他数据
     * 然后查询getDispose，查看返回的是哪一条记录
     */
    @GetMapping("testDisposeDuplicateOrderID")
    public String testDisposeDuplicateOrderID() throws Exception {
        StringBuilder result = new StringBuilder();
        result.append("=== 测试相同orderID的多次dispose调用 ===\n\n");
        
        // 使用相同的orderID
        String testOrderID = "test_order_duplicate_001";
        
        // 第一次dispose调用
        result.append("【第一次dispose调用】\n");
        BigInteger timestamp1 = BigInteger.valueOf(System.currentTimeMillis() / 1000);
        String userID1 = "user_test_001";
        String deviceID1 = "device_test_001";
        String binID1 = "bin_test_001";
        String courtID1 = "court_test_001";
        byte[] imagesHash1 = new byte[32];
        imagesHash1[0] = 1; // 设置不同的hash值
        byte[] creditParamsHash1 = new byte[32];
        creditParamsHash1[0] = 1;
        BigInteger price1 = BigInteger.valueOf(100);
        BigInteger reward1 = BigInteger.valueOf(50);
        String wasteType1 = "干垃圾";
        BigInteger quantity1 = BigInteger.valueOf(1000);
        BigInteger score1 = BigInteger.valueOf(80);
        BigInteger resourceCoin1 = BigInteger.valueOf(200);
        String receiverAddress1 = "0x0000000000000000000000000000000000000001";
        
        DisposeItem item1 = new DisposeItem(timestamp1, testOrderID, userID1, deviceID1, binID1, courtID1, 
                imagesHash1, creditParamsHash1, price1, reward1, wasteType1, quantity1, score1, resourceCoin1, receiverAddress1);
        WasteManagerDisposeInputBO input1 = new WasteManagerDisposeInputBO(item1);
        String txHash1 = service.dispose(input1).getTransactionReceipt().getTransactionHash();
        result.append("交易哈希: ").append(txHash1).append("\n");
        result.append("orderID: ").append(testOrderID).append("\n");
        result.append("quantity: ").append(quantity1).append("\n");
        result.append("price: ").append(price1).append("\n");
        result.append("userID: ").append(userID1).append("\n\n");
        
        // 等待一下，确保两次交易有时间差
        Thread.sleep(1000);
        
        // 第二次dispose调用 - 相同的orderID，但不同的其他数据
        result.append("【第二次dispose调用】\n");
        BigInteger timestamp2 = BigInteger.valueOf(System.currentTimeMillis() / 1000);
        String userID2 = "user_test_002"; // 不同的userID
        String deviceID2 = "device_test_002"; // 不同的deviceID
        String binID2 = "bin_test_002"; // 不同的binID
        String courtID2 = "court_test_002"; // 不同的courtID
        byte[] imagesHash2 = new byte[32];
        imagesHash2[0] = 2; // 设置不同的hash值
        byte[] creditParamsHash2 = new byte[32];
        creditParamsHash2[0] = 2;
        BigInteger price2 = BigInteger.valueOf(200); // 不同的price
        BigInteger reward2 = BigInteger.valueOf(100); // 不同的reward
        String wasteType2 = "湿垃圾"; // 不同的wasteType
        BigInteger quantity2 = BigInteger.valueOf(2000); // 不同的quantity
        BigInteger score2 = BigInteger.valueOf(90); // 不同的score
        BigInteger resourceCoin2 = BigInteger.valueOf(400); // 不同的resourceCoin
        String receiverAddress2 = "0x0000000000000000000000000000000000000002"; // 不同的receiverAddress
        
        DisposeItem item2 = new DisposeItem(timestamp2, testOrderID, userID2, deviceID2, binID2, courtID2, 
                imagesHash2, creditParamsHash2, price2, reward2, wasteType2, quantity2, score2, resourceCoin2, receiverAddress2);
        WasteManagerDisposeInputBO input2 = new WasteManagerDisposeInputBO(item2);
        String txHash2 = service.dispose(input2).getTransactionReceipt().getTransactionHash();
        result.append("交易哈希: ").append(txHash2).append("\n");
        result.append("orderID: ").append(testOrderID).append(" (相同)\n");
        result.append("quantity: ").append(quantity2).append(" (不同)\n");
        result.append("price: ").append(price2).append(" (不同)\n");
        result.append("userID: ").append(userID2).append(" (不同)\n\n");
        
        // 等待一下，确保交易已上链
        Thread.sleep(1000);
        
        // 查询getDispose
        result.append("【查询getDispose】\n");
        result.append("查询orderID: ").append(testOrderID).append("\n");
        WasteManagerGetDisposeInputBO queryInput = new WasteManagerGetDisposeInputBO(testOrderID);
        DisposeItem queryResult = service.getDispose(queryInput);
        
        if (queryResult == null) {
            result.append("查询结果: null (未找到记录)\n");
        } else {
            result.append("查询结果:\n");
            result.append("  orderID: ").append(queryResult.orderID).append("\n");
            result.append("  timestamp: ").append(queryResult.timestamp).append("\n");
            result.append("  userID: ").append(queryResult.userID).append("\n");
            result.append("  deviceID: ").append(queryResult.deviceID).append("\n");
            result.append("  quantity: ").append(queryResult.quantity).append("\n");
            result.append("  price: ").append(queryResult.price).append("\n");
            result.append("  wasteType: ").append(queryResult.wasteType).append("\n");
            result.append("  score: ").append(queryResult.score).append("\n");
            result.append("  resourceCoin: ").append(queryResult.resourceCoin).append("\n");
            result.append("  receiverAddress: ").append(queryResult.receiverAddress).append("\n\n");
            
            // 判断返回的是哪一条
            if (queryResult.quantity.equals(quantity1) && queryResult.userID.equals(userID1)) {
                result.append("【结论】返回的是第一条记录（第一次dispose的数据）\n");
            } else if (queryResult.quantity.equals(quantity2) && queryResult.userID.equals(userID2)) {
                result.append("【结论】返回的是第二条记录（第二次dispose的数据）\n");
            } else {
                result.append("【结论】返回的数据与两次调用都不完全匹配，可能是其他记录\n");
            }
        }
        
        return result.toString();
    }

    @Autowired
    private org.example.dabashou_spring_demo.service.ResourceCoinManagerService resourceCoinService;

    /**
     * 测试用例：使用相同的orderID多次调用settle，检测是否会重复转账
     * 用于测试重入保护机制
     */
    @GetMapping("testSettleDuplicateOrderID")
    public String testSettleDuplicateOrderID() throws Exception {
        StringBuilder result = new StringBuilder();
        result.append("=== 测试相同orderID的多次settle调用（重入检测） ===\n\n");
        
        // 使用相同的orderID
        String testOrderID = "test_settle_order_002";
        String targerOrderID = "target_order_001";
        String toAddress = "0x0000000000000000000000000000000000000101"; // 接收地址
        BigInteger amount = BigInteger.valueOf(100); // 转账金额
        
        // 查询接收地址的初始余额
        result.append("【初始状态】\n");
        org.example.dabashou_spring_demo.model.bo.ResourceCoinManagerBalanceOfInputBO balanceInput = 
            new org.example.dabashou_spring_demo.model.bo.ResourceCoinManagerBalanceOfInputBO(toAddress);
        java.math.BigInteger initialBalance = resourceCoinService.balanceOf(balanceInput);
        result.append("接收地址: ").append(toAddress).append("\n");
        result.append("初始余额: ").append(initialBalance).append("\n");
        result.append("orderID: ").append(testOrderID).append("\n");
        result.append("转账金额: ").append(amount).append("\n\n");
        
        // 第一次settle调用
        result.append("【第一次settle调用】\n");
        BigInteger timestamp1 = BigInteger.valueOf(System.currentTimeMillis() / 1000);
        WasteManagerSettleInputBO input1 = new WasteManagerSettleInputBO(timestamp1, targerOrderID, testOrderID, toAddress, amount);
        
        String txHash1 = null;
        boolean firstCallSuccess = false;
        Integer firstCallStatus = null;
        try {
            org.fisco.bcos.sdk.v3.model.TransactionReceipt receipt1 = service.settle(input1).getTransactionReceipt();
            txHash1 = receipt1.getTransactionHash();
            firstCallStatus = receipt1.getStatus();
            // status == 0 表示成功
            firstCallSuccess = (firstCallStatus != null && firstCallStatus == 0);
            result.append("交易哈希: ").append(txHash1).append("\n");
            result.append("交易状态码: ").append(firstCallStatus).append("\n");
            result.append("调用状态: ").append(firstCallSuccess ? "成功" : "失败").append("\n");
            if (!firstCallSuccess) {
                result.append("错误信息: ").append(receipt1.getMessage() != null ? receipt1.getMessage() : "交易执行失败").append("\n");
            }
        } catch (Exception e) {
            result.append("调用异常: ").append(e.getMessage()).append("\n");
            result.append("调用状态: 异常\n");
        }
        
        // 等待一下，确保交易已上链
        Thread.sleep(1000);
        
        // 查询第一次调用后的余额
        java.math.BigInteger balanceAfterFirst = resourceCoinService.balanceOf(balanceInput);
        result.append("第一次调用后余额: ").append(balanceAfterFirst).append("\n");
        java.math.BigInteger balanceChange1 = balanceAfterFirst.subtract(initialBalance);
        result.append("余额变化: ").append(balanceChange1).append("\n\n");
        
        // 第二次settle调用 - 相同的orderID
        result.append("【第二次settle调用】（相同orderID）\n");
        BigInteger timestamp2 = BigInteger.valueOf(System.currentTimeMillis() / 1000);
        WasteManagerSettleInputBO input2 = new WasteManagerSettleInputBO(timestamp2, targerOrderID, testOrderID, toAddress, amount);
        
        String txHash2 = null;
        boolean secondCallSuccess = false;
        Integer secondCallStatus = null;
        try {
            org.fisco.bcos.sdk.v3.model.TransactionReceipt receipt2 = service.settle(input2).getTransactionReceipt();
            txHash2 = receipt2.getTransactionHash();
            secondCallStatus = receipt2.getStatus();
            // status == 0 表示成功
            secondCallSuccess = (secondCallStatus != null && secondCallStatus == 0);
            result.append("交易哈希: ").append(txHash2).append("\n");
            result.append("交易状态码: ").append(secondCallStatus).append("\n");
            result.append("调用状态: ").append(secondCallSuccess ? "成功" : "失败（可能被重入保护拦截）").append("\n");
            if (!secondCallSuccess) {
                result.append("错误信息: ").append(receipt2.getMessage() != null ? receipt2.getMessage() : "交易执行失败").append("\n");
            }
        } catch (Exception e) {
            result.append("调用异常: ").append(e.getMessage()).append("\n");
            result.append("调用状态: 异常\n");
        }
        
        // 等待一下，确保交易已上链（如果成功）
        Thread.sleep(1000);
        
        // 查询第二次调用后的余额
        java.math.BigInteger balanceAfterSecond = resourceCoinService.balanceOf(balanceInput);
        result.append("第二次调用后余额: ").append(balanceAfterSecond).append("\n");
        java.math.BigInteger balanceChange2 = balanceAfterSecond.subtract(balanceAfterFirst);
        result.append("第二次余额变化: ").append(balanceChange2).append("\n");
        java.math.BigInteger totalBalanceChange = balanceAfterSecond.subtract(initialBalance);
        result.append("总余额变化: ").append(totalBalanceChange).append("\n\n");
        
        // 分析结果
        result.append("【测试结果分析】\n");
        if (!firstCallSuccess) {
            result.append("✗ 第一次调用失败（status=").append(firstCallStatus).append("），无法进行重入测试\n");
        } else if (!secondCallSuccess) {
            result.append("✓ 重入保护生效！\n");
            result.append("  - 第一次调用成功（status=0），余额增加: ").append(balanceChange1).append("\n");
            result.append("  - 第二次调用失败（status=").append(secondCallStatus).append("），余额未变化: ").append(balanceChange2).append("\n");
            result.append("  - 总余额变化: ").append(totalBalanceChange).append("（应该等于一次转账金额）\n");
            if (totalBalanceChange.equals(amount)) {
                result.append("✓ 验证通过：只转账了一次，重入保护正常工作\n");
            } else {
                result.append("⚠ 警告：余额变化不符合预期\n");
            }
        } else {
            // 两次都成功
            result.append("⚠ 重入保护可能未生效！\n");
            result.append("  - 第一次调用成功（status=0），余额增加: ").append(balanceChange1).append("\n");
            result.append("  - 第二次调用也成功（status=0），余额增加: ").append(balanceChange2).append("\n");
            result.append("  - 总余额变化: ").append(totalBalanceChange).append("\n");
            if (totalBalanceChange.equals(amount.multiply(BigInteger.valueOf(2)))) {
                result.append("✗ 验证失败：转账了两次，可能存在重入漏洞\n");
            } else {
                result.append("⚠ 余额变化不符合预期，需要进一步检查\n");
            }
        }
        
        return result.toString();
    }


}
