package com.thinking.machines.hr.pl.ui;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.io.*;
import com.thinking.machines.hr.pl.model.*;
import com.thinking.machines.hr.bl.exceptions.*;
import com.thinking.machines.hr.bl.interfaces.pojo.*;
import com.thinking.machines.hr.bl.pojo.*;

public class VaccinationManagerUI extends JFrame implements DocumentListener,ListSelectionListener
{
private Container container;
private JPanel leftPanel;
private JPanel topPanel;
private JPanel rightPanel;
private JLabel headerLabel;
private JLabel titleLabel;
private JLabel searchLabel;
private JTextField searchTextField;
private JLabel searchErrorLabel;
private JButton searchButton;
private JButton navigateToHomeButton;
private JButton navigateToVaccinesButton;
private JButton navigateToClientsButton;
private ImageIcon homeIcon;
private ImageIcon vaccineIcon;
private ImageIcon clientIcon;
private ImageIcon addIcon;
private ImageIcon deleteIcon;
private ImageIcon editIcon;
private ImageIcon updateIcon;
private ImageIcon pdfIcon;
private ImageIcon clearIcon;
private ImageIcon cancelIcon;
private ImageIcon saveIcon;
private VaccineModel vaccineModel;
private JTable vaccineTable;
private JScrollPane scrollPane;
private VaccinePanel vaccinePanel;
private enum RightPanelMode{HOME,VACCINES,CLIENTS};
private RightPanelMode rightPanelMode;
private enum MODE{ADD,EDIT,DELETE,VIEW,EXPORT_TO_PDF};
private MODE mode;
public VaccinationManagerUI()
{
initComponents();
setAppearance();
addListeners();
this.rightPanelMode=RightPanelMode.HOME;
setViewMode();
vaccinePanel.setViewMode();
}
private void initComponents()
{
vaccineModel=new VaccineModel();
container=getContentPane();
leftPanel=new JPanel();
topPanel=new JPanel();
rightPanel=new JPanel();
searchTextField=new JTextField();
headerLabel=new JLabel("Vaccine Manager");
searchLabel=new JLabel("Search");
searchErrorLabel=new JLabel("");
titleLabel=new JLabel("Vaccines");
homeIcon=new ImageIcon("home.png","ssh");
vaccineIcon=new ImageIcon("vaccine.png","ssh");
clientIcon=new ImageIcon("client.png","ssh");
addIcon=new ImageIcon("add_icon.png","ssh");
deleteIcon=new ImageIcon("delete_icon.png","ssh");
editIcon=new ImageIcon("edit_icon.png","ssh");
pdfIcon=new ImageIcon("pdf_icon.png","ssh");
clearIcon=new ImageIcon("clear_icon.png","ssh");
cancelIcon=new ImageIcon("cancel_icon.png","ssh");
saveIcon=new ImageIcon("vaccine.png","ssh");
updateIcon=new ImageIcon("client.png","ssh");

navigateToHomeButton=new JButton(homeIcon);
navigateToVaccinesButton=new JButton(vaccineIcon);
navigateToClientsButton=new JButton(clientIcon);
searchButton=new JButton(homeIcon);
vaccineTable=new JTable(vaccineModel);
scrollPane=new JScrollPane(vaccineTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
vaccinePanel=new VaccinePanel();
}
private void setAppearance()
{
Font headerFont=new Font("Verdana",Font.BOLD,28);
Font titleFont=new Font("Verdana",Font.BOLD,18);
Font searchFont=new Font("Verdana",Font.BOLD,12);
Font dataFont=new Font("Verdana",Font.PLAIN,16);
titleLabel.setFont(titleFont);
searchLabel.setFont(searchFont);
searchTextField.setFont(dataFont);
headerLabel.setFont(headerFont);
searchErrorLabel.setFont(dataFont);
searchErrorLabel.setForeground(Color.red);

container.setLayout(null);
rightPanel.setLayout(null);
leftPanel.setLayout(null);
//topPanel.setLayout(null);

vaccineTable.getColumnModel().getColumn(0).setPreferredWidth(10);
vaccineTable.getColumnModel().getColumn(1).setPreferredWidth(180);
vaccineTable.getColumnModel().getColumn(2).setPreferredWidth(80);
vaccineTable.getColumnModel().getColumn(3).setPreferredWidth(80);


vaccineTable.setRowHeight(35);
vaccineTable.setBackground(new Color(44,52,58)); 
vaccineTable.setForeground(Color.white);
vaccineTable.setRowSelectionAllowed(true);
vaccineTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
scrollPane.setBackground(new Color(44,52,58));

vaccineTable.setOpaque(true);
vaccineTable.setFillsViewportHeight(true);
int lm,tm,bm,rm; //left and top margin 
lm=0;
tm=0;
bm=0;
rm=0;
topPanel.setBounds(lm,tm,rm+1050,bm+80);
scrollPane.setBounds(lm+250,tm+40+110,600,220); 
leftPanel.setBounds(lm,tm,rm+80,680);
rightPanel.setBounds(lm,tm,rm+1050,bm+680);
navigateToHomeButton.setBounds(lm+10,tm+80+40+20,rm+60,bm+60);
navigateToVaccinesButton.setBounds(lm+10,tm+80+40+20+60+5,rm+60,bm+60);
navigateToClientsButton.setBounds(lm+10,tm+80+40+20+60+5+60+5,rm+60,bm+60);
headerLabel.setBounds(lm+600,tm+40,rm+600,bm+30);
titleLabel.setBounds(lm+80+20,tm+40+40,rm+100,bm+30);
searchLabel.setBounds(lm+80+20+100+200+40,tm+40+40+20,rm+60,bm+20);
searchErrorLabel.setBounds(lm+80+20+100+200+40+280,tm+40+30,rm+100,bm+40);
searchTextField.setBounds(lm+80+20+100+200+40+60+5,tm+40+40+20,rm+300,bm+30);
searchButton.setBounds(lm+80+20+100+200+40+60+5+300+5,tm+40+40+20,rm+30,bm+30);
vaccinePanel.setBounds(lm+250,tm+40+110+220+10,600,220);
leftPanel.setBackground(new Color(21,25,28));
rightPanel.setBackground(new Color(34,40,44));
searchTextField.setBackground(new Color(44,52,58));
searchButton.setBackground(new Color(44,52,58));
navigateToHomeButton.setBackground(new Color(34,40,44));
navigateToVaccinesButton.setBackground(new Color(34,40,44));
navigateToClientsButton.setBackground(new Color(34,40,44));
topPanel.setBackground(new Color(34,40,44));
rightPanel.setBackground(new Color(34,40,44));
vaccineTable.setBackground(new Color(44,52,58)); 
scrollPane.setBackground(new Color(44,52,58)); 
JTableHeader header1=vaccineTable.getTableHeader();
header1.setBackground(new Color(44,52,58));
header1.setReorderingAllowed(false);
header1.setResizingAllowed(false);
header1.setForeground(Color.white);
titleLabel.setForeground(Color.white);
searchLabel.setForeground(Color.white);
searchTextField.setForeground(Color.white);
headerLabel.setForeground(Color.white);
vaccineTable.setForeground(Color.white);
leftPanel.setBackground(new Color(21,25,28));
leftPanel.setLayout(null);
vaccinePanel.setBackground(new Color(44,52,58));
container.add(leftPanel);
container.add(topPanel);
container.add(rightPanel);
topPanel.add(headerLabel);
leftPanel.add(navigateToHomeButton);
leftPanel.add(navigateToVaccinesButton);
leftPanel.add(navigateToClientsButton);
rightPanel.add(scrollPane);
rightPanel.add(titleLabel);
rightPanel.add(searchLabel);
rightPanel.add(searchErrorLabel);
rightPanel.add(searchTextField);
rightPanel.add(searchButton);
rightPanel.add(vaccinePanel);
int w,h;
w=1050;
h=680;
setSize(w,h);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
setLocation((d.width/2)-(w/2),(d.height/2)-(h/2));
}
private void addListeners()
{
searchTextField.getDocument().addDocumentListener(this);
searchButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
searchTextField.setText("");
searchTextField.requestFocus();
}
});
vaccineTable.getSelectionModel().addListSelectionListener(this);
}

