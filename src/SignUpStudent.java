import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.GregorianCalendar;

public class SignUpStudent implements ActionListener{
    JFrame f;
    JTextField regNo;
    JTextField email;
    JTextField sem;
    JComboBox branch;
    JPasswordField password;
    JTextField name;

    JLabel warning = new JLabel();
    JLabel nameLable = new JLabel("Name: ");
    JLabel user = new JLabel("Reg. No.: ");
    JLabel emailID = new JLabel("Email: ");
    JLabel semester = new JLabel("Semester(1-8): ");
    JLabel branchStudy = new JLabel("Branch: ");
    JLabel pword = new JLabel("Password: ");

    JButton signup = new JButton("Sign Up");

    SignUpStudent(){
        f = new JFrame("Student Login");

        JLabel welcome = new JLabel("Welcome to NIT Andhra Pradesh");
        welcome.setBounds(200,10,200,20);

        JLabel logo = new JLabel(new ImageIcon("logo.png"));
        logo.setBounds(200,25,350,350);

        user.setBounds(160,250,100,30);
        regNo = new JTextField();
        regNo.setBounds(250,250,150,30);

        nameLable.setBounds(160,300,100,30);
        name = new JTextField();
        name.setBounds(250,150,300,30);

        emailID.setBounds(160,350,100,30);
        email = new JTextField();
        email.setBounds(250,350,150,30);

        semester.setBounds(160,400,100,30);
        sem = new JTextField();
        sem.setBounds(250,400,150,30);

        branchStudy.setBounds(160,450,100,30);
        String b[] = {"CSE", "BioTech", "Chemical", "Mech", "Civil", "MME", "EEE", "ECE"};
        branch = new JComboBox(b);
        branch.setBounds(250,450,150,30);

        pword.setBounds(160,500,100,30);
        password = new JPasswordField();
        password.setBounds(250,500,150,30);

        warning.setBounds(220,550,300,30);

        signup.setBounds(220,600,100,30);
        signup.addActionListener(this);

        f.add(welcome);
        f.add(logo);
        f.add(regNo);
        f.add(name);
        f.add(nameLable);
        f.add(user);
        f.add(emailID);
        f.add(email);
        f.add(sem);
        f.add(semester);
        f.add(branch);
        f.add(branchStudy);
        f.add(password);
        f.add(pword);
        f.add(signup);
        f.add(warning);

        f.setSize(600,600);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == signup){
            //JOptionPane.showMessageDialog(f,"Clicked");
            if(!(regNo.getText().isEmpty() && name.getText().isEmpty() && email.getText().isEmpty() && sem.getText().isEmpty() && password.getPassword().toString() != "")){
                if(!regNo.getText().isEmpty()){
                    try{
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/semester_registration?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
                        Statement stmt = connection.createStatement();
                        char []p = password.getPassword();
                        String pwd = "";
                        for (int i = 0; i < p.length; i++){
                            pwd += p[i];
                        }
                        System.out.println(pwd);
                        String query = "INSERT INTO `student` VALUES (" + regNo.getText() + "," + "'" + pwd + "'" + "," + "'" + name.getText() + "'" + "," + "'" +branch.getSelectedItem() + "'" + ", 'NO', 866085, '" + email.getText() + "'" + "," + sem.getText() + ")";
                        System.out.println(query);
                        int res = stmt.executeUpdate(query);
                        new StudentRegForm(name.getText(), Integer.parseInt(regNo.getText()));
                        System.out.println("Inserted.");
                    }catch (SQLException ex){
                        System.out.println("Error: " + ex);
                    }
                }else{
                    warning.setText("Proper Credentials are mandatory.");
                    warning.setOpaque(true);
                    warning.setForeground(Color.RED);
                }
            }
        }
    }
//    public static void main(String[] args){
//        new SignUpStudent();
//    }
}