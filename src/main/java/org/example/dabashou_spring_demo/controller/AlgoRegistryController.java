package org.example.dabashou_spring_demo.controller;


import org.example.dabashou_spring_demo.model.bo.AlgoRegistryRegisterInputBO;
import org.example.dabashou_spring_demo.model.bo.AlgoRegistrySelectInputBO;
import org.example.dabashou_spring_demo.service.AlgoRegistryService;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("algoregistry")
public class AlgoRegistryController {

    @Autowired private AlgoRegistryService service;

    @GetMapping("register")
    public String register(@RequestParam("algoID") String algoID) throws Exception {
        byte[] hash = new byte[32];  //default to 0000000...0000
        BigInteger timestamp = BigInteger.valueOf(1715666453);
        String comments = "test comments";

        AlgoRegistryRegisterInputBO input = new AlgoRegistryRegisterInputBO(timestamp,algoID,hash,comments);
        return service.register(input).getTransactionReceipt().toString();
    }

    @GetMapping("select")
    public Tuple3<BigInteger, byte[], String> select(@RequestParam("from") String algoID) throws Exception {

        AlgoRegistrySelectInputBO input = new AlgoRegistrySelectInputBO(algoID);

        List<Object> returnObj =  service.select( input ).getReturnObject();
        Tuple3<BigInteger, byte[], String> resp = new Tuple3(
                (BigInteger)returnObj.get(0),
                (byte[])returnObj.get(1),
                (String)returnObj.get(2)

        );

       return resp;
    }

}
