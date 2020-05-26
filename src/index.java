import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class index implements ActionListener{
    JFrame f;



    JLabel info = new JLabel("Nit Andhra Pradesh Online Semester Registration");
    JLabel noAccount = new JLabel("Do not have an account?");
    JButton sign = new JButton("Sign-Up");
    JLabel hasAccount = new JLabel("Have an account?");
    JButton log = new JButton("Log-in");


    index(){
        f = new JFrame("Home Page");

        info.setBounds(170, 10, 300, 20);
        hasAccount.setBounds(220,100,200,20);
        log.setBounds(220,250,100,30);

        noAccount.setBounds(200, 200, 200, 20);
        sign.setBounds(220,250,100,30);

        log.addActionListener(this);
        sign.addActionListener(this);

        f.add(info);
        f.add(hasAccount);
        f.add(noAccount);
        f.add(log);
        f.add(sign);

        f.setSize(600,600);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == log){
            new LoginStudent();
        }else {
            new SignUpStudent();
        }
    }

    public static void main(String[] args){
        new index();
    }

}