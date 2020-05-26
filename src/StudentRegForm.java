import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class  StudentRegForm implements ActionListener{
    String userName;
    int reg;

    JFrame f;
    JLabel welcome;
    JLabel pending;
    JLabel instFile = new JLabel();
    JLabel hostFile = new JLabel();
    JLabel incomeFile = new JLabel();
    JLabel casteFile = new JLabel();
    boolean f1 = false, f2 = false, f3 = false, f4 = false;

    JLabel name  = new JLabel("Full Name: ");
    JLabel instFee = new JLabel("Institute Fee: ");
    JLabel hostelFee = new JLabel("Hostel Fee: ");
    JLabel category = new JLabel("Category: ");
    JLabel iSlip = new JLabel("Institute Fee Proof: ");
    JLabel hSlip = new JLabel("Hostel Fee Proof: ");
    JLabel ic = new JLabel("Income Certificate: ");
    JLabel cc = new JLabel("Character Certificate: ");
    JTextField fname;
    JTextField institute, hostel;
    JComboBox cat;
    JButton instSlip = new JButton("Choose File");
    JButton hostSlip = new JButton("Choose File");
    JButton incomeCert = new JButton("Choose File");
    JButton casteCert = new JButton("Choose File");

    JButton submit = new JButton("Submit");

    StudentRegForm(String Uname, int reg){
        this.userName = Uname;
        this.reg = reg;
        f = new JFrame("Registration Form");
        welcome = new JLabel("Welcome " + userName);
        welcome.setBounds(220,10,200,20);

        pending = new JLabel("Please Fill Your Form.");
        pending.setBounds(200,50,200,20);

        name.setBounds(160,100,100,30);
        fname = new JTextField(userName);
        fname.setBounds(250,100,150,30);

        instFee.setBounds(160,150,150,30);
        institute = new JTextField();
        institute.setBounds(250,150,150,30);

        hostelFee.setBounds(160,200,100,30);
        hostel = new JTextField();
        hostel.setBounds(250,200,150,30);

        category.setBounds(160,250,100,30);


        String c[] = {"OPEN", "OBC", "SC/ST", "OEB", "MEB"};
        cat = new JComboBox(c);
        cat.setBounds(250,250,150,30);
        iSlip.setBounds(160,300,150,30);
        instSlip.addActionListener(this);
        instSlip.setBounds(300,300,100,30);

        hSlip.setBounds(160,350,150,30);
        hostSlip.addActionListener(this);
        hostSlip.setBounds(300,350,100,30);

        ic.setBounds(160,400,150,30);
        incomeCert.addActionListener(this);
        incomeCert.setBounds(300,400,100,30);

        cc.setBounds(160,450,150,30);
        casteCert.addActionListener(this);
        casteCert.setBounds(300,450,100,30);

        submit.setBounds(210,500,100,30);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(! fname.getText().isEmpty() && ! institute.getText().isEmpty() && !hostel.getText().isEmpty() && cat.getSelectedItem() != "" && (f1 || f2 || f3 || f4)){
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/semester_registration?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
                        Statement stmt = connection.createStatement();
                        String query = "INSERT INTO `registrations` VALUES (" + reg + "," + "'" + cat.getSelectedItem() + "'" + "," + institute.getText() + "," + hostel.getText() + ", current_timestamp, '" + instFile.getIcon().toString() + "','" + hostFile.getIcon().toString() + "','" + incomeFile.getIcon().toString() + "','" + casteFile.getIcon().toString() + "','pending')";
                        System.out.println(query);
                        int res = stmt.executeUpdate(query);
                    }catch (SQLException ex){
                        System.out.println("Error: " + ex);
                    }
                }
            }
        });

        instFee.setBounds(10,550,150,150);
        hostFile.setBounds(170,550,150,150);
        incomeFile.setBounds(330,550,150,150);
        casteFile.setBounds(485,550,150,150);

        f.add(welcome);
        f.add(pending);
        f.add(name);
        f.add(instFee);
        f.add(hostelFee);
        f.add(category);
        f.add(fname);
        f.add(institute);
        f.add(hostel);
        f.add(cat);
        f.add(submit);
        f.add(iSlip);
        f.add(instSlip);
        f.add(hSlip);
        f.add(hostSlip);
        f.add(ic);
        f.add(incomeCert);
        f.add(cc);
        f.add(casteCert);
        f.add(instFile);
        f.add(hostFile);
        f.add(incomeFile);
        f.add(casteFile);

        f.setSize(700,900);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void actionPerformed(ActionEvent e){
        JFileChooser file = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg","jpeg", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if(e.getSource() == instSlip){
            if(result == JFileChooser.APPROVE_OPTION){
                File selectedFile = file.getSelectedFile();
                String path = selectedFile.getAbsolutePath();
                instFile.setIcon(resize(path));
                f1 = true;
            }else if(result == JFileChooser.CANCEL_OPTION){
                System.out.println("No File Selected.");
            }else if(e.getSource() == hostSlip){
                if(result == JFileChooser.APPROVE_OPTION){
                    File selectedFile = file.getSelectedFile();
                    String path = selectedFile.getAbsolutePath();
                    hostFile.setIcon(resize(path));
                    f2 = true;
                }else if (result == JFileChooser.CANCEL_OPTION){
                    System.out.println("No File Selected.");
                }
            }else if(e.getSource() == incomeCert){
                if(result == JFileChooser.APPROVE_OPTION){
                    File selectedFile = file.getSelectedFile();
                    String path = selectedFile.getAbsolutePath();
                    incomeFile.setIcon(resize(path));
                    f3 = true;
                }else if(result == JFileChooser.CANCEL_OPTION){
                    System.out.println("No File Selected.");
                }
            }else{
                if(result == JFileChooser.APPROVE_OPTION){
                    File selectedFile = file.getSelectedFile();
                    String path = selectedFile.getAbsolutePath();
                    casteFile.setIcon(resize(path));
                    f4 = true;
                }else {
                    System.out.println("No File Selected.");
                }
            }
        }
    }
    public ImageIcon resize(String path){
        ImageIcon image = new ImageIcon(path);
        Image img = image.getImage();
        Image newImg = img.getScaledInstance(instFee.getWidth(), instFile.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon returnImage = new ImageIcon(newImg);
        return returnImage;
    }

//    public static void main(String[] args){
//        new StudentRegForm(args[0]);
//    }
}