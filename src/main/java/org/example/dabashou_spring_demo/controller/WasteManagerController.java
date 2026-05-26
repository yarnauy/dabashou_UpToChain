package org.example.dabashou_spring_demo.controller;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.dabashou_spring_demo.model.bo.*;
import org.example.dabashou_spring_demo.model.bo.DisposeRewardItem;
import org.example.dabashou_spring_demo.model.bo.PropertyRewardItem;
import org.example.dabashou_spring_demo.service.WasteManagerService;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.transaction.model.dto.TransactionResponse;
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
     * 活动奖励上链（示范）
     */
    @GetMapping("activityReward")
    public String activityReward() throws Exception {
        BigInteger timestamp = BigInteger.valueOf(1719805000);
        String orderID = "activity_order_001";
        String userID = "user113";
        String activityID = "spring_campaign_2026";
        BigInteger resourceCoin = BigInteger.valueOf(100);
        ActivityRewardItem item = new ActivityRewardItem(timestamp, orderID, userID, activityID, resourceCoin);
        WasteManagerActivityRewardInputBO input = new WasteManagerActivityRewardInputBO(item);
        return service.activityReward(input).getTransactionReceipt().toString();
    }

    /**
     * 按 orderID 查询活动奖励（示范）
     */
    @GetMapping("getActivityRewardByOrderID")
    public ActivityRewardItem getActivityRewardByOrderID() throws Exception {
        String orderID = "activity_order_001";
        WasteManagerGetActivityRewardByOrderIDInputBO input = new WasteManagerGetActivityRewardByOrderIDInputBO(orderID);
        return service.getActivityRewardByOrderID(input);
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
     * 测试 dispose 使用负数 resourceCoin（int256），并查询链上记录
     */
    @GetMapping("testDisposeNegativeResourceCoin")
    public String testDisposeNegativeResourceCoin() throws Exception {
        StringBuilder result = new StringBuilder();
        result.append("=== 测试 dispose 负数 resourceCoin ===\n\n");

        String testOrderID = "test_negative_rc_" + (System.currentTimeMillis() % 100000);
        byte[] imagesHash = new byte[32];
        byte[] creditParamsHash = new byte[32];
        BigInteger resourceCoin = BigInteger.valueOf(-88);

        DisposeItem item = new DisposeItem(
                BigInteger.valueOf(System.currentTimeMillis() / 1000),
                testOrderID,
                "user_neg",
                "device_neg",
                "bin_neg",
                "court_neg",
                imagesHash,
                creditParamsHash,
                BigInteger.valueOf(100),
                BigInteger.valueOf(50),
                "干垃圾",
                BigInteger.valueOf(500),
                BigInteger.valueOf(60),
                resourceCoin,
                "0x0000000000000000000000000000000000000001");
        result.append(formatTxResult(service.dispose(new WasteManagerDisposeInputBO(item)))).append("\n\n");

        Thread.sleep(1000);

        DisposeItem onChain = service.getDispose(new WasteManagerGetDisposeInputBO(testOrderID));
        result.append("【链上查询】orderID=").append(testOrderID).append("\n");
        if (onChain == null) {
            result.append("未查到记录\n");
        } else {
            result.append("resourceCoin=").append(onChain.resourceCoin).append("\n");
            if (resourceCoin.equals(onChain.resourceCoin)) {
                result.append("【结论】负数 resourceCoin 写入成功\n");
            } else {
                result.append("【结论】链上 resourceCoin 与预期不符\n");
            }
        }
        return result.toString();
    }

    /**
     * 测试 dispose 幂等：相同 orderID 第二次调用成功但不改链上数据
     */
    @GetMapping("testDisposeIdempotent")
    public String testDisposeIdempotent() throws Exception {
        StringBuilder result = new StringBuilder();
        result.append("=== 测试 dispose 幂等 ===\n\n");

        String testOrderID = "test_dispose_idem_" + (System.currentTimeMillis() % 100000);
        byte[] imagesHash = new byte[32];
        byte[] creditParamsHash = new byte[32];

        DisposeItem item1 = new DisposeItem(
                BigInteger.valueOf(System.currentTimeMillis() / 1000),
                testOrderID,
                "user_first",
                "device_first",
                "bin_first",
                "court_first",
                imagesHash,
                creditParamsHash,
                BigInteger.valueOf(100),
                BigInteger.valueOf(50),
                "干垃圾",
                BigInteger.valueOf(1000),
                BigInteger.valueOf(80),
                BigInteger.valueOf(200),
                "0x0000000000000000000000000000000000000001");
        result.append("【第一次】\n").append(formatTxResult(service.dispose(new WasteManagerDisposeInputBO(item1)))).append("\n\n");

        Thread.sleep(1000);

        DisposeItem item2 = new DisposeItem(
                BigInteger.valueOf(System.currentTimeMillis() / 1000),
                testOrderID,
                "user_second",
                "device_second",
                "bin_second",
                "court_second",
                imagesHash,
                creditParamsHash,
                BigInteger.valueOf(999),
                BigInteger.valueOf(999),
                "湿垃圾",
                BigInteger.valueOf(9999),
                BigInteger.valueOf(99),
                BigInteger.valueOf(-50),
                "0x0000000000000000000000000000000000000002");
        result.append("【第二次 相同orderID】\n").append(formatTxResult(service.dispose(new WasteManagerDisposeInputBO(item2)))).append("\n\n");

        Thread.sleep(1000);

        DisposeItem onChain = service.getDispose(new WasteManagerGetDisposeInputBO(testOrderID));
        result.append("【链上】userID=").append(onChain != null ? onChain.userID : "null");
        result.append(", resourceCoin=").append(onChain != null ? onChain.resourceCoin : "null").append("\n");
        if (onChain != null && "user_first".equals(onChain.userID) && onChain.resourceCoin.equals(BigInteger.valueOf(200))) {
            result.append("【结论】幂等生效\n");
        } else {
            result.append("【结论】请检查合约或 orderID 冲突\n");
        }
        return result.toString();
    }

    /**
     * 测试 update / disposeReward / propertyReward 幂等
     */
    @GetMapping("testRewardIdempotent")
    public String testRewardIdempotent() throws Exception {
        StringBuilder result = new StringBuilder();
        result.append("=== 测试 update / disposeReward / propertyReward 幂等 ===\n\n");
        byte[] imagesHash = new byte[32];
        byte[] creditParamsHash = new byte[32];

        String updateOrderID = "test_update_idem_" + (System.currentTimeMillis() % 100000);
        UpdateItem update1 = new UpdateItem(
                BigInteger.valueOf(System.currentTimeMillis() / 1000),
                "order126",
                updateOrderID,
                "user_u1",
                "device_u1",
                "bin_u1",
                "court_u1",
                imagesHash,
                creditParamsHash,
                BigInteger.valueOf(100),
                BigInteger.valueOf(50),
                "干垃圾",
                BigInteger.valueOf(500),
                BigInteger.valueOf(60),
                BigInteger.valueOf(10),
                "第一次更新");
        result.append("【update 第一次】\n").append(formatTxResult(service.update(new WasteManagerUpdateInputBO(update1)))).append("\n");
        UpdateItem update2 = new UpdateItem(
                BigInteger.valueOf(System.currentTimeMillis() / 1000),
                "order126",
                updateOrderID,
                "user_u2",
                "device_u2",
                "bin_u2",
                "court_u2",
                imagesHash,
                creditParamsHash,
                BigInteger.valueOf(999),
                BigInteger.valueOf(999),
                "湿垃圾",
                BigInteger.valueOf(999),
                BigInteger.valueOf(99),
                BigInteger.valueOf(-99),
                "第二次更新");
        result.append("【update 第二次 相同orderID】\n").append(formatTxResult(service.update(new WasteManagerUpdateInputBO(update2)))).append("\n\n");

        Thread.sleep(1000);

        String disposeRewardOrderID = "test_dr_idem_" + (System.currentTimeMillis() % 100000);
        DisposeRewardItem dr1 = new DisposeRewardItem("20260101", disposeRewardOrderID, "干垃圾",
                BigInteger.valueOf(10), BigInteger.valueOf(2), BigInteger.valueOf(20),
                BigInteger.valueOf(1), BigInteger.valueOf(19), "第一次");
        result.append("【disposeReward 第一次】\n").append(formatTxResult(service.disposeReward(new WasteManagerDisposeRewardInputBO(dr1)))).append("\n");
        DisposeRewardItem dr2 = new DisposeRewardItem("20260102", disposeRewardOrderID, "湿垃圾",
                BigInteger.valueOf(99), BigInteger.valueOf(99), BigInteger.valueOf(99),
                BigInteger.valueOf(99), BigInteger.valueOf(99), "第二次");
        result.append("【disposeReward 第二次】\n").append(formatTxResult(service.disposeReward(new WasteManagerDisposeRewardInputBO(dr2)))).append("\n\n");

        Thread.sleep(1000);

        String propertyOrderID = "test_pr_idem_" + (System.currentTimeMillis() % 100000);
        PropertyRewardItem pr1 = new PropertyRewardItem("20260101", propertyOrderID, "court_a",
                BigInteger.valueOf(10), BigInteger.valueOf(80), BigInteger.valueOf(1),
                BigInteger.valueOf(10), BigInteger.valueOf(100), "第一次");
        result.append("【propertyReward 第一次】\n").append(formatTxResult(service.propertyReward(new WasteManagerPropertyRewardInputBO(pr1)))).append("\n");
        PropertyRewardItem pr2 = new PropertyRewardItem("20260102", propertyOrderID, "court_b",
                BigInteger.valueOf(99), BigInteger.valueOf(99), BigInteger.valueOf(99),
                BigInteger.valueOf(99), BigInteger.valueOf(9999), "第二次");
        result.append("【propertyReward 第二次】\n").append(formatTxResult(service.propertyReward(new WasteManagerPropertyRewardInputBO(pr2)))).append("\n");

        result.append("\n第二次调用若 returnValue=0 且 idempotent=true 表示幂等命中\n");
        return result.toString();
    }

    /**
     * 测试活动奖励上链、查询、幂等重复调用
     */
    @GetMapping("testActivityReward")
    public String testActivityReward() throws Exception {
        StringBuilder result = new StringBuilder();
        result.append("=== 测试 activityReward ===\n\n");

        String orderID = "test_activity_" + (System.currentTimeMillis() % 100000);
        ActivityRewardItem item1 = new ActivityRewardItem(
                BigInteger.valueOf(System.currentTimeMillis() / 1000),
                orderID,
                "user_act",
                "campaign_test",
                BigInteger.valueOf(150));
        result.append("【第一次上链】\n").append(formatTxResult(service.activityReward(new WasteManagerActivityRewardInputBO(item1)))).append("\n\n");

        Thread.sleep(1000);

        ActivityRewardItem item2 = new ActivityRewardItem(
                BigInteger.valueOf(System.currentTimeMillis() / 1000),
                orderID,
                "user_act_other",
                "campaign_other",
                BigInteger.valueOf(-30));
        result.append("【第二次 相同orderID】\n").append(formatTxResult(service.activityReward(new WasteManagerActivityRewardInputBO(item2)))).append("\n\n");

        Thread.sleep(1000);

        ActivityRewardItem onChain = service.getActivityRewardByOrderID(new WasteManagerGetActivityRewardByOrderIDInputBO(orderID));
        result.append("【查询】\n");
        if (onChain == null) {
            result.append("未查到记录\n");
        } else {
            result.append("userID=").append(onChain.userID)
                    .append(", activityID=").append(onChain.activityID)
                    .append(", resourceCoin=").append(onChain.resourceCoin).append("\n");
            if ("user_act".equals(onChain.userID) && onChain.resourceCoin.equals(BigInteger.valueOf(150))) {
                result.append("【结论】活动奖励幂等生效\n");
            } else {
                result.append("【结论】链上数据与第一次不一致\n");
            }
        }
        return result.toString();
    }

    private String formatTxResult(TransactionResponse response) {
        TransactionReceipt receipt = response.getTransactionReceipt();
        StringBuilder sb = new StringBuilder();
        sb.append(receipt.toString());
        Object returnValue = response.getReturnObject();
        if (returnValue != null) {
            sb.append("\nreturnValue=").append(returnValue);
            if (isIdempotentReturn(returnValue)) {
                sb.append("\nidempotent=true (duplicate orderID, state unchanged)");
            } else {
                sb.append("\nidempotent=false (new record written)");
            }
        }
        return sb.toString();
    }

    private boolean isIdempotentReturn(Object returnValue) {
        if (returnValue instanceof BigInteger) {
            return BigInteger.ZERO.equals(returnValue);
        }
        if (returnValue instanceof Number) {
            return ((Number) returnValue).longValue() == 0L;
        }
        if (returnValue instanceof java.util.List) {
            java.util.List<?> list = (java.util.List<?>) returnValue;
            return !list.isEmpty() && isIdempotentReturn(list.get(0));
        }
        return "0".equals(String.valueOf(returnValue));
    }

}
