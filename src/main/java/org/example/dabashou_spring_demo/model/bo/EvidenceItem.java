package org.example.dabashou_spring_demo.model.bo;

import java.math.BigInteger;
import java.util.List;

public class EvidenceItem {
    private BigInteger timestamp;
    private String evidenceID;
    private byte[] contentHash;
    private BigInteger mode;
    private byte[] reserved1;
    private List<String> reserved2;
    private String creator;

    public EvidenceItem() {
    }

    public EvidenceItem(BigInteger timestamp, String evidenceID, byte[] contentHash, 
                       BigInteger mode, byte[] reserved1, List<String> reserved2, String creator) {
        this.timestamp = timestamp;
        this.evidenceID = evidenceID;
        this.contentHash = contentHash;
        this.mode = mode;
        this.reserved1 = reserved1;
        this.reserved2 = reserved2;
        this.creator = creator;
    }

    public BigInteger getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(BigInteger timestamp) {
        this.timestamp = timestamp;
    }

    public String getEvidenceID() {
        return evidenceID;
    }

    public void setEvidenceID(String evidenceID) {
        this.evidenceID = evidenceID;
    }

    public byte[] getContentHash() {
        return contentHash;
    }

    public void setContentHash(byte[] contentHash) {
        this.contentHash = contentHash;
    }

    public BigInteger getMode() {
        return mode;
    }

    public void setMode(BigInteger mode) {
        this.mode = mode;
    }

    public byte[] getReserved1() {
        return reserved1;
    }

    public void setReserved1(byte[] reserved1) {
        this.reserved1 = reserved1;
    }

    public List<String> getReserved2() {
        return reserved2;
    }

    public void setReserved2(List<String> reserved2) {
        this.reserved2 = reserved2;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 从ArrayList构造EvidenceItem对象
     * @param list 包含EvidenceItem字段的ArrayList
     * @return EvidenceItem对象，如果构造失败返回null
     */
    public static EvidenceItem fromArrayList(java.util.ArrayList<?> list) {
        if (list == null || list.size() < 7) {
            return null;
        }
        
        try {
            return new EvidenceItem(
                (BigInteger) list.get(0),  // timestamp
                (String) list.get(1),     // evidenceID
                (byte[]) list.get(2),     // contentHash
                (BigInteger) list.get(3), // mode
                (byte[]) list.get(4),     // reserved1
                (List<String>) list.get(5), // reserved2
                (String) list.get(6)      // creator
            );
        } catch (Exception e) {
            return null;
        }
    }
} 