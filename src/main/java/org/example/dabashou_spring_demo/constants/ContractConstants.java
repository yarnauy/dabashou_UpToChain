package org.example.dabashou_spring_demo.constants;

import java.lang.Exception;
import java.lang.RuntimeException;
import java.lang.String;

public class ContractConstants {
  public static String StatsDataAbi;

  public static String StatsDataBinary;

  public static String StatsDataGmBinary;

  public static String WasteManagerAbi;

  public static String WasteManagerBinary;

  public static String WasteManagerGmBinary;

  public static String AlgoRegistryAbi;

  public static String AlgoRegistryBinary;

  public static String AlgoRegistryGmBinary;

  static {
    try {
      StatsDataAbi = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("abi/StatsData.abi"));
      StatsDataBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/ecc/StatsData.bin"));
      StatsDataGmBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/sm/StatsData.bin"));
      WasteManagerAbi = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("abi/WasteManager.abi"));
      WasteManagerBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/ecc/WasteManager.bin"));
      WasteManagerGmBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/sm/WasteManager.bin"));
      AlgoRegistryAbi = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("abi/AlgoRegistry.abi"));
      AlgoRegistryBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/ecc/AlgoRegistry.bin"));
      AlgoRegistryGmBinary = org.apache.commons.io.IOUtils.toString(Thread.currentThread().getContextClassLoader().getResource("bin/sm/AlgoRegistry.bin"));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
