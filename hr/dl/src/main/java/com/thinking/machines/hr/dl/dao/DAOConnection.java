package com.thinking.machines.hr.dl.dao;
import java.sql.*;
import com.thinking.machines.hr.dl.exceptions.*;
public class DAOConnection
{
private DAOConnection(){};
static public Connection getConnection()throws DAOException
{
Connection connection=null;
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/hsdb","hsdbuser","hsdbuser");
return connection;
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}
}