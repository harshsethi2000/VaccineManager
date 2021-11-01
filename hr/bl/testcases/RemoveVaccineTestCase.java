import java.util.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
class RemoveVaccineTestCase
{
public static void main(String gg[])
{
try
{
VaccineManagerInterface vaccineManager=VaccineManager.getVaccineManager();
System.out.println(vaccineManager.getVaccineCount());
vaccineManager.removeVaccine(14);
System.out.println(vaccineManager.getVaccineCount());


}catch(BLException blException)
{
System.out.println(blException.getGenericException());
System.out.println("some");

}
}
}