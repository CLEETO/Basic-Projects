import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;  
import javax.swing.*;
public class admin extends JFrame implements ActionListener
{
   int i=0;
   connect o=new connect();
   JTextField t2=new JTextField();
   JTextField t3=new JTextField();
   JTextField t4=new JTextField();
   JTextField t5=new JTextField();
   JTextField t6=new JTextField();
   JTextField t7=new JTextField();
   JLabel l2=new JLabel("Product No:");
   JLabel l3=new JLabel("Product Name:");
   JLabel l4=new JLabel("In Stock:");
   JLabel l5=new JLabel("Selling Price:");
   JLabel l6=new JLabel("MRP:");
   JLabel l7=new JLabel("Discount %:");
   JButton b1=new JButton("ADD");
   JButton b3=new JButton("UPDATE");
   JButton b4=new JButton("DELETE");
   JButton b5=new JButton("SEARCH");
   JButton b7=new JButton("BACK");
   JButton b8=new JButton("ORDERS");
   JComboBox c1= new JComboBox();
   admin()
   {
     l2.setBounds(50,140,100,20);
     l3.setBounds(50,180,100,20);
     l4.setBounds(50,240,100,20);
     l5.setBounds(50,280,100,20);
     l6.setBounds(50,320,100,20);
     l7.setBounds(50,360,100,20);
     t2.setBounds(200,140,100,20);
     t3.setBounds(200,180,100,20);
     t4.setBounds(200,240,100,20);
     t5.setBounds(200,280,100,20);
     t6.setBounds(200,320,100,20);
     t7.setBounds(200,360,100,20);
     b1.setBounds(350,150,100,30);
     b3.setBounds(350,200,100,30);
     b4.setBounds(350,250,100,30);
     b5.setBounds(350,300,100,30);
     b7.setBounds(350,50,100,30);
     b8.setBounds(350,350,100,30);
     c1.setBounds(200,205,100,20);
     add(l2);add(l3);add(t2);add(t3);add(b1);add(b1); add(c1); add(b5); add(b8);
     add(l4);add(l5);add(l6);add(l7);add(t4);add(t5);add(t6);add(t7); add(b3);add(b4); add(b7);
     b1.addActionListener(this); b3.addActionListener(this); b4.addActionListener(this); b5.addActionListener(this); 
     b7.addActionListener(this); b8.addActionListener(this); 
     setLayout(null);
     setSize(500,500);
     setVisible(true);
     ResultSet rs=o.execute2("select pname from products");
     
     try
     {
       while(rs.next())  
        c1.addItem(rs.getString("pname"));
     }catch(Exception e){ System.out.println(e);} 
     c1.addActionListener(new ActionListener() { 
   public void actionPerformed(ActionEvent e) {
  t3.setText((String)((JComboBox)e.getSource()).getSelectedItem());}
      });
    }
   void listmake()
   {
       
   }
  public void actionPerformed(ActionEvent e)
  {    
    if(e.getSource()==b5)
    { 
       ResultSet rs=o.execute2("select * from products where pname='"+t3.getText()+"'");
       try
       {
       while(rs.next())
       {
       t2.setText(Integer.toString(rs.getInt("pno")));
       t4.setText(Integer.toString(rs.getInt("stock")));
       t5.setText(Float.toString(rs.getInt("price")));
       t6.setText(Float.toString(rs.getInt("mrp")));
       t7.setText(Float.toString(rs.getInt("dp")));
       }
       }catch(Exception x){ System.out.println(e);} 
    }  
    if(e.getSource()==b1)
    {
       String k="";
       k=t2.getText()+","+"'"+t3.getText()+"',"+t4.getText()+","+t5.getText()+","+t6.getText()+","+t7.getText(); 
       k="insert into products values("+k+ ")"; 
       o.execute(k);
       c1.addItem(t3.getText());
       t2.setText(""); t3.setText(""); t4.setText(""); t5.setText(""); t7.setText(""); t6.setText("");
       
    }
    if(e.getSource()==b7)
    {
        setVisible(false);
        new one();
    }
    if(e.getSource()==b8)
    {
        setVisible(false);
        new view(1,0);
    }
    if(e.getSource()==b3)
    {
       String k="update products set ";
       if(!t2.getText().equals(""))
       {
          if(!t4.getText().equals(""))
          {
            String s=k+"stock="+t4.getText()+" where pno="+t2.getText();
            o.execute(s);
          }
          if(!t5.getText().equals(""))
          {
            String s=k+"price="+t5.getText()+" where pno="+t2.getText();
            o.execute(s);
          }
          if(!t6.getText().equals(""))
          {
            String s=k+"mrp="+t6.getText()+" where pno="+t2.getText();
            o.execute(s);
          }
          if(!t7.getText().equals(""))
          {
            String s=k+"dp="+t7.getText()+" where pno="+t2.getText();
            o.execute(s);
          }
       }
       if(!t3.getText().equals(""))
       {
          if(!t4.getText().equals(""))
          {
            String s=k+"stock="+t4.getText()+" where pname='"+t3.getText()+"'";
            o.execute(s);
          }
          if(!t5.getText().equals(""))
          {
            String s=k+"price="+t5.getText()+" where pname='"+t3.getText()+"'";
            o.execute(s);
          }
          if(!t6.getText().equals(""))
          {
            String s=k+"mrp="+t6.getText()+" where pname='"+t3.getText()+"'";
            o.execute(s);
          }
          if(!t7.getText().equals(""))
          {
            String s=k+"dp="+t7.getText()+" where pname='"+t3.getText()+"'";
            o.execute(s);
          }
       }
       t2.setText(""); t3.setText(""); t4.setText(""); t5.setText(""); t7.setText(""); t6.setText("");
    }
    if(e.getSource()==b4)
    {
       String k="delete from products where ";
       if(!t2.getText().equals(""))
         k=k+" pno="+t2.getText();
       else
       if(!t3.getText().equals(""))
         k=k+" pname='"+t3.getText()+"'";
       o.execute(k);
       t2.setText(""); t3.setText(""); t4.setText(""); t5.setText(""); t7.setText(""); t6.setText("");
    }
  }
  
}