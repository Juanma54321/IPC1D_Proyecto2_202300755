
package Controller;

import Model.Servicios;
import Model.Usuarios;
import Model.Vehiculos;
import View.LoginVista;
import View.ProgresoUserVista;
import View.RegistroVista;
import View.UserVista;
import View.VerVehiculosVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class User implements ActionListener{
    private UserVista view;
    private Usuarios user;
    public static boolean barra=false;

    public User(Usuarios user,UserVista view) {
        this.view = view;
        this.user = user;
        this.view.BtnCerrar.addActionListener(this);
        this.view.btnRegistrar.addActionListener(this);
        this.view.btnVer.addActionListener(this);
        this.view.btnProgreso.addActionListener(this);
        
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
                Usuarios model2 = new Usuarios();
                LoginVista view2 = new LoginVista();
                
                Login controller = new Login(model2,view2);
                controller.IniciarVista();
                
                break;
            
            //accion del boton registrar
            case("Registrar"):
                RegistroVista view1 = new RegistroVista();
                
                Registro controller1 = new Registro(user,view1);
                
                controller1.IniciarVista();
                    
                break;
            //accion del boton ver mis vehiculos
            case("Ver"):
                if (user.getVehiculos()!=null) {
                    VerVehiculosVista view3 = new VerVehiculosVista();
                    VerVehiculo controller3 = new VerVehiculo(user,view3);
                    
                    controller3.IniciarVista();
                    
                }else{
                    JOptionPane.showMessageDialog(view,"No existen vehiculos registrados","ERROR", JOptionPane.ERROR_MESSAGE);
                }
                break;
            //accion del boton ver progreso
            case ("Progreso"):
                if (user.getVehiculos()!=null) {
                    ProgresoUserVista view4 = new ProgresoUserVista();
                    Usuarios model4 = new Usuarios();
                    Servicios model5 = new Servicios();
                    Vehiculos model6 = new Vehiculos(); 
                    
                    TiemposEspera controller4 = new TiemposEspera(user,model4,model5,model6,null,view4);
                    controller4.IniciarVistaUser();
                }else{
                    JOptionPane.showMessageDialog(view,"No existen vehiculos registrados","ERROR", JOptionPane.ERROR_MESSAGE);
                }
                
                
                break;
        }
    
    }
    
}
