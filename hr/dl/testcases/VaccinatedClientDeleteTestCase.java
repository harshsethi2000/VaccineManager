import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.util.*;
import java.text.SimpleDateFormat;
class VaccinatedClientDeleteTestCase
{
public static void main(String gg[])
{
try
{
VaccinatedClientDAOInterface vaccinatedClientDAO=new VaccinatedClientDAO();
System.out.println(vaccinatedClientDAO.getVaccinatedClientCount());
vaccinatedClientDAO.delete(8);
System.out.println("deleted");
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