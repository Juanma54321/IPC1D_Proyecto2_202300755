
package Controller;

import Model.Usuarios;
import View.LoginVista;
import View.RegistroVista;
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
        this.view.btnRegistrar.addActionListener(this);
        
    }
    
    //metodo para iniciar la vista
    public void IniciarVista(){
        view.setVisible(true);
        view.setTitle("Taller DonJuan");
        view.setLocationRelativeTo(null);
    
    }
    
    //acciones que realizaran los botones
    public void actionPerformed(ActionEvent e){
        String opcion = e.getActionCommand();
        
        switch(opcion){
            //accion del boton cerrar
            case("Cerrar"):
                view.dispose();
                Usuarios model = new Usuarios();
                LoginVista view = new LoginVista();
                
                Login controller = new Login(model,view);
                controller.IniciarVista();
                
                break;
            
            //accion del boton registrar
            case("Registrar"):
                RegistroVista view1 = new RegistroVista();
                
                Registro controller1 = new Registro(user,view1);
                
                controller1.IniciarVista();
                    
                break;
        }
    
    }
    
}
