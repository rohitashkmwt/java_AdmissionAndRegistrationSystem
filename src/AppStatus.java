import javax.swing.*;

public class AppStatus {
    String name;
    String appStatus = "Pending";

    JFrame f;
    JLabel welcome;
    JLabel status = new JLabel();
    AppStatus(String Uname){
        name = Uname;
        f = new JFrame("Application Status");

        welcome = new JLabel("Welcome " + name);
        welcome.setBounds(200,10,200,20);
        status.setBounds(200,200,200,20);
        welcome.setHorizontalAlignment(JLabel.CENTER);
        status.setHorizontalAlignment(JLabel.CENTER);
        status.setText("Application Status: " + appStatus);

        f.add(welcome);
        f.add(status);
        f.setSize(600,600);
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
//    public static void main(String [] args){
//        new AppStatus(args[0]);
//    }
}