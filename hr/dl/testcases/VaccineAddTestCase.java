import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
class VaccineAddTestCase
{
public static void main(String gg[])
{
try
{
VaccineDTOInterface vaccineDTO=new VaccineDTO();
vaccineDTO.setVaccineName("pqe");
vaccineDTO.setNumberOfDose(2);
vaccineDTO.setGapBetweenDose(28);
VaccineDAOInterface vaccineDAO=new VaccineDAO();
vaccineDAO.add(vaccineDTO);
System.out.println(vaccineDAO.getVaccineCount());
System.out.println(vaccineDAO.vaccineIdExists(15));
System.out.println(vaccineDAO.vaccineNameExists("COVAXIN"));

}catch(DAOException e)
{
System.out.println(e.getMessage());
}
}
}