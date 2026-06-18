package Myjavaprj;
import dao.DBConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class LoginFrame extends JFrame {
    JTextField txtUser;
    JPasswordField txtPass;
    JButton btnLogin;
    public LoginFrame() {
        setTitle("Library Login");
        setSize(350,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel(new GridLayout(3,2));
        panel.add(new JLabel("Username"));
        txtUser = new JTextField();
        panel.add(txtUser);

        panel.add(new JLabel("Password"));
        txtPass = new JPasswordField();
        panel.add(txtPass);

        btnLogin = new JButton("Login");
        panel.add(btnLogin);

        add(panel);

        btnLogin.addActionListener(e -> login());

        setVisible(true);
    }

    private void login() {

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM admin WHERE username=? AND password=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, txtUser.getText());
            ps.setString(2,
                    String.valueOf(txtPass.getPassword()));

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {

                JOptionPane.showMessageDialog(this,
                        "Login Success");

                new DashboardFrame();

                dispose();

            } else {

                JOptionPane.showMessageDialog(this,
                        "Invalid Credentials");
            }

        } catch(Exception ex) {

            ex.printStackTrace();
        }
    }
}



    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginFrame();
        });
    }
    }

