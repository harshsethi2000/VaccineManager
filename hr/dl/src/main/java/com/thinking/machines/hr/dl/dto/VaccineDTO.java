package com.thinking.machines.hr.dl.dto;
import com.thinking.machines.hr.dl.interfaces.dto.*;
public class VaccineDTO implements VaccineDTOInterface
{
private int vaccineId;
private String vaccineName;
private int numberOfDose;
private int gapBetweenDose;
public VaccineDTO()
{
this.vaccineId=0;
this.vaccineName="";
this.numberOfDose=1;
this.gapBetweenDose=0;

}
public void setVaccineId(int vaccineId)
{
this.vaccineId=vaccineId;
}
public int getVaccineId()
{
return this.vaccineId;
}

public void setVaccineName(String vaccineName)
{
this.vaccineName=vaccineName;
}
public String getVaccineName()
{
return this.vaccineName;
}
public void setNumberOfDose(int numberOfDose)
{
this.numberOfDose=numberOfDose;
}
public int getNumberOfDose()
{
return this.numberOfDose;
}
public void setGapBetweenDose(int gapBetweenDose)
{
this.gapBetweenDose=gapBetweenDose;
}

public int getGapBetweenDose()
{
return this.gapBetweenDose;
}
public int hashCode()
{
return this.vaccineId;
}
public int compareTo(VaccineDTOInterface vaccineDTO)
{
return this.vaccineId-vaccineDTO.getVaccineId();
}
public boolean equals(Object other)
{
if(!(other instanceof VaccineDTOInterface)) return false;
VaccineDTOInterface vaccineDTO;
vaccineDTO=(VaccineDTO)other;
return this.vaccineId==vaccineDTO.getVaccineId();
}
}//vaccineDTO class ends here