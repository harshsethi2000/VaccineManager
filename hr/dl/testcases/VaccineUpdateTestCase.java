import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
class VaccineUpdateTestCase
{
public static void main(String gg[])
{
try
{
VaccineDTOInterface vaccineDTO=new VaccineDTO();
vaccineDTO.setVaccineId(12);
vaccineDTO.setVaccineName("njsjakshj");
vaccineDTO.setNumberOfDose(1);
vaccineDTO.setGapBetweenDose(86);
VaccineDAOInterface vaccineDAO=new VaccineDAO();
vaccineDAO.update(vaccineDTO);
System.out.println("updated");
}catch(DAOException e)
{
System.out.println(e.getMessage());
}
}
}