
package Controller;

import Model.Inventario;
import static Model.Inventario.libreria_inventario;
import Model.Servicios;
import Model.Usuarios;
import View.AdminVista;
import View.LoginVista;
import View.RepuestosVista;
import View.ServiciosVista;
import View.UsuariosAdminVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Admin implements ActionListener{
    private AdminVista view;

    public Admin(AdminVista view) {
        this.view = view;
        this.view.BtnCerrar.addActionListener(this);
        this.view.BtnRepuestos.addActionListener(this);
        this.view.BtnClietnes.addActionListener(this);
        this.view.BtnServicios.addActionListener(this);
    }
    
    //metodo para iniciar la vista
    public void IniciarVista(){
        view.setVisible(true);
        view.setTitle("Modo Aministrador");
        view.setLocationRelativeTo(null);
    };
    
    //acciones de los botones
    public void actionPerformed(ActionEvent e){
        String opcion;
        
        opcion=e.getActionCommand();
        
        switch(opcion){
        
            
            //accion del boton cerrar
            case ("Cerrar"):
                //cerrando la pestaña actual
                view.dispose();
                
                //iniciando de nuevo la vista inicial
                LoginVista view1 = new LoginVista();
                Usuarios model1 = new Usuarios();
                
                Login controller = new Login(model1,view1);
                controller.IniciarVista();
                break;
                
            //accion del boton Repuestos
            case ("Repuestos"):
                //iniciando la vista repuestos
                RepuestosVista view3 = new RepuestosVista();
                Inventario model3 = new Inventario();
                InventarioAdmin controller1 = new InventarioAdmin(model3,view3);
                controller1.IniciarRepuestos();
                
                break;
                
            //accion del boton Clientes    
            case ("Clientes"):
                //iniciando la vista de clientes
                Usuarios model2 = new Usuarios();
                UsuariosAdminVista view2 = new UsuariosAdminVista();
                UsuariosAdmin controller2 = new UsuariosAdmin(view2,model2);
                controller2.IniciarVista();
                
                break;
                
            //accion del boton servicios    
            case("Servicios"):
                if (libreria_inventario[0]!=null) {
                    //iniciando la vista de servicios
                    Servicios model4 = new Servicios();
                    ServiciosVista view4 = new ServiciosVista();
                    ServiciosAdmin controller4 = new ServiciosAdmin(model4,view4);
                    controller4.IniciarVista();
                }else{
                    JOptionPane.showMessageDialog(view,"No existen repuestos para crear servicios", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    
    }
    
}
