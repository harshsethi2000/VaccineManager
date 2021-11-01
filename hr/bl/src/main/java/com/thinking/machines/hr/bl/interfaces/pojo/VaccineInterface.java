package com.thinking.machines.hr.bl.interfaces.pojo;
public interface VaccineInterface extends Comparable<VaccineInterface>,java.io.Serializable
{
public void setVaccineId(int vaccineId);
public int getVaccineId();
public void setVaccineName(String vaccineName);
public String getVaccineName();
public void setNumberOfDose(int numberOfDose);
public int getNumberOfDose();
public void setGapBetweenDose(int gapBetweenDose);
public int getGapBetweenDose();
}
