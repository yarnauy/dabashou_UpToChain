package org.example.dabashou_spring_demo.controller;

import org.example.dabashou_spring_demo.model.bo.*;
import org.example.dabashou_spring_demo.model.bo.EvidenceItem;
import org.example.dabashou_spring_demo.service.EvidenceManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("evidencemanager")
public class EvidenceManagerController {
    @Autowired
    private EvidenceManagerService service;

    @GetMapping("createEvidence")
    public Object createEvidence() throws Exception {
        EvidenceManagerCreateEvidenceInputBO input = new EvidenceManagerCreateEvidenceInputBO(
                BigInteger.valueOf(1719800000),
                "evidence002",
                new byte[32],
                BigInteger.valueOf(1),
                new byte[0],
                Arrays.asList("备注1", "备注2")
        );
        return service.createEvidence(input).getTransactionReceipt().toString();
    }

    @GetMapping("approveEvidence")
    public Object approveEvidence() throws Exception {
        EvidenceManagerApproveEvidenceInputBO input = new EvidenceManagerApproveEvidenceInputBO(
                BigInteger.valueOf(1719801000),
                "evidence002"
        );
        return service.approveEvidence(input).getTransactionReceipt().toString();
    }

    @GetMapping("verifyEvidence")
    public Object verifyEvidence() throws Exception {
        EvidenceManagerVerifyEvidenceInputBO input = new EvidenceManagerVerifyEvidenceInputBO(
                "evidence002",
                new byte[32]
        );
        return service.verifyEvidence(input).getReturnObject();
    }

    @GetMapping("getEvidence")
    public EvidenceItem getEvidence() throws Exception {
        EvidenceManagerGetEvidenceInputBO input = new EvidenceManagerGetEvidenceInputBO("evidence002");
        return service.getEvidence(input);
    }

    @GetMapping("getApprovals")
    public Object getApprovals() throws Exception {
        EvidenceManagerGetApprovalsInputBO input = new EvidenceManagerGetApprovalsInputBO("evidence001");
        return service.getApprovals(input).getReturnObject();
    }
} 