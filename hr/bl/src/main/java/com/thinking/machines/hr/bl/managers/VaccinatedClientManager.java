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
public class VaccinatedClientManager implements VaccinatedClientManagerInterface
{
private static VaccinatedClientManager vaccinatedClientManager=null;
private Map<Integer,VaccinatedClientInterface> clientIdWiseVaccinatedClientsMap;
private Map<String,VaccinatedClientInterface> aadharCardNumberWiseVaccinatedClientsMap;
private Set<VaccinatedClientInterface> vaccinatedClientsSet;
private VaccinatedClientManager()throws BLException
{
populateDataStructure();
}
private void populateDataStructure()throws BLException
{
this.clientIdWiseVaccinatedClientsMap=new HashMap<>();
this.aadharCardNumberWiseVaccinatedClientsMap=new HashMap<>();
this.vaccinatedClientsSet=new TreeSet<>();
try
{
VaccinatedClientDAOInterface vaccinatedClientDAO=new VaccinatedClientDAO();
List<VaccinatedClientDTOInterface> vaccinatedClients=vaccinatedClientDAO.getAll();
VaccinatedClientInterface vaccinatedClient;
for(VaccinatedClientDTOInterface v:vaccinatedClients)
{
vaccinatedClient=new VaccinatedClient();
vaccinatedClient.setClientId(v.getClientId());
vaccinatedClient.setName(v.getName());
vaccinatedClient.setFirstDose(v.getFirstDose());
vaccinatedClient.setSecondDose(v.getSecondDose());
vaccinatedClient.setAadharCardNumber(v.getAadharCardNumber());
vaccinatedClient.setFirstDoseDate(v.getFirstDoseDate());
vaccinatedClient.setSecondDoseDate(v.getSecondDoseDate());
vaccinatedClient.setVaccineId(v.getVaccineId());
vaccinatedClient.setVaccineName(v.getVaccineName());
this.clientIdWiseVaccinatedClientsMap.put(vaccinatedClient.getClientId(),vaccinatedClient);
this.aadharCardNumberWiseVaccinatedClientsMap.put(vaccinatedClient.getAadharCardNumber(),vaccinatedClient);
this.vaccinatedClientsSet.add(vaccinatedClient);
System.out.println("Populated DS successfully");
}
}catch(DAOException daoException)
{
BLException blException=new BLException();
blException.setGenericException(daoException.getMessage());
throw blException;
}
}
public static VaccinatedClientManagerInterface getVaccinatedClientManager()throws BLException
{
if(vaccinatedClientManager==null)vaccinatedClientManager=new VaccinatedClientManager();
return vaccinatedClientManager;
}
public void addVaccinatedClient(VaccinatedClientInterface vaccinatedClient)throws BLException
{
BLException blException=new BLException();
blException.setGenericException("not implemented");
throw blException;
}
public void updateVaccinatedClient(VaccinatedClientInterface vaccinatedClient)throws BLException
{
BLException blException=new BLException();
blException.setGenericException("not implemented");
throw blException;
}
public void removeVaccinatedClient(int clientId)throws BLException
{
BLException blException=new BLException();
blException.setGenericException("not implemented");
throw blException;
}
public VaccinatedClientInterface getClientByClientId(int clientId)throws BLException
{
BLException blException=new BLException();
blException.setGenericException("not implemented");
throw blException;
}
public VaccinatedClientInterface getClientByAadharCardNumber(String aadharCardNumber)throws BLException
{
BLException blException=new BLException();
blException.setGenericException("not implemented");
throw blException;
}
public int getClientCount()throws BLException
{
BLException blException=new BLException();
blException.setGenericException("not implemented");
throw blException;
}
public boolean clientIdExists(int clientId)throws BLException
{
return this.clientIdWiseVaccinatedClientsMap.containsKey(clientId);
}
public boolean clientAadharCardNumberExists(String aadharCardNumber)throws BLException
{
return this.aadharCardNumberWiseVaccinatedClientsMap.containsKey(aadharCardNumber);
}
public Set<VaccinatedClientInterface> getClients()throws BLException
{
BLException blException=new BLException();
blException.setGenericException("not implemented");
throw blException;
}
public Set<VaccinatedClientInterface> getClientsByVaccineName(String vaccineName)throws BLException
{
BLException blException=new BLException();
blException.setGenericException("not implemented");
throw blException;
}


}