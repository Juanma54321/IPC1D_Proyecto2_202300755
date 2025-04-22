
package Controller;

import Model.Usuarios;
import View.LoginVista;
import View.UserVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class User implements ActionListener{
    private UserVista view;
    private Usuarios user;

    public User(Usuarios user,UserVista view) {
        this.view = view;
        this.user = user;
        this.view.BtnCerrar.addActionListener(this);
    }
    
    //metodo para iniciar la vista
    public void IniciarVista(){
        view.setVisible(true);
        view.setTitle("Taller DonJuan");
        view.setLocationRelativeTo(null);
    
    }
    
    public void actionPerformed(ActionEvent e){
        String opcion = e.getActionCommand();
        
        switch(opcion){
            case("Cerrar"):
                view.dispose();
                Usuarios model = new Usuarios();
                LoginVista view = new LoginVista();
                
                Login controller = new Login(model,view);
                controller.IniciarVista();
        }
    
    }
    
}
