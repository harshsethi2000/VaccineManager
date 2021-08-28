package com.thinking.machines.hr.dl.dto;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.util.*;
public class VaccinatedClientDTO implements VaccinatedClientDTOInterface
{
private int clientId;
private String name;
private boolean firstDose;
private boolean secondDose;
private String aadharCardNumber;
private Date firstDoseDate;
private Date secondDoseDate;
private int vaccineId;
private String vaccineName;
public VaccinatedClientDTO()
{
this.clientId=0;
this.name="";
this.firstDose=false;
this.secondDose=false;
this.aadharCardNumber="";
this.firstDoseDate=null;
this.secondDoseDate=null;
this.vaccineId=0;
this.vaccineName="";
}
public void setClientId(int clientId)
{
this.clientId=clientId;
}
public int getClientId()
{
return this.clientId;
}
public void setName(java.lang.String name)
{
this.name=name;
}
public java.lang.String getName()
{
return this.name;
}
public void setFirstDose(boolean firstDose)
{
this.firstDose=firstDose;
}
public boolean getFirstDose()
{
return this.firstDose;
}
public void setSecondDose(boolean secondDose)
{
this.secondDose=secondDose;
}
public boolean getSecondDose()
{
return this.secondDose;
}
public void setAadharCardNumber(java.lang.String aadharCardNumber)
{
this.aadharCardNumber=aadharCardNumber;
}
public java.lang.String getAadharCardNumber()
{
return this.aadharCardNumber;
}
public void setFirstDoseDate(Date firstDoseDate)
{
this.firstDoseDate=firstDoseDate;
}
public Date getFirstDoseDate()
{
return this.firstDoseDate;
}
public void setSecondDoseDate(Date secondDoseDate)
{
this.secondDoseDate=secondDoseDate;
}
public Date getSecondDoseDate()
{
return this.secondDoseDate;
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
public int hashCode()
{
return this.clientId;
}
public boolean equals(Object other)
{
if(!(other instanceof VaccinatedClientDTOInterface)) return false;
VaccinatedClientDTOInterface vaccinatedClientDTO;
vaccinatedClientDTO=(VaccinatedClientDTO)other;
return this.clientId==vaccinatedClientDTO.getClientId();
}
public int compareTo(VaccinatedClientDTOInterface vaccinatedClientDTO)
{
return this.clientId-vaccinatedClientDTO.getClientId();
}

}