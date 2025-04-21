
package Controller;

import Model.Servicios;
import View.ServiciosVista;
import java.io.File;
import javax.swing.JFileChooser;

public class ServiciosAdmin {
    private Servicios model;
    private ServiciosVista view;

    public ServiciosAdmin(Servicios model, ServiciosVista view) {
        this.model = model;
        this.view = view;
    }
    
    //metodo para iniciar la vista
    public void IniciarVista(){
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        view.setTitle("Servicios");
    
    }
    
    //metodo para obtener la ruta del archivo
    public String ObtenerRuta(){
        JFileChooser buscador = new JFileChooser();
        int seleccion = buscador.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File ruta = buscador.getSelectedFile();

            return ruta.getAbsolutePath();
        }else{
            return ("Archivo no encontrado");
        }
    }
    
    
}