public void searchVaccine()
{
searchErrorLabel.setText("");
String vaccineName=searchTextField.getText().trim();
if(vaccineName.length()==0)return;
int rowIndex;
try
{
//true will allow partial search
rowIndex=vaccineModel.indexOfVaccine(vaccineName,true);
}catch(BLException blException)
{
System.out.println("Not Found");
searchErrorLabel.setText("Not Found");
return;
}
vaccineTable.setRowSelectionInterval(rowIndex,rowIndex);
Rectangle rectangle=vaccineTable.getCellRect(rowIndex,0,true);
vaccineTable.scrollRectToVisible(rectangle);
}
//Defination of Implemented DocumentListener Methods
public void changedUpdate(DocumentEvent e)
{
searchVaccine();
}
public void insertUpdate(DocumentEvent e)
{
searchVaccine();
}
public void removeUpdate(DocumentEvent e)
{
searchVaccine();
}

//Abstract methods of ListSelectionListener
public void valueChanged(ListSelectionEvent ev)
{
int selectedRowIndex=vaccineTable.getSelectedRow();
try
{
VaccineInterface vaccine=vaccineModel.getVaccine(selectedRowIndex);
vaccinePanel.setVaccineName(vaccine);
}catch(BLException blException)
{
vaccinePanel.clearVaccineName();
}
}
private void setViewMode()
{
/*
1.In view Mode UI should follow-
searchTextField is disabled
clearSearchTextField Button disabled
table disabled
*/
this.mode=MODE.VIEW;
if(vaccineModel.getRowCount()==0)
{
searchTextField.setEnabled(false);
searchButton.setEnabled(false);
vaccineTable.setEnabled(false);
}
else
{
searchTextField.setEnabled(true);
searchButton.setEnabled(true);
vaccineTable.setEnabled(true);
}
}
private void setAddMode()
{
this.mode=MODE.ADD;
searchTextField.setEnabled(false);
searchButton.setEnabled(false);
vaccineTable.setEnabled(false);
}
private void setEditMode()
{
this.mode=MODE.EDIT;
searchTextField.setEnabled(false);
searchButton.setEnabled(false);
vaccineTable.setEnabled(false);
}
private void setDeleteMode()
{
this.mode=MODE.DELETE;
searchTextField.setEnabled(false);
searchButton.setEnabled(false);
vaccineTable.setEnabled(false);
}
private void setExportToPDFMode()
{
this.mode=MODE.EXPORT_TO_PDF;
searchTextField.setEnabled(false);
searchButton.setEnabled(false);
vaccineTable.setEnabled(false);
}


