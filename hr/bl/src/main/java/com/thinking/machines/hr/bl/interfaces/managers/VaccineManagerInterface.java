package com.thinking.machines.hr.bl.interfaces.managers;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;
import java.util.*;

public interface VaccineManagerInterface
{
public void addVaccine(VaccineInterface vaccine)throws BLException;
public void updateVaccine(VaccineInterface vaccine)throws BLException;
public void removeVaccine(int vaccineId)throws BLException;
public VaccineInterface getVaccineByVaccineId(int vaccineId)throws BLException;
public VaccineInterface getVaccineByVaccineName(String vaccineName)throws BLException;
public int getVaccineCount();
public boolean vaccineIdExists(int vaccineId);
public boolean vaccineNameExists(String vaccineName);
public Set<VaccineInterface> getVaccines();

}
