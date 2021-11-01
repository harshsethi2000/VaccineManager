import java.util.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.dl.exceptions.*;

class GetVaccinatedClient
{
public static void main(String gg[])
{
try
{
VaccinatedClientManagerInterface vaccinatedClientManager=VaccinatedClientManager.getVaccinatedClientManager();
}catch(BLException blException)
{
System.out.println(blException.getGenericException());
}
}
}