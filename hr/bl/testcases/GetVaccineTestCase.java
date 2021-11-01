import java.util.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
class GetVaccineTestCase
{
public static void main(String gg[])
{
try
{
VaccineManagerInterface vaccineManager=VaccineManager.getVaccineManager();
System.out.println(vaccineManager.getVaccineCount());
}catch(BLException blException)
{
System.out.println(blException.getGenericException());
}
}
}