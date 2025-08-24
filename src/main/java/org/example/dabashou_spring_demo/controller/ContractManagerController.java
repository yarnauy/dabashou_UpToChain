package org.example.dabashou_spring_demo.controller;

import org.example.dabashou_spring_demo.model.bo.*;
import org.example.dabashou_spring_demo.model.bo.ContractResponse;
import org.example.dabashou_spring_demo.model.bo.SignItem;
import org.example.dabashou_spring_demo.model.bo.AdvanceItem;
import org.example.dabashou_spring_demo.service.ContractManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("contractmanager")
public class ContractManagerController {
    @Autowired
    private ContractManagerService service;

    @GetMapping("initContract")
    public Object initContract() throws Exception {
        ContractItem item = new ContractItem(BigInteger.valueOf(1719800000), "contract001", new byte[32]);
        ContractManagerInitContractInputBO input = new ContractManagerInitContractInputBO(item);
        return service.initContract(input).getTransactionReceipt().toString();
    }

    @GetMapping("signContract")
    public Object signContract() throws Exception {
        ContractManagerSignContractInputBO input = new ContractManagerSignContractInputBO(BigInteger.valueOf(1719801000), "contract001");
        return service.signContract(input).getTransactionReceipt().toString();
    }

    @GetMapping("advanceContract")
    public Object advanceContract() throws Exception {
        AdvanceItem item = new AdvanceItem(BigInteger.valueOf(1719802000), "contract001", new byte[32], "推进说明");
        ContractManagerAdvanceContractInputBO input = new ContractManagerAdvanceContractInputBO(item);
        return service.advanceContract(input).getTransactionReceipt().toString();
    }

    @GetMapping("getContractInfo")
    public ContractResponse getContractInfo() throws Exception {
        ContractManagerGetContractInfoInputBO input = new ContractManagerGetContractInfoInputBO("contract001");
        return service.getContractInfo(input);
    }
} 