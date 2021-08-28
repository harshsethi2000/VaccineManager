import com.thinking.machines.hr.dl.interfaces.dto.*;
import com.thinking.machines.hr.dl.interfaces.dao.*;
import com.thinking.machines.hr.dl.dto.*;
import com.thinking.machines.hr.dl.dao.*;
import com.thinking.machines.hr.dl.exceptions.*;
class VaccineDeleteTestCase
{
public static void main(String gg[])
{
try
{
VaccineDAOInterface vaccineDAO=new VaccineDAO();
vaccineDAO.delete(7);
System.out.println("Deleted");
}catch(DAOException e)
{
System.out.println(e.getMessage());
}
}
}