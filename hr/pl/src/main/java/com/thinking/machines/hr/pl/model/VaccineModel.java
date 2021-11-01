package com.thinking.machines.hr.pl.model;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.dl.exceptions.*;
import com.thinking.machines.hr.bl.pojo.*;
import com.thinking.machines.hr.bl.managers.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.interfaces.managers.*;
import java.util.*;
import javax.swing.table.*;
public class VaccineModel extends AbstractTableModel
{
private List<VaccineInterface> vaccines;
private VaccineManagerInterface vaccineManager;
private String[] columnTitle;
public VaccineModel()
{
//populate ds
this.populateDataStructures();
} 
private void populateDataStructures()
{
this.columnTitle=new String[4];
this.columnTitle[0]="S.No.";
this.columnTitle[1]="Vaccine";
this.columnTitle[2]="Number Of Dose";
this.columnTitle[3]="Gap Between Dose";

try
{
vaccineManager=VaccineManager.getVaccineManager();
}catch(BLException blException)
{
System.out.println("someException"+blException.getGenericException());
}
Set<VaccineInterface> blVaccines=vaccineManager.getVaccines();
this.vaccines=new LinkedList<>();
for(VaccineInterface vaccine:blVaccines)
{
this.vaccines.add(vaccine);
System.out.println(vaccine.getVaccineName()+vaccine.getGapBetweenDose());
}
Collections.sort(this.vaccines,new Comparator<VaccineInterface>(){
public int compare(VaccineInterface left,VaccineInterface right)
{
return left.getVaccineName().toUpperCase().compareTo(right.getVaccineName().toUpperCase());
}
});
}
public int getRowCount()
{
return vaccines.size();
}
public int getColumnCount()
{
return this.columnTitle.length;
}
public String getColumnName(int columnIndex)
{
return columnTitle[columnIndex];
}
public Object getValueAt(int rowIndex,int columnIndex)
{
if(columnIndex==0)return rowIndex+1;
if(columnIndex==1)return this.vaccines.get(rowIndex).getVaccineName();
if(columnIndex==2)return this.vaccines.get(rowIndex).getNumberOfDose();
return this.vaccines.get(rowIndex).getGapBetweenDose();

}
public Class getColumnClass(int columnIndex)
{
if(columnIndex==1)return String.class;
return Integer.class;
}
public boolean isCellEditable(int rowIndex,int columnIndex)
{
return false;
}
//
public int indexOfVaccine(String vaccineName,boolean partialLeftSearch)throws BLException
{
Iterator<VaccineInterface> iterator=this.vaccines.iterator();
VaccineInterface d;
int index=0;
while(iterator.hasNext())
{
d=iterator.next();
if(partialLeftSearch)
{
if(d.getVaccineName().toUpperCase().startsWith(vaccineName.toUpperCase()))
{
return index;
}
}
else
{
if(d.getVaccineName().equalsIgnoreCase(vaccineName))
{
return index;
}
}
index++;
}
//designation not found
BLException blException=new BLException();
blException.setGenericException("Invalid Vaccine Name"+vaccineName);
throw blException;
}
public VaccineInterface getVaccine(int index)throws BLException
{
if(index<0 || index >=this.vaccines.size())
{
BLException blException=new BLException();
blException.setGenericException("Inavlid Index");
throw blException;
}
return this.vaccines.get(index);
}

}