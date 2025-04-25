
package Controller;

import Model.Usuarios;
import static Model.Usuarios.libreria_usuarios;
import View.ClienteNuevoVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class RegistroUsuario implements ActionListener{
    private Usuarios model;
    private ClienteNuevoVista view;

    public RegistroUsuario(Usuarios model, ClienteNuevoVista view) {
        this.model = model;
        this.view = view;
        this.view.btnRegistrar.addActionListener(this);
    }
    
    public void IniciarVista(){
        view.setVisible(true);
        view.setTitle("Bienvenido a taller DonJuan");
        view.setLocationRelativeTo(null);
    }
    
    public void actionPerformed(ActionEvent e){
        Usuarios p1= new Usuarios();
        
        
        if (!view.txtNombre.getText().equals("Ingrese su nombre") && !view.txtCUI.getText().equals("0000000000000") && !view.txtUsuario.getText().equals("Ingrese algun nombre") && !view.txtContraseña.getText().equals("XXXX")) {
            if (p1.VerficadorCUI(view.txtCUI.getText())) {
               //obtenemos los datos de la vista
                p1.setNombre(view.txtNombre.getText());
                p1.setNombre_usuario(view.txtUsuario.getText());
                p1.setCui(Long.parseLong(view.txtCUI.getText()));
                p1.setPassword(view.txtContraseña.getText());
                p1.setCliente_oro(false);
                p1.setVehiculos(null);
                
                libreria_usuarios[p1.ContadorUsuarios()]=p1;
                JOptionPane.showMessageDialog(view,"Usuario registrado con exito", "INFO", JOptionPane.INFORMATION_MESSAGE);
                view.dispose();
                
            }else{
                JOptionPane.showMessageDialog(view,"CUI no valido", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(view,"Llene todos los campos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    
    }
}
