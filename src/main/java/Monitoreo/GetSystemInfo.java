package Monitoreo;

import org.hyperic.sigar.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GetSystemInfo {
  private static Sigar sigar = new Sigar();

  public static void main(String[] args) {
    SystemInfo si = new SystemInfo();

    System.out.println("Nombre Equipo : " + si.getNetInfo().getHostName());
    System.out.println("Sistema operativo : " + si.getOs().toMap());
  }

  public static SysInfo getOs() {
    SysInfo sysInfo = new SysInfo();
    try {
      sysInfo.gather(sigar);

    } catch (SigarException se) {
      se.printStackTrace();
    }
    return sysInfo;
  }

  public static NetInfo getNetInfo() {
    NetInfo netInfo = new NetInfo();
    try {
      netInfo.gather(sigar);

    } catch (SigarException se) {
      se.printStackTrace();
    }
    return netInfo;
  }

  static class SystemInfo {
    CpuInfo[] procesadores;
    String ram;
    SysInfo os;
    NetInfo netInfo;

    public CpuInfo[] getProcesadores() {
      return procesadores;
    }

    public void setProcesadores(CpuInfo[] procesadores) {
      this.procesadores = procesadores;
    }

    public String getRam() {
      return ram;
    }

    public void setRam(String ram) {
      this.ram = ram;
    }

    public SysInfo getOs() {
      return os;
    }

    public void setOs(SysInfo os) {
      this.os = os;
    }

    public NetInfo getNetInfo() {
      return netInfo;
    }

    public void setNetInfo(NetInfo netInfo) {
      this.netInfo = netInfo;
    }

    SystemInfo() {
      os = GetSystemInfo.getOs();
      netInfo = GetSystemInfo.getNetInfo();
    }
  }
}