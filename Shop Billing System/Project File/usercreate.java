import java.io.*;
import java.util.*;
import java.sql.*;  
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class usercreate implements ActionListener
{
   int x=0;
   connect o=new connect();
   JFrame f1=new JFrame();
   JLabel l1=new JLabel("Enter Name:");
   JTextField t1=new JTextField();
   JLabel l2=new JLabel("Enter new pin:");
   JTextField t2=new JTextField();
   JLabel l3=new JLabel("");
   JButton b1=new JButton("CREATE");
   JButton b7=new JButton("BACK");
   usercreate()
   {
      b1.setBounds(50,150,100,50);
      l1.setBounds(50,50,100,30);
      t1.setBounds(190,50,100,30);
      l2.setBounds(50,100,100,30);
      t2.setBounds(190,100,100,30);
      l2.setBounds(50,100,100,30);
      l3.setBounds(50,220,150,30);
     b7.setBounds(350,50,100,30);

      f1.setSize(500,500);
      f1.setLayout(null);
      f1.add(b1);   
      f1.add(l2); f1.add(t2); 
      f1.add(l1); f1.add(t1); f1.add(l3); f1.add(b7);
      b1.addActionListener(this); b7.addActionListener(this);
      f1.setVisible(true);
   }
   public void actionPerformed(ActionEvent e)
   {    
       if(e.getSource()==b7)
    {
        f1.setVisible(false);
        new one();
    }
      if(e.getSource()==b1)
      {
         String k="";
         int cno=0;
         ResultSet rs=o.execute2("select max(cno) from customer");
         try
         {
           while(rs.next())
           {
             cno=rs.getInt("max(cno)")+1;
             System.out.println(cno);
           }
          }catch(Exception x){ System.out.println(e);} 
         k=Integer.toString(cno)+",'"+t1.getText()+"',"+t2.getText();
         k="insert into customer values("+k+ ")"; 
         o.execute(k);
         l3.setText("YOUR CNO IS "+Integer.toString(cno));
      }
      
   }
   public static void main(String args[])
   {
      usercreate obj=new usercreate();
    
   }
}