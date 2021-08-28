import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.util.*;
import java.text.SimpleDateFormat;
class VaccinatedClientUpdateTestCase
{
public static void main(String gg[])
{
try
{
VaccinatedClientDTOInterface v=new VaccinatedClientDTO();
v.setClientId(9);
v.setName("Harsh Sethi");
v.setAadharCardNumber("pqedk");
v.setFirstDose(true);
v.setSecondDose(true);
v.setVaccineId(1);
System.out.println(v.getClientId());
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
java.util.Date dateOfFirstDose=sdf.parse("26/08/2000");
v.setFirstDoseDate(dateOfFirstDose);
java.util.Date dateOfSecondDose=sdf.parse("29/08/2000");
v.setSecondDoseDate(dateOfSecondDose);
VaccinatedClientDAOInterface vaccinatedClientDAO=new VaccinatedClientDAO();
System.out.println(vaccinatedClientDAO.getVaccinatedClientCount());
System.out.println(vaccinatedClientDAO.clientIdExists(4));

vaccinatedClientDAO.update(v);
System.out.println("updated");
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