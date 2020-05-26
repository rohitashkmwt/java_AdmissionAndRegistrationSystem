import javax.swing.*;
import  java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class LoginStudent implements ActionListener{
    JFrame f;
    JTextField regNo;
    JPasswordField password;

    JLabel warning = new JLabel();
    JLabel user = new JLabel("Reg. No.: ");
    JLabel pword = new JLabel("Password: ");
    JButton login = new JButton("Log In");

    LoginStudent(){
        f = new JFrame("Student Login");
        JLabel welcome = new JLabel("Welcome To NIT Andhra Pradesh");
        welcome.setBounds(200,10,200,20);

        JLabel logo = new JLabel((new ImageIcon("logo.png")));
        logo.setBounds(200, 25, 100, 30);

        user.setBounds(160, 250,100,30);

        regNo = new JTextField();
        regNo.setBounds(250,250,150,30);

        pword.setBounds(160, 300,100,30);;
        password = new JPasswordField();
        password.setBounds(250,300,150,30);

        warning.setBounds(220,350,300,30);
        warning.setForeground(Color.red);

        login.setBounds(200,400,100,30);
        login.addActionListener(this);

        f.add(welcome);
        f.add(logo);
        f.add(regNo);
        f.add(warning);
        f.add(user);
        f.add(password);
        f.add(pword);
        f.add(login);

        f.setSize(600,600);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==login){
            if(!regNo.getText().isEmpty() && password.getPassword().toString() != ""){
                try{
                    Connection connection =
                            DriverManager.getConnection("jdbc:mysql://localhost/semester_registration?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
                    Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    String query = "SELRCT `regNo`, `password`, `status`, `name` FROM student WHERE `regNo` = " + regNo.getText();
                    ResultSet res = stmt.executeQuery(query);
                    res.last();
                    if(res.getRow() == 0){
                        warning.setText("Account Not Found.");
                    }else{
                        char []p = password.getPassword();
                        String pwd = "";
                        for(int i= 0; i < p.length; i++){
                            pwd += p[i];
                        }
                        System.out.println("Entered Password: " + pwd);
                        System.out.println("DB : " + res.getString(2));
                        if(pwd.equals(res.getString(2))){
                            if(res.getString(3).equals("NO")){
                                new StudentRegForm(res.getString(4), Integer.parseInt(regNo.getText()));
                            }else{
                                new AppStatus(res.getString(4));
                            }
                        }else {
                            warning.setText("Wrong Credentials.");
                        }
                    }
                }catch (SQLException ex){
                    System.out.println("Error: " + ex);
                }
            }else{
                warning.setText("Please provide proper Credentials.");
            }
        }
    }
 //   public static void main(String[] args){
 //       new LoginStudent();
 //   }
}