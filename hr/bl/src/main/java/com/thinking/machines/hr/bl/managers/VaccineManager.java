package com.thinking.machines.hr.bl.managers;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import java.util.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
public class VaccineManager implements VaccineManagerInterface
{

private Map<Integer,VaccineInterface> idWiseVaccinesMap;
private Map<String,VaccineInterface> nameWiseVaccinesMap;
private Set<VaccineInterface> vaccinesSet;
private static VaccineManagerInterface vaccineManager=null;
private VaccineManager()throws BLException
{
/*private because
no one can access
no one can derieve this class
*/
populateDataStructure();
}
private void populateDataStructure()throws BLException
{

this.idWiseVaccinesMap=new HashMap<>();
this.nameWiseVaccinesMap=new HashMap<>();
this.vaccinesSet=new TreeSet<>();
// hashMap because performance is priority
//treeSet beacuse sorted order is required
try
{
List<VaccineDTOInterface> dlVaccines;
VaccineDAOInterface vaccineDAO=new VaccineDAO();
dlVaccines=vaccineDAO.getAll();
VaccineInterface vaccine;
for(VaccineDTOInterface dlVaccine:dlVaccines)
{
vaccine=new Vaccine();
vaccine.setVaccineId(dlVaccine.getVaccineId());
vaccine.setVaccineName(dlVaccine.getVaccineName());
vaccine.setNumberOfDose(dlVaccine.getNumberOfDose());
vaccine.setGapBetweenDose(dlVaccine.getGapBetweenDose());
this.idWiseVaccinesMap.put(new Integer(vaccine.getVaccineId()),vaccine);
this.nameWiseVaccinesMap.put(vaccine.getVaccineName().toUpperCase(),vaccine);
this.vaccinesSet.add(vaccine);

}
}catch(DAOException daoException)
{
BLException blException=new BLException();
blException.setGenericException(daoException.getMessage());
throw blException;
}
}
public static VaccineManagerInterface getVaccineManager()throws BLException
{
if(vaccineManager==null)vaccineManager=new VaccineManager();
return vaccineManager;
}

public void addVaccine(VaccineInterface vaccine)throws BLException
{
int vaccineId;
String vaccineName;
int numberOfDose;
int gapBetweenDose;
BLException blException;
blException=new BLException();
try
{
if(vaccine==null)
{
blException.setGenericException("Vaccine Required");
throw blException;
}
vaccineId=vaccine.getVaccineId();
vaccineName=vaccine.getVaccineName();
numberOfDose=vaccine.getNumberOfDose();
gapBetweenDose=vaccine.getGapBetweenDose();
if(vaccineId!=0)blException.addException("vaccineId","Vaccine Id should be 0");
if(vaccineName==null)
{
blException.addException("vaccineName","vaccine name required");
vaccineName="";
}
vaccineName=vaccineName.trim();
if(vaccineName.length()==0)blException.addException("vaccineName","Vaccine Name should not be null");
if(vaccineName.length()>0 && this.vaccineNameExists(vaccineName))blException.addException("vaccineName","Vaccine " +vaccineName+ " already exist");
if(gapBetweenDose<0)blException.addException("gapBetweenDose","Gap between dose should not be less than 0");
if(numberOfDose<0)blException.addException("gapBetweenDose","Number of dose should not be less than 0");

if(blException.hasExceptions())
{
throw blException;
}
VaccineDTOInterface vaccineDTO=new VaccineDTO();
vaccineDTO.setVaccineName(vaccineName);
vaccineDTO.setNumberOfDose(numberOfDose);
vaccineDTO.setGapBetweenDose(gapBetweenDose);
VaccineDAOInterface vaccineDAO=new VaccineDAO();
vaccineDAO.add(vaccineDTO);
vaccineId=vaccineDTO.getVaccineId();
VaccineInterface tmpVaccine=new Vaccine();
tmpVaccine.setVaccineName(vaccineName);
tmpVaccine.setNumberOfDose(numberOfDose);
tmpVaccine.setNumberOfDose(gapBetweenDose);
tmpVaccine.setVaccineId(vaccineId);
this.idWiseVaccinesMap.put(new Integer(vaccine.getVaccineId()),tmpVaccine);
this.nameWiseVaccinesMap.put(vaccine.getVaccineName().toUpperCase(),tmpVaccine);
this.vaccinesSet.add(tmpVaccine);
vaccine.setVaccineId(vaccineId);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}

public void updateVaccine(VaccineInterface vaccine)throws BLException
{
BLException blException=new BLException();
if(vaccine==null)
{
blException.setGenericException("Vaccine Required");
throw blException;
}
int vaccineId=vaccine.getVaccineId();
String vaccineName=vaccine.getVaccineName();
int numberOfDose=vaccine.getNumberOfDose();
int gapBetweenDose=vaccine.getGapBetweenDose();
if(vaccineId<=0)blException.addException("vaccineId","Invalid Vaccine Id");
if(vaccineId>0 && !vaccineIdExists(vaccineId))blException.addException("vaccineId","Invalid Vaccine Id");
if(vaccineName==null)
{
blException.addException("vaccineName","vaccine name required");
vaccineName="";
}
vaccineName=vaccineName.trim();
if(vaccineName.length()==0)blException.addException("vaccineName","Vaccine Name should not be null");
if(vaccineName.length()>0 && this.vaccineNameExists(vaccineName))
{ 
VaccineInterface vVaccine=this.nameWiseVaccinesMap.get(vaccineName.toUpperCase());

if(vVaccine.getVaccineId()!=vaccineId)
{

blException.addException("vaccineName","Vaccine name "+vaccineName+" already exits");
}
}
if(gapBetweenDose<0)blException.addException("gapBetweenDose","Gap between dose should not be less than 0");
if(numberOfDose<0)blException.addException("gapBetweenDose","Number of dose should not be less than 0");
if(blException.hasExceptions())throw blException;
try
{
/*
Remove old data from DS
add new data in dl
add new data in DS
*/

VaccineDTOInterface tmpVaccine=new VaccineDTO();
tmpVaccine.setVaccineId(vaccineId);
tmpVaccine.setVaccineName(vaccineName);
tmpVaccine.setGapBetweenDose(gapBetweenDose);
tmpVaccine.setNumberOfDose(numberOfDose);
VaccineDAOInterface vaccineDAO=new VaccineDAO();
vaccineDAO.update(tmpVaccine);
VaccineInterface vaccineI=idWiseVaccinesMap.get(vaccineId);
idWiseVaccinesMap.remove(vaccineId);
nameWiseVaccinesMap.remove(vaccineName.toUpperCase());

vaccinesSet.remove(vaccineI);
vaccineI.setVaccineId(vaccineId);
vaccineI.setVaccineName(vaccineName);
vaccineI.setGapBetweenDose(gapBetweenDose);
vaccineI.setNumberOfDose(numberOfDose);

idWiseVaccinesMap.put(vaccineId,vaccineI);
nameWiseVaccinesMap.put(vaccineName,vaccineI);
vaccinesSet.add(vaccineI);

}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}
public void removeVaccine(int vaccineId)throws BLException
{
BLException blException=new BLException();
if(vaccineId<=0)blException.addException("vaccineId","Invalid Vaccine Id");
if(vaccineId>0 && !vaccineIdExists(vaccineId))blException.addException("vaccineId","Invalid Vaccine Id");
if(blException.hasExceptions())throw blException;
try
{
VaccineInterface vaccine=idWiseVaccinesMap.get(vaccineId);
VaccineDAOInterface vaccineDAO=new VaccineDAO();
VaccineDTOInterface vaccineDTO=new VaccineDTO();
vaccineDTO.setVaccineId(vaccineId);
vaccineDAO.delete(vaccineId);
//remove deleted from ds
idWiseVaccinesMap.remove(vaccineId);
nameWiseVaccinesMap.remove(vaccine.getVaccineName());
vaccinesSet.remove(vaccine);
}catch(DAOException daoException)
{
blException.setGenericException(daoException.getMessage());
throw blException;
}
}
public VaccineInterface getVaccineByVaccineId(int vaccineId)throws BLException
{
VaccineInterface vaccine;
vaccine=this.idWiseVaccinesMap.get(vaccineId);
if(vaccine==null)
{
BLException blException=new BLException();
blException.addException("vaccineId","Invalid vaccine Id : "+vaccineId);
throw blException;
}
return vaccine;
}
public VaccineInterface getVaccineByVaccineName(String vaccineName)throws BLException
{
VaccineInterface vaccine;
vaccine=this.nameWiseVaccinesMap.get(vaccineName);
if(vaccine==null)
{
BLException blException=new BLException();
blException.addException("vaccineName","Invalid vaccine name : "+vaccineName);
throw blException;
}
return vaccine;
}
public int getVaccineCount()
{
return this.vaccinesSet.size();
}
public boolean vaccineIdExists(int vaccineId)
{
return this.idWiseVaccinesMap.containsKey(vaccineId);
}
public boolean vaccineNameExists(String vaccineName)
{
return this.nameWiseVaccinesMap.containsKey(vaccineName.toUpperCase());
}
public Set<VaccineInterface> getVaccines()
{
Set<VaccineInterface> tmpSet=new TreeSet<>();
VaccineInterface vaccine;
for(VaccineInterface v: this.vaccinesSet)
{
vaccine=new Vaccine();
vaccine.setVaccineName(v.getVaccineName());
vaccine.setVaccineId(v.getVaccineId());
vaccine.setGapBetweenDose(v.getGapBetweenDose());
vaccine.setNumberOfDose(v.getNumberOfDose());
tmpSet.add(vaccine);
}

return tmpSet;
}

}