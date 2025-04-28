
package Controller;

import Model.HiloTablas;
import View.ProgresoAdminVista;

public class VerProgresoAdmin {
    private ProgresoAdminVista view;

    public VerProgresoAdmin(ProgresoAdminVista view) {
        this.view = view;
    }
    
    //metodo para iniciar la vista de Adim
    public void InciarVistaAdmin(){
        view.setVisible(true);
        view.setTitle("Progreso Autos");
        view.setLocationRelativeTo(null);
        
        //creando hilo
        HiloTablas.actualizarTablas();
    }
    
    
    
}