class VaccinePanel extends JPanel
{
private JLabel vaccineCaptionLabel;
private JLabel vaccineLabel;
private JTextField vaccineTextField;
private JButton clearVaccineTextFieldButton;
private JButton addButton;
private JButton editButton;
private JButton cancelButton;
private JButton deleteButton;
private JButton exportToPDFButton;
private JPanel buttonsPanel;
private VaccineInterface vaccine;
VaccinePanel()
{ 
setBorder(BorderFactory.createLineBorder(new Color(165,165,165)));
initComponents();
setAppearance();
addListeners();
}
private void initComponents()
{
vaccine=null;
vaccineCaptionLabel=new JLabel("Vaccine");
vaccineLabel=new JLabel("");
vaccineTextField=new JTextField();
clearVaccineTextFieldButton=new JButton(homeIcon);
buttonsPanel=new JPanel();
addButton=new JButton(addIcon);
editButton=new JButton(editIcon);
cancelButton=new JButton(cancelIcon);
deleteButton=new JButton(deleteIcon);
exportToPDFButton=new JButton(pdfIcon);
}//funtion ends

private void setAppearance()
{
Font captionFont=new Font("Verdana",Font.BOLD,16);
Font dataFont=new Font("Verdana",Font.PLAIN,16);
vaccineCaptionLabel.setFont(captionFont);
vaccineLabel.setFont(dataFont);
vaccineTextField.setFont(dataFont);
setLayout(null);
int lm,tm;
//lm+250,tm+40+110,600,200
lm=0;
tm=0;
Color textColor=Color.white;
vaccineCaptionLabel.setForeground(textColor);
vaccineLabel.setForeground(textColor);
vaccineTextField.setForeground(textColor);
vaccineTextField.setBackground(new Color(44,52,55));
vaccineCaptionLabel.setBounds(lm+20,tm+10+5,110,30);
vaccineLabel.setBounds(lm+20+110+5,tm+20,400,20);
vaccineTextField.setBounds(lm+20+110+5,tm+20,350,30);
clearVaccineTextFieldButton.setBounds(lm+10+110+5+350+5,tm+20,30,30);
buttonsPanel.setBounds(lm+50,tm+20+30+30,465,90); //75 last one
buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
addButton.setBounds(70,17,50,50); // 2nd one 12
editButton.setBounds(70+50+20,17,50,50);
cancelButton.setBounds(70+50+20+50+20,17,50,50);
deleteButton.setBounds(70+50+20+50+20+50+20,17,50,50);
exportToPDFButton.setBounds(70+50+20+50+20+50+20+50+20,17,50,50);
buttonsPanel.setBackground(new Color(44,52,55));
buttonsPanel.setLayout(null);
buttonsPanel.add(addButton);
buttonsPanel.add(editButton);
buttonsPanel.add(cancelButton);
buttonsPanel.add(deleteButton);
buttonsPanel.add(exportToPDFButton);
add(vaccineCaptionLabel);
add(vaccineTextField);
add(vaccineLabel);
add(clearVaccineTextFieldButton);
add(buttonsPanel);
}//function ends
public void clearVaccineName()
{
this.vaccine=null;
this.vaccineLabel.setText("");
}
public void setVaccineName(VaccineInterface vaccine)
{
this.vaccine=vaccine;
vaccineLabel.setText(vaccine.getVaccineName());

}//function ends
public boolean addVaccine()
{
String vaccineName=vaccineTextField.getText().trim();
System.out.println(vaccineName);
if(vaccineName.length()==0)
{
JOptionPane.showMessageDialog(this,"Vaccine required");
vaccineTextField.requestFocus();
return false;
}
VaccineInterface v=new Vaccine();
vaccine.setVaccineName(vaccineName);
vaccine.setGapBetweenDose(gapBetweenDose);
vaccine.setNumberOfDose(numberOfDose);
try
{
vaccineMode.add(v);
rowIndex=vaccineModel.indexOfVaccine(v);

}catch(BLException blException)
return true;
}
public boolean updateVaccine()
{
return true;
}
public boolean deleteVaccine()
{
return true;
}

public void addListeners()
{
this.addButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
if(mode==MODE.VIEW)
{
setAddMode();
}
else
{
if(addVaccine())
{
setViewMode();
}
}
}
});
this.editButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
if(mode==MODE.VIEW)
{
setEditMode();
}
else
{
if(updateVaccine())
{
setViewMode();
}
} 
}
});
this.cancelButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
setViewMode();
}
});
this.deleteButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
setDeleteMode();
}
});

}

