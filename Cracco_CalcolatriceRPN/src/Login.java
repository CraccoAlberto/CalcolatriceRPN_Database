import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {
    private JPanel login;
    private JTextField txtUser;
    private JPasswordField pwdUser;
    private JButton bAccess;
    private JButton bRec;

    public Login() {
        Database db = new Database("127.0.0.1", "3306", "CalcolatriceRPN");

        bAccess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUser.getText();
                String password = pwdUser.getText();

                if(db.Login(username,password))
                    openCalculatorForm();
            }
        });

        bRec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUser.getText();
                String password = pwdUser.getText();

                db.Registrazione(username, password);

                openCalculatorForm();
            }
        });
    }

    private void openCalculatorForm(){
        //nascondo il form
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(login);
        if (frame != null) {
            frame.setVisible(false);
        }
        CalcolatriceRPN.main();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().login);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
