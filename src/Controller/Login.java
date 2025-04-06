
package Controller;

import Model.Usuarios;
import View.AdminVista;
import View.LoginVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Login implements ActionListener{
    
    private Usuarios model;
    private LoginVista view;

    public Login(Usuarios model, LoginVista view) {
        this.model = model;
        this.view = view;
        this.view.btnLogin.addActionListener(this);
    }
    
    //metodo inicializar la vista login
    public void IniciarVista(){
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        view.setTitle("LOGIN");
    }
    
    public void actionPerformed(ActionEvent e){
        String user = view.txtUsuario.getText();
        String pass = view.txtContrasena.getText();
        
        byte contador=model.ConfirmarInformacion(user, pass);
        
        //si es un admin
        if (contador==1) {
            AdminVista view1 = new AdminVista();
            
            Admin controller = new Admin(view1);
            
            view.dispose();
            controller.IniciarVista();
        }else{
            JOptionPane.showMessageDialog(view, "Usuario o contraseña incorrectas", "ERROR", JOptionPane.ERROR_MESSAGE);
            view.txtUsuario.setText("Ingrese su usuario");
            view.txtContrasena.setText("**********");
        }
        
        
        
    }
}
