package Monitoreo;

import org.hyperic.sigar.*;

public class Prueba2 {

  public static void main(String[] args) throws SigarException {
    cpu();
  }

  private static void cpu() throws SigarException {
    Sigar sigar = new Sigar();

    CpuPerc perc = sigar.getCpuPerc();
    System.out.println("整体cpu的占用情况:");
    System.out.println("空闲率: " + CpuPerc.format(perc.getIdle()));// 获取当前cpu的空闲率
    System.out.println("占用率: " + CpuPerc.format(perc.getCombined()));// 获取当前cpu的占用率

    CpuInfo infos[] = sigar.getCpuInfoList();
    CpuPerc cpuList[] = null;
    cpuList = sigar.getCpuPercList();
    for (int i = 0; i < infos.length; i++) {// 不管是单块CPU还是多CPU都适用
      CpuInfo info = infos[i];
      System.out.println("第" + (i + 1) + "块CPU信息");
      System.out.println("CPU的总量MHz:    " + info.getMhz());// CPU的总量MHz
      System.out.println("CPU生产商:    " + info.getVendor());// 获得CPU的卖主，如：Intel
      System.out.println("CPU类别:    " + info.getModel());// 获得CPU的类别，如：Celeron
      System.out.println("CPU缓存数量:    " + info.getCacheSize());// 缓冲存储器数量
      System.out.println(cpuList[i]);
    }
  }

}