private void setViewMode()
{
VaccinationManagerUI.this.setViewMode();
this.addButton.setIcon(addIcon);
this.editButton.setIcon(editIcon);
this.vaccineTextField.setVisible(false);
this.clearVaccineTextFieldButton.setVisible(false); 
this.vaccineLabel.setVisible(true);
this.addButton.setEnabled(true);
this.cancelButton.setEnabled(false);
if(vaccineModel.getRowCount()>0)
{
this.editButton.setEnabled(true);
this.deleteButton.setEnabled(true);
this.exportToPDFButton.setEnabled(true);
}
else
{
this.editButton.setEnabled(false);
this.deleteButton.setEnabled(false);
this.exportToPDFButton.setEnabled(false);
}
}//funtion ends

private void setAddMode()
{
VaccinationManagerUI.this.setAddMode();
this.vaccineTextField.setText("");
this.vaccineLabel.setVisible(false);
this.clearVaccineTextFieldButton.setVisible(true);
this.vaccineTextField.setVisible(true);
addButton.setIcon(saveIcon);   
editButton.setEnabled(false);
cancelButton.setEnabled(true);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
}//funtion ends

private void setEditMode()
{
if(vaccineTable.getSelectedRow()<0 || vaccineTable.getSelectedRow()>=vaccineModel.getRowCount())
{
JOptionPane.showMessageDialog(this,"Select vaccine to edit");
return;
}
VaccinationManagerUI.this.setEditMode();
this.vaccineTextField.setText(this.vaccine.getVaccineName());
this.vaccineLabel.setVisible(false);
this.clearVaccineTextFieldButton.setVisible(true);
this.vaccineTextField.setVisible(true);
addButton.setEnabled(false);   
cancelButton.setEnabled(true);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
editButton.setIcon(updateIcon);
}//funtion ends

private void setDeleteMode()
{
if(vaccineTable.getSelectedRow()<0 || vaccineTable.getSelectedRow()>=vaccineModel.getRowCount())
{
JOptionPane.showMessageDialog(this,"Select vaccine to delete");
return;
}
VaccinationManagerUI.this.setDeleteMode();  
addButton.setEnabled(false);   
editButton.setEnabled(false);   
cancelButton.setEnabled(false);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
deleteVaccine();
VaccinationManagerUI.this.setViewMode();
this.setViewMode();
}//funtion ends

private void setExportToPDFMode()
{
VaccinationManagerUI.this.setExportToPDFMode();  
addButton.setEnabled(false);   
editButton.setEnabled(false);   
cancelButton.setEnabled(false);
deleteButton.setEnabled(false);
exportToPDFButton.setEnabled(false);
}//funtion ends

}//inner class ends

}