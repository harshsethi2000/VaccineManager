import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
import java.util.*;
class VaccineGetTestCase
{
public static void main(String gg[])
{
try
{
VaccineDTOInterface vaccineDTO=new VaccineDTO();
VaccineDAOInterface vaccineDAO=new VaccineDAO();
List<VaccineDTOInterface> vaccines;
vaccines=vaccineDAO.getAll();

for(VaccineDTOInterface v:vaccines)
{
System.out.println(v.getVaccineName()+","+v.getVaccineId());
}

}catch(DAOException e)
{
System.out.println(e.getMessage());
}
}
}