import java.io.*;
import java.util.*;
import java.sql.*;  
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class one implements ActionListener
{
   int x=0;
   connect o=new connect();
   JFrame f1=new JFrame();
   JLabel l1=new JLabel("Enter PIN:");
   JTextField t1=new JTextField();
   JLabel l2=new JLabel("Enter CNO:");
   JTextField t2=new JTextField();
   JButton b1=new JButton("USER");
   JButton b2=new JButton("ADMIN");
   JButton b3=new JButton("LOGIN");
   JButton b4=new JButton("NEW USER");
   one()
   {
      b1.setBounds(100,150,100,50);
      b2.setBounds(300,150,100,50);
      l1.setBounds(80,350,100,30);
      t1.setBounds(190,350,100,30);
      l2.setBounds(80,300,100,30);
      t2.setBounds(190,300,100,30);
      b3.setBounds(330,350,100,30);
      b4.setBounds(100,400,100,30);
      f1.setSize(500,500);
      f1.setLayout(null);
      f1.add(b1); f1.add(b2);  
      f1.add(l2); f1.add(t2); f1.add(b3); f1.add(b4); 
      f1.add(l1); f1.add(t1);
      l2.setVisible(false); t2.setVisible(false);
      l1.setVisible(false); t1.setVisible(false);
      b3.setVisible(false); b4.setVisible(false);
      b1.addActionListener(this); b2.addActionListener(this);
      b3.addActionListener(this); b4.addActionListener(this);
      f1.setVisible(true);
   }
   public void actionPerformed(ActionEvent e)
   {    
      if(e.getSource()==b1)
      {
         l2.setVisible(true); t2.setVisible(true);
         l1.setVisible(true); t1.setVisible(true);
         b3.setVisible(true); b4.setVisible(true); x=1;
      }
      if(e.getSource()==b4)
      {
         f1.setVisible(false);
         new usercreate();
      }
      if(e.getSource()==b2)
      {
         l2.setVisible(false); t2.setVisible(false);
         l1.setVisible(true); t1.setVisible(true);
         b3.setVisible(true); b4.setVisible(false); x=2;
      }
      if(e.getSource()==b3)
      {
         if(x==2)
         {
         if(t1.getText().equals("12340"))
         {
           t1.setText("Correct");
           l1.setVisible(false); t1.setVisible(false); b3.setVisible(false);
           f1.setVisible(false);
           new admin();
         }
         else
           t1.setText("Wrong"); 
         }
         if(x==1)
         {
         ResultSet rs=o.execute2("select pin from customer where cno="+t2.getText());
         String s="";
         try
         {
         while(rs.next())
           s=Integer.toString(rs.getInt("pin"));
         }catch(Exception x){ System.out.println(e);} 
         if(t1.getText().equals(s))
         {
           f1.setVisible(false);
           new user(Integer.parseInt(t2.getText()));
         }
         else
           t1.setText("Wrong"); 
         }
      } 
   }
   public static void main(String args[])
   {
      one obj=new one();
    
   }
}