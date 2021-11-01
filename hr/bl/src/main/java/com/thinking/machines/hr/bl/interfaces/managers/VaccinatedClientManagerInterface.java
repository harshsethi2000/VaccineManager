package com.thinking.machines.hr.bl.interfaces.managers;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;
import java.util.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.dto.*;

public interface VaccinatedClientManagerInterface
{
public void addVaccinatedClient(VaccinatedClientInterface vaccinatedClient)throws BLException;
public void updateVaccinatedClient(VaccinatedClientInterface vaccinatedClient)throws BLException;
public void removeVaccinatedClient(int clientId)throws BLException;
public VaccinatedClientInterface getClientByClientId(int clientId)throws BLException;
public VaccinatedClientInterface getClientByAadharCardNumber(String aadharCardNumber)throws BLException;
public int getClientCount()throws BLException;
public boolean clientIdExists(int clientId)throws BLException;
public boolean clientAadharCardNumberExists(String aadharCardNumber)throws BLException;
public Set<VaccinatedClientInterface> getClients()throws BLException;
public Set<VaccinatedClientInterface> getClientsByVaccineName(String vaccineName)throws BLException;

}
