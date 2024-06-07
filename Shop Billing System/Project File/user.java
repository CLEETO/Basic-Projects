import java.io.*;
import java.util.*;
import java.sql.*;  
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class user implements ActionListener
{
   int no; 
   connect o=new connect();
   JFrame f1=new JFrame();
   JLabel l1=new JLabel("Select Item:");
   JTextField t1=new JTextField();
   JLabel l2=new JLabel("Select Qty:");
   JTextField t2=new JTextField("0");
   JLabel l3=new JLabel("Unit Price:");
   JLabel l4=new JLabel("");
   JLabel l5=new JLabel("MRP:");
   JLabel l6=new JLabel("");
   JLabel l7=new JLabel("Discount%:");
   JLabel l8=new JLabel("");
   JLabel l9=new JLabel("In Stock:");
   JLabel l10=new JLabel("");
   JLabel l11=new JLabel("Total Price:");
   JLabel l12=new JLabel("");
   JComboBox c1= new JComboBox();
   JButton b1=new JButton("SET");
   JButton b2=new JButton("ORDER");
   JButton b7=new JButton("BACK");
   JButton b8=new JButton("VIEW ORDERS");
   user(int n)
   {
     no=n;
     l1.setBounds(50,50,80,30);
     t1.setBounds(130,50,100,30);
     l2.setBounds(50,120,80,30);
     t2.setBounds(130,120,30,30);
     b1.setBounds(160,120,70,30);
     b2.setBounds(250,50,80,30);
     c1.setBounds(130,80,100,30);
     l3.setBounds(50,170,80,30);
     l4.setBounds(130,170,80,30);
     l5.setBounds(50,220,80,30);
     l6.setBounds(130,220,80,30);
     l7.setBounds(50,270,80,30);
     l8.setBounds(130,270,80,30);
     l9.setBounds(50,320,80,30);
     l10.setBounds(130,320,80,30);
     l11.setBounds(50,370,80,30);
     l12.setBounds(130,370,80,30);
          b7.setBounds(350,50,100,30);
     b8.setBounds(250,100,200,30);
     f1.setSize(500,500);
      f1.setLayout(null);
      f1.add(b7);
      f1.add(l1); f1.add(t1); f1.add(c1); f1.add(b1); f1.add(b2); f1.add(b8);
      f1.add(l2); f1.add(t2); f1.add(l3); f1.add(l4); f1.add(l5); f1.add(l6); f1.add(l7); f1.add(l8); 
      f1.add(l9); f1.add(l10); f1.add(l11); f1.add(l12); 
      f1.setVisible(true);
      f1.setTitle("    CNO: "+Integer.toString(no));
      b1.addActionListener(this);      b2.addActionListener(this);
      b7.addActionListener(this);       b8.addActionListener(this);

     ResultSet rs=o.execute2("select pname from products");
     
     try
     {
       while(rs.next())  
        c1.addItem(rs.getString("pname"));
     }catch(Exception e){ System.out.println(e);} 
     c1.addActionListener(new ActionListener() { 
   public void actionPerformed(ActionEvent e) {
  t1.setText((String)((JComboBox)e.getSource()).getSelectedItem());
   ResultSet rs1=o.execute2("select price from products where pname='"+t1.getText()+"'");     
   float price=0;
   try
     {
       while(rs1.next())  
        price=rs1.getFloat("price");
     }catch(Exception t){ System.out.println(t);}
     l4.setText("             "+Float.toString(price));
     
     ResultSet rs2=o.execute2("select mrp from products where pname='"+t1.getText()+"'");     
   float mrp=0;
   try
     {
       while(rs2.next())  
        mrp=rs2.getFloat("mrp");
     }catch(Exception t){ System.out.println(t);}
     l6.setText("             "+Float.toString(mrp));
   
   ResultSet rs3=o.execute2("select dp from products where pname='"+t1.getText()+"'");     
   float dp=0;
   try
     {
       while(rs3.next())  
        dp=rs3.getFloat("dp");
     }catch(Exception t){ System.out.println(t);}
     l8.setText("             "+Float.toString(dp));
     ResultSet rs4=o.execute2("select stock from products where pname='"+t1.getText()+"'");     
   int stock=0;
   try
     {
       while(rs4.next())  
        stock=rs4.getInt("stock");
     }catch(Exception t){ System.out.println(t);}
     l10.setText("             "+Integer.toString(stock));
    }
      });
   }
   public void actionPerformed(ActionEvent e)
   { 
       if(e.getSource()==b7)
    {
        f1.setVisible(false);
        new one();
    }
    if(e.getSource()==b8)
    {
        f1.setVisible(false);
        new view(2,no);
    }
       if(e.getSource()==b1)
       {
           l12.setText("             "+Float.toString(Float.parseFloat(l4.getText())*(float)Integer.parseInt(t2.getText())));
       }
       if(e.getSource()==b2)
       {
           String k="insert into orders values(";
           int on=1;
           ResultSet rs=o.execute2("select max(ono) from orders");
           try
           {
             while(rs.next())  
              on=rs.getInt("max(ono)")+1;
           }catch(Exception t){ System.out.println(e);}
           k=k+Integer.toString(on)+","+Integer.toString(no);
           int pn=1;
           ResultSet rs1=o.execute2("select pno from products where pname='"+t1.getText()+"'");
           try
           {
             while(rs1.next())  
              pn=rs1.getInt("pno");
           }catch(Exception t){ System.out.println(e);}
           k=k+","+Integer.toString(pn)+",'"+t1.getText().trim()+"',"+t2.getText().trim()+","+l4.getText().trim()+","+l6.getText().trim()+","+l12.getText().trim()+")";
           if(Integer.parseInt(l10.getText().trim())>0&&Integer.parseInt(t2.getText().trim())>0)
           {
           o.execute(k);
           int curr=Integer.parseInt(l10.getText().trim())-Integer.parseInt(t2.getText().trim());
           k="update products set stock="+Integer.toString(curr)+" where pname='"+t1.getText().trim()+"'";
           System.out.println(k);
           o.execute(k);
           t1.setText(""); t2.setText("");
           l4.setText(""); l6.setText("");
           l8.setText(""); l10.setText("");
           l12.setText(""); 
          }
       }
   }
   public static void main(String args[])
   {
       new user(5);
   }
}