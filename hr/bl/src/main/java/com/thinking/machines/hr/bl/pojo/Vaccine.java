package com.thinking.machines.hr.bl.pojo;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
public class Vaccine implements VaccineInterface
{
private int vaccineId;
private String vaccineName;
private int numberOfDose;
private int gapBetweenDose;
public Vaccine()
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
public int compareTo(VaccineInterface vaccine)
{
return this.vaccineId-vaccine.getVaccineId();
}
public boolean equals(Object other)
{
if(!(other instanceof VaccineInterface)) return false;
VaccineInterface vaccine;
vaccine=(Vaccine)other;
return this.vaccineId==vaccine.getVaccineId();
}
}//vaccineDTO class ends here