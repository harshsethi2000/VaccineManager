import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.util.*;
import java.text.SimpleDateFormat;
class VaccinatedClientAddTestCase
{
public static void main(String gg[])
{
try
{
VaccinatedClientDTOInterface v=new VaccinatedClientDTO();
v.setName("Harsh Sethi");
v.setAadharCardNumber("Kgap656");
v.setFirstDose(true);
v.setSecondDose(true);
v.setVaccineId(1);
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
java.util.Date dateOfFirstDose=sdf.parse("26/08/2000");
v.setFirstDoseDate(dateOfFirstDose);
java.util.Date dateOfSecondDose=sdf.parse("29/08/2000");
v.setSecondDoseDate(dateOfSecondDose);
VaccinatedClientDAOInterface vaccinatedClientDAO=new VaccinatedClientDAO();
System.out.println(vaccinatedClientDAO.getVaccinatedClientCount());
vaccinatedClientDAO.add(v);
System.out.println("added");
System.out.println(vaccinatedClientDAO.getVaccinatedClientCount());

}catch(DAOException e)
{
System.out.println(e.getMessage());
}
catch(Exception e)
{

}
}
}