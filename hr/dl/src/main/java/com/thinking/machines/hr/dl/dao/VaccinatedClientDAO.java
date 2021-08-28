package com.thinking.machines.hr.dl.dao;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;

import com.thinking.machines.hr.dl.exceptions.*;
import java.util.*;
import java.io.*;
import java.sql.*;
public class VaccinatedClientDAO implements VaccinatedClientDAOInterface
{
public void add(VaccinatedClientDTOInterface vaccinatedClientDTO) throws DAOException
{
String name=vaccinatedClientDTO.getName().trim();
boolean firstDose=vaccinatedClientDTO.getFirstDose();
boolean secondDose=vaccinatedClientDTO.getSecondDose();
java.util.Date dateOfFirstDose=vaccinatedClientDTO.getFirstDoseDate();
java.util.Date dateOfSecondDose=vaccinatedClientDTO.getSecondDoseDate();
int vaccineId=vaccinatedClientDTO.getVaccineId();
String vaccineName;
String aadharCardNumber=vaccinatedClientDTO.getAadharCardNumber();
try
{
VaccineDAOInterface vaccineDAO=new VaccineDAO();
VaccineDTOInterface vaccineDTO=vaccineDAO.getByVaccineId(vaccineId);
vaccineName=vaccineDTO.getVaccineName().trim();
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from vaccinatedClient where aadhar_card_number=?");
preparedStatement.setString(1,aadharCardNumber);
ResultSet resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Aadhar card number : "+aadharCardNumber+" already exist");
}
preparedStatement=connection.prepareStatement("insert into vaccinatedClient (name,first_dose,second_dose,aadhar_card_number,date_of_first_dose,date_of_second_dose,vaccine_id) values(?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
preparedStatement.setString(1,name);
preparedStatement.setBoolean(2,firstDose);
preparedStatement.setBoolean(3,secondDose);
preparedStatement.setString(4,aadharCardNumber);

java.sql.Date sqlDateOfFirstDose=new java.sql.Date(dateOfFirstDose.getYear(),dateOfFirstDose.getMonth(),dateOfFirstDose.getDate());
java.sql.Date sqlDateOfSecondDose=new java.sql.Date(dateOfSecondDose.getYear(),dateOfSecondDose.getMonth(),dateOfSecondDose.getDate());

preparedStatement.setDate(5,sqlDateOfFirstDose);
preparedStatement.setDate(6,sqlDateOfSecondDose);
preparedStatement.setInt(7,vaccineId);
preparedStatement.executeUpdate();
resultSet=preparedStatement.getGeneratedKeys();
resultSet.next();
int clientId=resultSet.getInt(1);
vaccinatedClientDTO.setClientId(clientId);
resultSet.close();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public void update(VaccinatedClientDTOInterface vaccinatedClientDTO) throws DAOException
{
try
{
String name=vaccinatedClientDTO.getName().trim();
int clientId=vaccinatedClientDTO.getClientId();
boolean firstDose=vaccinatedClientDTO.getFirstDose();
boolean secondDose=vaccinatedClientDTO.getSecondDose();
java.util.Date dateOfFirstDose=vaccinatedClientDTO.getFirstDoseDate();
java.util.Date dateOfSecondDose=vaccinatedClientDTO.getSecondDoseDate();
int vaccineId=vaccinatedClientDTO.getVaccineId();
String vaccineName;
String aadharCardNumber=vaccinatedClientDTO.getAadharCardNumber();

if(!clientIdExists(clientId))
{
System.out.println(clientId);
throw new DAOException("Invalid client Id : "+clientId);
}
//check if AadharCardNumber doesn't exist in other clients ignore if it exist with same client id 
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from vaccinatedClient where aadhar_card_number=? and client_id<>?");
preparedStatement.setString(1,aadharCardNumber);
preparedStatement.setInt(2,clientId);
ResultSet resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Aadhar Card Number : "+aadharCardNumber+" exists.");
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("update vaccinatedClient set name=?,first_dose=?,second_dose=?,aadhar_card_number=?,date_of_first_dose=?,date_of_second_dose=?,vaccine_id=? where client_id=?");
preparedStatement.setString(1,name);
preparedStatement.setBoolean(2,firstDose);
preparedStatement.setBoolean(3,secondDose);
preparedStatement.setString(4,aadharCardNumber);

java.sql.Date sqlDateOfFirstDose=new java.sql.Date(dateOfFirstDose.getYear(),dateOfFirstDose.getMonth(),dateOfFirstDose.getDate());
java.sql.Date sqlDateOfSecondDose=new java.sql.Date(dateOfSecondDose.getYear(),dateOfSecondDose.getMonth(),dateOfSecondDose.getDate());

preparedStatement.setDate(5,sqlDateOfFirstDose);
preparedStatement.setDate(6,sqlDateOfSecondDose);
preparedStatement.setInt(7,vaccineId);
preparedStatement.setInt(8,clientId);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public void delete(int clientId) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("delete from vaccinatedClient where client_id=?");
preparedStatement.setInt(1,clientId);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public List<VaccinatedClientDTOInterface> getAll() throws DAOException
{
List<VaccinatedClientDTOInterface> clients;
clients=new LinkedList<>();
try
{
Connection connection=DAOConnection.getConnection();
Statement statement;
statement=connection.createStatement();
ResultSet resultSet;
resultSet=statement.executeQuery("select vaccine.vaccine_id,vaccine.vaccine_name,vaccinatedClient.client_id,vaccinatedClient.name,vaccinatedClient.first_dose,vaccinatedClient.second_dose,vaccinatedClient.aadhar_card_number,vaccinatedClient.date_of_first_dose,vaccinatedClient.date_of_second_dose from vaccinatedClient inner join vaccine on vaccinatedClient.vaccine_id=vaccine.vaccine_id order by vaccinatedClient.name");
VaccinatedClientDTOInterface vaccinatedClientDTO;
while(resultSet.next())
{
vaccinatedClientDTO=new VaccinatedClientDTO();
vaccinatedClientDTO.setClientId(resultSet.getInt("client_id"));
vaccinatedClientDTO.setName(resultSet.getString("name").trim());
vaccinatedClientDTO.setAadharCardNumber(resultSet.getString("aadhar_card_number"));
vaccinatedClientDTO.setFirstDose(resultSet.getBoolean("first_dose"));
vaccinatedClientDTO.setSecondDose(resultSet.getBoolean("second_dose"));
vaccinatedClientDTO.setFirstDoseDate(resultSet.getDate("date_of_first_dose"));
vaccinatedClientDTO.setSecondDoseDate(resultSet.getDate("date_of_second_dose"));
vaccinatedClientDTO.setVaccineId(resultSet.getInt("vaccine_id"));
vaccinatedClientDTO.setVaccineName(resultSet.getString("vaccine_name"));
clients.add(vaccinatedClientDTO);
}
resultSet.close();
statement.close();
connection.close();
}
catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
return clients;

}
public VaccinatedClientDTOInterface getByClientId(int clientId) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from vaccinatedClient where client_id=?");
preparedStatement.setInt(1,clientId);
ResultSet resultSet=preparedStatement.executeQuery();
while(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Client Id : "+clientId+" doesn't exist");
}
VaccinatedClientDTOInterface vaccinatedClientDTO=new VaccinatedClientDTO();
vaccinatedClientDTO.setClientId(resultSet.getInt("client_id"));
vaccinatedClientDTO.setName(resultSet.getString("name").trim());
vaccinatedClientDTO.setAadharCardNumber(resultSet.getString("aadhar_card_number"));
vaccinatedClientDTO.setFirstDose(resultSet.getBoolean("first_dose"));
vaccinatedClientDTO.setSecondDose(resultSet.getBoolean("second_dose"));
vaccinatedClientDTO.setFirstDoseDate(resultSet.getDate("date_of_first_dose"));
vaccinatedClientDTO.setSecondDoseDate(resultSet.getDate("date_of_second_dose"));
vaccinatedClientDTO.setVaccineId(resultSet.getInt("vaccine_id"));
VaccineDAOInterface vaccineDAO=new VaccineDAO();
VaccineDTOInterface vaccineDTO=vaccineDAO.getByVaccineId(vaccinatedClientDTO.getVaccineId());
String vaccineName=vaccineDTO.getVaccineName().trim();
vaccinatedClientDTO.setVaccineName(vaccineName);
resultSet.close();
preparedStatement.close();
connection.close();
return vaccinatedClientDTO;
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}

}
public VaccinatedClientDTOInterface getByAadharCardNumber(String aadharCardNumber) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from vaccinatedClient where aadhar_card_number=?");
preparedStatement.setString(1,aadharCardNumber);
ResultSet resultSet=preparedStatement.executeQuery();
while(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Aadhar Card Number : "+aadharCardNumber+" doesn't exist");
}
VaccinatedClientDTOInterface vaccinatedClientDTO=new VaccinatedClientDTO();
vaccinatedClientDTO.setClientId(resultSet.getInt("client_id"));
vaccinatedClientDTO.setName(resultSet.getString("name").trim());
vaccinatedClientDTO.setFirstDose(resultSet.getBoolean("first_dose"));
vaccinatedClientDTO.setSecondDose(resultSet.getBoolean("second_dose"));
vaccinatedClientDTO.setAadharCardNumber(resultSet.getString("aadhar_card_number"));
vaccinatedClientDTO.setFirstDoseDate(resultSet.getDate("date_of_first_dose"));
vaccinatedClientDTO.setSecondDoseDate(resultSet.getDate("date_of_second_dose"));
vaccinatedClientDTO.setVaccineId(resultSet.getInt("vaccine_id"));
VaccineDAOInterface vaccineDAO=new VaccineDAO();
VaccineDTOInterface vaccineDTO=vaccineDAO.getByVaccineId(vaccinatedClientDTO.getVaccineId());
String vaccineName=vaccineDTO.getVaccineName().trim();
vaccinatedClientDTO.setVaccineName(vaccineName);
resultSet.close();
preparedStatement.close();
connection.close();
return vaccinatedClientDTO;
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}

}
public List<VaccinatedClientDTOInterface> getByVaccineName(String vaccineName)throws DAOException
{
List<VaccinatedClientDTOInterface> clients;
clients=new LinkedList<>();
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select vaccine.vaccine_id,vaccine.vaccine_name,vaccinatedClient.client_id,vaccinatedClient.name,vaccinatedClient.first_dose,vaccinatedClient.second_dose,vaccinatedClient.aadhar_card_number,vaccinatedClient.date_of_first_dose,vaccinatedClient.date_of_second_dose from vaccinatedClient inner join vaccine on vaccinatedClient.vaccine_id=vaccine.vaccine_id where vaccine.vaccine_name=? order by vaccinatedClient.name");
preparedStatement.setString(1,vaccineName);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
VaccinatedClientDTOInterface vaccinatedClientDTO;
while(resultSet.next())
{
vaccinatedClientDTO=new VaccinatedClientDTO();
vaccinatedClientDTO.setClientId(resultSet.getInt("client_id"));
vaccinatedClientDTO.setName(resultSet.getString("name").trim());
vaccinatedClientDTO.setAadharCardNumber(resultSet.getString("aadhar_card_number"));
vaccinatedClientDTO.setFirstDose(resultSet.getBoolean("first_dose"));
vaccinatedClientDTO.setSecondDose(resultSet.getBoolean("second_dose"));
vaccinatedClientDTO.setFirstDoseDate(resultSet.getDate("date_of_first_dose"));
vaccinatedClientDTO.setSecondDoseDate(resultSet.getDate("date_of_second_dose"));
vaccinatedClientDTO.setVaccineId(resultSet.getInt("vaccine_id"));
vaccinatedClientDTO.setVaccineName(resultSet.getString("vaccine_name"));
clients.add(vaccinatedClientDTO);
}
resultSet.close();
preparedStatement.close();
connection.close();
}
catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
return clients;

}
public boolean clientIdExists(int clientId) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from vaccinatedClient where client_id = ?");
preparedStatement.setInt(1,clientId);
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
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
}
public boolean aadharCardNumberExists(String aadharCardNumber) throws DAOException
{
try
{
Connection connection=DAOConnection.getConnection();
PreparedStatement preparedStatement=connection.prepareStatement("select * from vaccinatedClient where aadhar_card_number= ?");
preparedStatement.setString(1,aadharCardNumber);
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
}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}

}

public int getVaccinatedClientCount() throws DAOException
{
int count=0;
try
{
Connection connection=DAOConnection.getConnection();
Statement statement=connection.createStatement();
ResultSet resultSet;
resultSet=statement.executeQuery("select count(*) from vaccinatedClient");
while(resultSet.next())
{
count=resultSet.getInt(1);
}

}catch(SQLException sqlException)
{
throw new DAOException(sqlException.getMessage());
}
return count;
}

}