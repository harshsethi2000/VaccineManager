import java.util.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
class AddVaccineTestCase
{
public static void main(String gg[])
{
try
{
VaccineManagerInterface vaccineManager=VaccineManager.getVaccineManager();
System.out.println(vaccineManager.getVaccineCount());
VaccineInterface vaccine=new Vaccine();
vaccine.setVaccineId(0);
vaccine.setVaccineName("kl56");
vaccine.setGapBetweenDose(28);
vaccine.setNumberOfDose(2);
vaccineManager.addVaccine(vaccine);
System.out.println(vaccine.getVaccineId());
System.out.println(vaccineManager.getVaccineCount());


}catch(BLException blException)
{
System.out.println(blException.getGenericException());
}
}
}