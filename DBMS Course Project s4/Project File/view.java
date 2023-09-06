import java.io.*;
import java.util.*;
import java.sql.*;  
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class view implements ActionListener
{
   int n=0,t=0,cn;
   connect o=new connect();
   JFrame f1=new JFrame();
   JLabel l=new JLabel("TOTAL SALE: RS.");
   JLabel l1=new JLabel("ORDER NO");
   JLabel l2=new JLabel("CNO");
   JLabel l3=new JLabel("PNO");
   JLabel l4=new JLabel("PNAME");
   JLabel l5=new JLabel("QTY");
   JLabel l6=new JLabel("PRICE");
   JLabel l7=new JLabel("MRP");
   JLabel l8=new JLabel("TOT");
   JLabel or[]=new JLabel[20];
   String s[]=new String[100];
      JButton b7=new JButton("BACK");

   view(int k,int c)
   {
     n=k; cn=c;;
     l1.setBounds(40,50,80,30);
     l2.setBounds(130,50,50,30);
     l3.setBounds(180,50,50,30);
     l4.setBounds(230,50,50,30);
     l5.setBounds(295,50,50,30);
     l6.setBounds(340,50,50,30);
     l7.setBounds(400,50,50,30);
     l8.setBounds(450,50,50,30);
          b7.setBounds(550,50,100,30);
     l.setBounds(40,5,200,30);
     f1.setSize(700,1000);
     f1.setLayout(null);
      f1.add(l1); f1.add(l2); f1.add(l4); f1.add(l3); f1.add(l5); f1.add(l);
      f1.add(l8); f1.add(l7); f1.add(l6); f1.add(b7);
      f1.setVisible(true); b7.addActionListener(this);
      
     ResultSet rs=null;
     if(n==1)
       rs=o.execute2("select count(ono) from orders");
     if(n==2)
       rs=o.execute2("select count(ono) from orders where cno="+Integer.toString(cn));
       try
       {
       while(rs.next())
    
        t=rs.getInt("count(ono)");
       
       }catch(Exception e){ System.out.println(e);}
       if(t!=0)
       {
          for(int i=0;i<8;i++)
          {
              or[i]=new JLabel("");
              or[i].setBounds(50,50*(i+2),500,30);
              f1.add(or[i]);
          }
       }
       ResultSet rs2=null;
       if(n==1)
       rs2=o.execute2("select sum(tot) from orders");
       if(n==2)
       rs2=o.execute2("select sum(tot) from orders where cno="+Integer.toString(cn));
       try
       {
       while(rs2.next())
    
        l.setText(l.getText()+Float.toString(rs2.getFloat("sum(tot)")));
       
       }catch(Exception e){ System.out.println(e);}
       if(n!=0)
       {
          for(int i=0;i<8;i++)
          {
              or[i]=new JLabel("");
              or[i].setBounds(50,50*(i+2),500,30);
              f1.add(or[i]);
          }
       }
       ResultSet rs1=null;
       if(n==1)
       rs1=o.execute2("select * from orders");
       if(n==2)
       rs1=o.execute2("select * from orders where cno="+Integer.toString(cn));
       int i=0;
       for(int j=0;j<100;j++)
         s[j]="";
       
       try
       {
       while(rs1.next()&&i<20)
       {
         s[i]=s[i]+"     "+Integer.toString(rs1.getInt("ono"));
         s[i]=s[i]+"                    "+Integer.toString(rs1.getInt("cno"));
         s[i]=s[i]+"               "+Integer.toString(rs1.getInt("pno"));
         s[i]=s[i]+"            "+rs1.getString("pname");
         s[i]=s[i]+"           "+Integer.toString(rs1.getInt("qty"));
         s[i]=s[i]+"            "+Float.toString(rs1.getFloat("price"));
         s[i]=s[i]+"            "+Float.toString(rs1.getFloat("mrp"));
         s[i]=s[i]+"           "+Float.toString(rs1.getFloat("tot"));
         or[i].setText(s[i]);
         i++;
       }
       }catch(Exception e){ System.out.println(e);}
       
   }   
   public void actionPerformed(ActionEvent e)
   { 
       if(e.getSource()==b7)
    {
        f1.setVisible(false);
        if(n==1)
        new admin();
        if(n==2)
        new user(cn);
    }
   }
}