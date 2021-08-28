import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.util.*;
class VaccinatedClientGetTestCase
{
public static void main(String gg[])
{
try
{
VaccinatedClientDTOInterface vaccinatedClientDTO=new VaccinatedClientDTO();
VaccinatedClientDAOInterface vaccinatedClientDAO=new VaccinatedClientDAO();
List<VaccinatedClientDTOInterface> clients;
clients=vaccinatedClientDAO.getByVaccineName("covaxin");

for(VaccinatedClientDTOInterface v:clients)
{
System.out.println(v.getVaccineName()+","+v.getVaccineId());
System.out.println(v.getName()+","+v.getClientId());
}

}catch(DAOException e)
{
System.out.println(e.getMessage());
}
}
}