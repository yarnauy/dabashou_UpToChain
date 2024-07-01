package org.example.dabashou_spring_demo.controller;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.dabashou_spring_demo.model.bo.*;
import org.example.dabashou_spring_demo.service.AlgoRegistryService;
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


    @GetMapping("dispose")
    public String dispose() throws Exception {
        BigInteger timestamp = BigInteger.valueOf(1719802693);
        String orderID = "order125";
        String userID = "user113";
        String deviceID = "device143";
        String binID = "bin123";
        String courtID = "court113";
        byte[] imagesHash = new byte[32];  //default to 0000000...0000
        byte[] creditParamsHash = new byte[32];  //default to 0000000...0000
        String creditAlgoID = "123";
        String wasteType = "干垃圾";
        BigInteger quantity = BigInteger.valueOf(1230);
        BigInteger score = BigInteger.valueOf(100);
        BigInteger resourceCoin = BigInteger.valueOf(300);

        DisposeItem item = new DisposeItem(timestamp,orderID,userID,deviceID,binID,courtID,imagesHash,creditParamsHash,creditAlgoID,wasteType,quantity,score,resourceCoin);
        WasteManagerDisposeInputBO input = new WasteManagerDisposeInputBO(item);

        return service.dispose(input).getTransactionReceipt().toString();
    }



    @GetMapping("update")
    public String update() throws Exception {
        BigInteger timestamp = BigInteger.valueOf(1715666453);
        String orderID = "order123";
        String userID = "user113";
        String deviceID = "device143";
        String binID = "bin123";
        String courtID = "court113";
        byte[] imagesHash = new byte[32];  //default to 0000000...0000
        byte[] creditParamsHash = new byte[32];  //default to 0000000...0000
        String creditAlgoID = "123";
        String wasteType = "干垃圾";
        BigInteger quantity = BigInteger.valueOf(1230);
        BigInteger score = BigInteger.valueOf(100);
        BigInteger resourceCoin = BigInteger.valueOf(200);
        BigInteger version = BigInteger.valueOf(1);
        String comment = "申诉xxxxx";

        UpdateItem item = new UpdateItem(timestamp,orderID,userID,deviceID,binID,courtID,imagesHash,creditParamsHash,creditAlgoID,wasteType,quantity,score,resourceCoin,version,comment);
        WasteManagerUpdateInputBO input = new WasteManagerUpdateInputBO(item);

        return service.update(input).getTransactionReceipt().toString();
    }


    @GetMapping("issueReward")
    public String issueReward() throws Exception {
        BigInteger timestamp = BigInteger.valueOf(1715666453);
        String orderID = "order123";
        String courtID = "court113";
        BigInteger houseHoldNum = BigInteger.valueOf(1230);
        BigInteger score = BigInteger.valueOf(100);
        BigInteger ratio = BigInteger.valueOf(100);
        BigInteger resourceCoin = BigInteger.valueOf(1);

        RewardItem item = new RewardItem(timestamp,orderID,courtID,houseHoldNum,score,ratio,resourceCoin);
        WasteManagerIssueRewardInputBO input = new WasteManagerIssueRewardInputBO(item);

        return service.issueReward(input).getTransactionReceipt().toString();
    }



    @GetMapping("select")
    public List<Object>  select() throws Exception {

        int year  = 2024;
        int month = 5;
        //select stats in 2024.5
        BigInteger from = BigInteger.valueOf(year*12+month);
        BigInteger to = BigInteger.valueOf(year*12+month);

        WasteManagerSelectInputBO input = new WasteManagerSelectInputBO(from ,to);
        List<Object> returnObj =  service.select(input).getReturnObject();
//        StatsItem[] resp = (StatsItem[])returnObj.get(0);

        return returnObj;
    }




}
