package com.thinking.machines.hr.dl.interfaces.dto;
import java.util.*;
public interface VaccinatedClientDTOInterface extends Comparable<VaccinatedClientDTOInterface>,java.io.Serializable
{
public void setClientId(int clientId);
public int getClientId();
public void setName(java.lang.String name);
public java.lang.String getName();
public void setFirstDose(boolean firstDose);
public boolean getFirstDose();
public void setSecondDose(boolean secondDose);
public boolean getSecondDose();
public void setAadharCardNumber(java.lang.String aadharCardNumber);
public java.lang.String getAadharCardNumber();
public void setFirstDoseDate(Date firstDoseDate);
public Date getFirstDoseDate();
public void setSecondDoseDate(Date secondDoseDate);
public Date getSecondDoseDate();
public void setVaccineId(int vaccineId);
public int getVaccineId();
public void setVaccineName(java.lang.String vaccineNumber);
public java.lang.String getVaccineName();

}