
package Controller;

import Model.Inventario;
import Model.Usuarios;
import View.AdminVista;
import View.LoginVista;
import View.RepuestosVista;
import View.UsuariosAdminVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin implements ActionListener{
    private AdminVista view;

    public Admin(AdminVista view) {
        this.view = view;
        this.view.BtnCerrar.addActionListener(this);
        this.view.BtnRepuestos.addActionListener(this);
        this.view.BtnClietnes.addActionListener(this);
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
                
                Login control = new Login(model1,view1);
                control.IniciarVista();
                break;
        
            case ("Repuestos"):
                RepuestosVista view = new RepuestosVista();
                Inventario model = new Inventario();
                
                InventarioAdmin controller = new InventarioAdmin(model,view);
                
                controller.IniciarRepuestos();
                
                break;
            case ("Clientes"):
                Usuarios model2 = new Usuarios();
                UsuariosAdminVista view2 = new UsuariosAdminVista();
                
                UsuariosAdmin controller1 = new UsuariosAdmin(view2,model2);
                
                controller1.IniciarVista();
                
                break;
        }
    
    }
    
}
