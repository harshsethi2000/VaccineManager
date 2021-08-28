package com.thinking.machines.hr.dl.interfaces.dao;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import java.util.*;
public interface VaccinatedClientDAOInterface
{
public void add(VaccinatedClientDTOInterface vaccinatedClientDTO) throws DAOException;
public void update(VaccinatedClientDTOInterface vaccinatedClientDTO) throws DAOException;
public void delete(int clientId) throws DAOException;
public List<VaccinatedClientDTOInterface> getAll() throws DAOException;
public VaccinatedClientDTOInterface getByClientId(int clientId) throws DAOException;
public VaccinatedClientDTOInterface getByAadharCardNumber(String aadharCardNumber) throws DAOException;
public List<VaccinatedClientDTOInterface> getByVaccineName(String vaccineName)throws DAOException;
public boolean clientIdExists(int clientId) throws DAOException;
public boolean aadharCardNumberExists(String aadharCardNumber) throws DAOException;
public int getVaccinatedClientCount() throws DAOException;
}