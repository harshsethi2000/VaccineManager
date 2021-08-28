package com.thinking.machines.hr.dl.interfaces.dao;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import java.util.*;
public interface VaccineDAOInterface
{
public void add(VaccineDTOInterface vaccineDTO) throws DAOException;
public void update(VaccineDTOInterface vaccineDTO) throws DAOException;
public void delete(int vaccineId) throws DAOException;
public List<VaccineDTOInterface> getAll() throws DAOException;
public VaccineDTOInterface getByVaccineId(int vaccineId) throws DAOException;
public VaccineDTOInterface getByVaccineName(String vaccineName) throws DAOException;
public boolean vaccineIdExists(int vaccineId) throws DAOException;
public boolean vaccineNameExists(String vaccineName) throws DAOException;
public int getVaccineCount() throws DAOException;
}