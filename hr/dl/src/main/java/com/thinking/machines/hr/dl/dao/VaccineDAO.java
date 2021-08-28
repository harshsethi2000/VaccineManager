package com.thinking.machines.hr.dl.dao;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.util.*;
import java.io.*;
import java.sql.*;
public class VaccineDAO implements VaccineDAOInterface
{
public void add(VaccineDTOInterface vaccineDTO) throws DAOException
{
int vaccineId;
String vaccineName;
int gapBetweenDose;
int numberOfDose;
try
{
vaccineId=vaccineDTO.getVaccineId();
vaccineName=vaccineDTO.getVaccineName().trim();
gapBetweenDose=vaccineDTO.getGapBetweenDose();
numberOfDose=vaccineDTO.getNumberOfDose();
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from vaccine where vaccine_name=?");
preparedStatement.setString(1,vaccineName);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Vaccine "+vaccineName+" exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("insert into vaccine (vaccine_name,number_of_dose,gap_between_dose) values(?,?,?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,vaccineName);
preparedStatement.setInt(2,numberOfDose);
preparedStatement.setInt(3,gapBetweenDose);
preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
if(resultSet.next())vaccineId=resultSet.getInt(1);
vaccineDTO.setVaccineId(vaccineId);
resultSet.close();
preparedStatement.close();
connection.close();

}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}//add methods end here
public void update(VaccineDTOInterface vaccineDTO) throws DAOException
{
/*
1.check whether vaccine_id exist or not
2.check if update details here vaccineName
*/
int vaccineId=vaccineDTO.getVaccineId();
String vaccineName=vaccineDTO.getVaccineName().trim();
int numberOfDose=vaccineDTO.getNumberOfDose();
int gapBetweenDose=vaccineDTO.getGapBetweenDose();
try
{
if(!vaccineIdExists(vaccineId))
{
throw new DAOException("Invalid vaccine Id: "+vaccineId);
}
if(vaccineNameExists(vaccineName))
{
throw new DAOException("Vaccine Name : "+vaccineName+" exist");
}
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("update vaccine set vaccine_name=? ,number_of_dose=? ,gap_between_dose=? where vaccine_id=? ");
preparedStatement.setString(1,vaccineName);
preparedStatement.setInt(2,numberOfDose);
preparedStatement.setInt(3,gapBetweenDose);
preparedStatement.setInt(4,vaccineId);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}
public void delete(int vaccineId) throws DAOException
{
try
{
if(vaccineId<=0)throw new DAOException("Invalid vaccineId : "+vaccineId);
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("delete from vaccine where vaccine_id=?");
preparedStatement.setInt(1,vaccineId);
int row=preparedStatement.executeUpdate();
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}


public List<VaccineDTOInterface> getAll() throws DAOException
{
List<VaccineDTOInterface> vaccines;
vaccines=new LinkedList<>();
try
{
Connection connection=DAOConnection.getConnection();
Statement statement;
statement=connection.createStatement();
ResultSet resultSet;
resultSet=statement.executeQuery("select * from vaccine order by vaccine_name");
VaccineDTOInterface vaccineDTO;
int vaccineId;
int gapBetweenDose;
int numberOfDose;
String vaccineName;
while(resultSet.next())
{
vaccineId=resultSet.getInt("vaccine_id");
numberOfDose=resultSet.getInt("number_of_dose");
gapBetweenDose=resultSet.getInt("gap_between_dose");
vaccineName=resultSet.getString("vaccine_name").trim();
vaccineDTO=new VaccineDTO();
vaccineDTO.setVaccineName(vaccineName);
vaccineDTO.setNumberOfDose(numberOfDose);
vaccineDTO.setVaccineId(vaccineId);
vaccineDTO.setGapBetweenDose(gapBetweenDose);
vaccines.add(vaccineDTO);
}
resultSet.close();
statement.close();
connection.close();
}
catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
return vaccines;

}

public VaccineDTOInterface getByVaccineId(int vaccineId) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from vaccine where vaccine_id=?");
preparedStatement.setInt(1,vaccineId);
ResultSet resultSet=preparedStatement.executeQuery();
while(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Vaccine Id : "+vaccineId+" doesn't exist");
}
VaccineDTOInterface vaccineDTO=new VaccineDTO();

vaccineDTO.setVaccineId(resultSet.getInt("vaccine_id"));
vaccineDTO.setVaccineName(resultSet.getString("vaccine_name").trim());
vaccineDTO.setNumberOfDose(resultSet.getInt("number_of_dose"));
vaccineDTO.setGapBetweenDose(resultSet.getInt("gap_between_dose"));
resultSet.close();
preparedStatement.close();
connection.close();
return vaccineDTO;
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}
public VaccineDTOInterface getByVaccineName(String vaccineName) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from vaccine where vaccine_name=?");
preparedStatement.setString(1,vaccineName);
ResultSet resultSet=preparedStatement.executeQuery();
while(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Vaccine Name : "+vaccineName+" doesn't exist");
}
VaccineDTOInterface vaccineDTO=new VaccineDTO();

vaccineDTO.setVaccineId(resultSet.getInt("vaccine_id"));
vaccineDTO.setVaccineName(resultSet.getString("vaccine_name").trim());
vaccineDTO.setNumberOfDose(resultSet.getInt("number_of_dose"));
vaccineDTO.setGapBetweenDose(resultSet.getInt("gap_between_dose"));
resultSet.close();
preparedStatement.close();
connection.close();
return vaccineDTO;
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}

}
public boolean vaccineIdExists(int vaccineId) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from vaccine where vaccine_id=?");
preparedStatement.setInt(1,vaccineId);
ResultSet resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
return true;
}
resultSet.close();
preparedStatement.close();
connection.close();
return false;
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}
public boolean vaccineNameExists(String vaccineName) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from vaccine where vaccine_name=?");
preparedStatement.setString(1,vaccineName);
ResultSet resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
return true;
}
resultSet.close();
preparedStatement.close();
connection.close();
return false;
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}
public int getVaccineCount() throws DAOException
{
int count=0;
try
{
Connection connection=DAOConnection.getConnection();
Statement statement=connection.createStatement();
ResultSet resultSet;
resultSet=statement.executeQuery("select count(*) from vaccine");
while(resultSet.next())
{
count=resultSet.getInt(1);
}
}catch(Exception e)
{
throw new DAOException(e.getMessage());
}
return count;
}

}