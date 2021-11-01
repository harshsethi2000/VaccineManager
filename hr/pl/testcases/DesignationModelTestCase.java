import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

class DesignationModelTestCase extends JFrame
{
private JPanel leftPanel;
private JPanel topPanel;
private JPanel navigationPanel;
private JPanel rightPanel;
private JPanel vaccinePanel;
private JTextField searchTextField;
private JLabel headerLabel;
private JLabel searchErrorLabel;
private JLabel titleLabel;
private JLabel searchLabel;
private JButton searchButton;
private JButton buttonOne;
private JButton buttonTwo;
private JButton buttonThree;
private Container container;
private ImageIcon homeIcon;
private ImageIcon vaccineIcon;
private ImageIcon clientIcon;
DesignationModelTestCase()
{
initComponents();
setAppearance();
}
private void initComponents()
{
homeIcon=new ImageIcon("home1.png","ssh");
vaccineIcon=new ImageIcon("home1.png","ssh");
clientIcon=new ImageIcon("home1.png","ssh");
leftPanel=new JPanel();
topPanel=new JPanel();
rightPanel=new JPanel();
navigationPanel=new JPanel();
vaccinePanel=new JPanel();
searchTextField=new JTextField();
headerLabel=new JLabel("Vaccine Manager");
searchLabel=new JLabel("Search");
searchErrorLabel=new JLabel("Error");
titleLabel=new JLabel("Vaccines");
buttonOne=new JButton(homeIcon);
buttonTwo=new JButton(vaccineIcon);
buttonThree=new JButton(clientIcon);
searchButton=new JButton(clientIcon);

container=getContentPane();
}
private void setAppearance()
{
Font headerFont=new Font("Verdana",Font.BOLD,28);
Font titleFont=new Font("Verdana",Font.BOLD,18);
Font searchFont=new Font("Verdana",Font.BOLD,12);
Font dataFont=new Font("Verdana",Font.PLAIN,16);
container.setLayout(null);
int lm,tm,bm,rm; //left and top margin 
lm=0;
tm=0;
bm=0;
rm=0;
leftPanel.setBounds(lm,tm,rm+80,680);
leftPanel.setBackground(new Color(21,25,28));
leftPanel.setLayout(null);
titleLabel.setFont(titleFont);
titleLabel.setBounds(lm+80+20,tm+40+40,rm+100,bm+30);
titleLabel.setForeground(Color.white);
searchLabel.setFont(searchFont);
searchLabel.setBounds(lm+80+20+100+200+40,tm+40+40+20,rm+60,bm+20);
searchLabel.setForeground(Color.white);
searchTextField.setBounds(lm+80+20+100+200+40+60+5,tm+40+40+20,rm+300,bm+30);
searchTextField.setFont(dataFont);
searchTextField.setBackground(new Color(34,40,44));
searchTextField.setForeground(Color.white);
searchButton.setBounds(lm+80+20+100+200+40+60+5+300+5,tm+40+40+20,rm+30,bm+30);
searchButton.setBackground(new Color(34,40,44));
buttonOne.setBounds(lm+10,tm+80+40+20,rm+60,bm+60);
buttonTwo.setBounds(lm+10,tm+80+40+20+60+5,rm+60,bm+60);
buttonThree.setBounds(lm+10,tm+80+40+20+60+5+60+5,rm+60,bm+60);
buttonOne.setBackground(new Color(34,40,44));
buttonTwo.setBackground(new Color(34,40,44));
buttonThree.setBackground(new Color(34,40,44));
leftPanel.add(buttonOne);
leftPanel.add(buttonTwo);
leftPanel.add(buttonThree);

topPanel.setBounds(lm,tm,rm+1050,bm+80);
topPanel.setBackground(new Color(34,40,44));

rightPanel.setBounds(lm,tm,rm+1050,bm+680);
rightPanel.setBackground(new Color(34,40,44));

headerLabel.setFont(headerFont);
headerLabel.setBounds(lm+600,tm+40,rm+600,bm+30);
headerLabel.setForeground(Color.white);
navigationPanel.setBounds(lm+80,tm,rm+1200,680);
navigationPanel.setBackground(new Color(34,40,44));
String[][] rec = {
         { "1", "Steve", "AUS" },
         { "2", "Virat", "IND" },
         { "3", "Kane", "NZ" },
         { "4", "David", "AUS" },
         { "5", "Ben", "ENG" },
         { "6", "Eion", "ENG" },
         { "7", "David", "AUS" },
         { "8", "Ben", "ENG" },
         { "9", "Eion", "ENG" },
         { "10", "David", "AUS" },
         { "11", "Ben", "ENG" },
         { "12", "Eion", "ENG" },
      };
      String[] header = { "Rank", "Player", "Country" };
      JTable table = new JTable(rec, header);
table.getColumnModel().getColumn(0).setPreferredWidth(80);
table.getColumnModel().getColumn(1).setPreferredWidth(300);
table.setRowHeight(35);
JScrollPane jscrollPane=new JScrollPane(table);
jscrollPane.setBounds(lm+250,tm+40+100,600,300); 
table.setBackground(new Color(44,52,58)); 
table.setForeground(Color.white);
jscrollPane.setBackground(new Color(44,52,58)); 
JTableHeader header1=table.getTableHeader();
header1.setBackground(new Color(44,52,58));
header1.setReorderingAllowed(false);
header1.setResizingAllowed(false);
header1.setForeground(Color.white);

container.add(jscrollPane);
vaccinePanel.setBounds(lm+250,tm+40+100+300+10,600,180);
vaccinePanel.setBackground(new Color(44,52,58));
container=getContentPane();
container.setLayout(null);
rightPanel.setLayout(null);
rightPanel.add(titleLabel);
rightPanel.add(vaccinePanel);
rightPanel.add(searchLabel);
rightPanel.add(searchTextField);
rightPanel.add(searchButton);
topPanel.add(headerLabel);
container.add(leftPanel);
container.add(topPanel);
container.add(rightPanel);
//container.add(navigationPanel);
int w,h;
w=1050;
h=680;
setSize(w,h);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
setLocation((d.width/2)-(w/2),(d.height/2)-(h/2));
setVisible(true); 
}

public static void main(String gg[])
{
DesignationModelTestCase dmtc=new DesignationModelTestCase();
}

}//class ends
