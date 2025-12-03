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
        String orderID = "RB20251201165401-336";
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


}
