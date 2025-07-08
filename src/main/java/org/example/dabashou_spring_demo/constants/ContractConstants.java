package org.example.dabashou_spring_demo.constants;

public class ContractConstants {
  public static String ContractDataAbi;

  public static String ContractDataBinary;

  public static String ContractDataGmBinary;

  public static String RewardDataAbi;

  public static String RewardDataBinary;

  public static String RewardDataGmBinary;

  public static String EvidenceDataAbi;

  public static String EvidenceDataBinary;

  public static String EvidenceDataGmBinary;

  public static String AlgoDataAbi;

  public static String AlgoDataBinary;

  public static String AlgoDataGmBinary;

  public static String WasteDataAbi;

  public static String WasteDataBinary;

  public static String WasteDataGmBinary;

  public static String ResourceCoinManagerAbi;

  public static String ResourceCoinManagerBinary;

  public static String ResourceCoinManagerGmBinary;

  public static String WasteManagerAbi;

  public static String WasteManagerBinary;

  public static String WasteManagerGmBinary;

  public static String EvidenceManagerAbi;

  public static String EvidenceManagerBinary;

  public static String EvidenceManagerGmBinary;

  public static String ContractManagerAbi;

  public static String ContractManagerBinary;

  public static String ContractManagerGmBinary;

  static {
    try {
      ContractDataAbi = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("abi/ContractData.abi"));
      ContractDataBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/ecc/ContractData.bin"));
      ContractDataGmBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/sm/ContractData.bin"));
      RewardDataAbi = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("abi/RewardData.abi"));
      RewardDataBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/ecc/RewardData.bin"));
      RewardDataGmBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/sm/RewardData.bin"));
      EvidenceDataAbi = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("abi/EvidenceData.abi"));
      EvidenceDataBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/ecc/EvidenceData.bin"));
      EvidenceDataGmBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/sm/EvidenceData.bin"));
      AlgoDataAbi = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("abi/AlgoData.abi"));
      AlgoDataBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/ecc/AlgoData.bin"));
      AlgoDataGmBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/sm/AlgoData.bin"));
      WasteDataAbi = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("abi/WasteData.abi"));
      WasteDataBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/ecc/WasteData.bin"));
      WasteDataGmBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/sm/WasteData.bin"));
      ResourceCoinManagerAbi = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("abi/ResourceCoinManager.abi"));
      ResourceCoinManagerBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/ecc/ResourceCoinManager.bin"));
      ResourceCoinManagerGmBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/sm/ResourceCoinManager.bin"));
      WasteManagerAbi = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("abi/WasteManager.abi"));
      WasteManagerBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/ecc/WasteManager.bin"));
      WasteManagerGmBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/sm/WasteManager.bin"));
      EvidenceManagerAbi = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("abi/EvidenceManager.abi"));
      EvidenceManagerBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/ecc/EvidenceManager.bin"));
      EvidenceManagerGmBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/sm/EvidenceManager.bin"));
      ContractManagerAbi = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("abi/ContractManager.abi"));
      ContractManagerBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/ecc/ContractManager.bin"));
      ContractManagerGmBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/sm/ContractManager.bin"));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
