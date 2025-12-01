package org.example.dabashou_spring_demo.model.bo;

import java.math.BigInteger;
import java.util.List;

public class ContractResponse {
    private BigInteger timestamp;
    private String contractID;
    private byte[] docHash;
    private String creator;
    private List<SignItem> signs;
    private List<AdvanceItem> advances;

    public ContractResponse() {
    }

    public ContractResponse(BigInteger timestamp, String contractID, byte[] docHash, 
                           String creator, List<SignItem> signs, List<AdvanceItem> advances) {
        this.timestamp = timestamp;
        this.contractID = contractID;
        this.docHash = docHash;
        this.creator = creator;
        this.signs = signs;
        this.advances = advances;
    }

    public BigInteger getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(BigInteger timestamp) {
        this.timestamp = timestamp;
    }

    public String getContractID() {
        return contractID;
    }

    public void setContractID(String contractID) {
        this.contractID = contractID;
    }

    public byte[] getDocHash() {
        return docHash;
    }

    public void setDocHash(byte[] docHash) {
        this.docHash = docHash;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<SignItem> getSigns() {
        return signs;
    }

    public void setSigns(List<SignItem> signs) {
        this.signs = signs;
    }

    public List<AdvanceItem> getAdvances() {
        return advances;
    }

    public void setAdvances(List<AdvanceItem> advances) {
        this.advances = advances;
    }

    /**
     * 从ArrayList构造ContractResponse对象
     * @param list 包含ContractResponse字段的ArrayList
     * @return ContractResponse对象，如果构造失败返回null
     */
    public static ContractResponse fromArrayList(java.util.ArrayList<?> list) {
        if (list == null || list.size() < 6) {
            return null;
        }
        
        try {
            // 处理签名列表
            List<SignItem> signs = new java.util.ArrayList<>();
            Object signsObj = list.get(4);
            if (signsObj instanceof java.util.List) {
                java.util.List<?> signsList = (java.util.List<?>) signsObj;
                for (Object signObj : signsList) {
                    if (signObj instanceof java.util.List) {
                        java.util.List<?> signItemList = (java.util.List<?>) signObj;
                        if (signItemList.size() >= 3) {
                            try {
                                SignItem signItem = new SignItem(
                                    (BigInteger) signItemList.get(0),  // timestamp
                                    (String) signItemList.get(1),     // contractID
                                    (String) signItemList.get(2)      // signer
                                );
                                signs.add(signItem);
                            } catch (Exception e) {
                                // 忽略单个转换失败的项目，继续处理下一个
                            }
                        }
                    }
                }
            }
            
            // 处理推进列表
            List<AdvanceItem> advances = new java.util.ArrayList<>();
            Object advancesObj = list.get(5);
            if (advancesObj instanceof java.util.List) {
                java.util.List<?> advancesList = (java.util.List<?>) advancesObj;
                for (Object advanceObj : advancesList) {
                    if (advanceObj instanceof java.util.List) {
                        java.util.List<?> advanceItemList = (java.util.List<?>) advanceObj;
                        if (advanceItemList.size() >= 4) {
                            try {
                                AdvanceItem advanceItem = new AdvanceItem(
                                    (BigInteger) advanceItemList.get(0),  // timestamp
                                    (String) advanceItemList.get(1),     // contractID
                                    (byte[]) advanceItemList.get(2),      // appendHash
                                    (String) advanceItemList.get(3)       // comments
                                );
                                advances.add(advanceItem);
                            } catch (Exception e) {
                                // 忽略单个转换失败的项目，继续处理下一个
                            }
                        }
                    }
                }
            }
            
            return new ContractResponse(
                (BigInteger) list.get(0),  // timestamp
                (String) list.get(1),     // contractID
                (byte[]) list.get(2),     // docHash
                (String) list.get(3),     // creator
                signs,                     // signs
                advances                   // advances
            );
        } catch (Exception e) {
            return null;
        }
    }
} 