
package Controller;

import static Model.Inventario.libreria_inventario;
import Model.Servicios;
import static Model.Servicios.libreria_servicios;
import View.EditarServicioVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

public class EditarServicios implements ActionListener{
    private Servicios model;
    private EditarServicioVista view;

    public EditarServicios(Servicios model, EditarServicioVista view) {
        this.model = model;
        this.view = view;
        this.view.btnSeleccionar.addActionListener(this);
    }
    
    //metodo para iniciar la vista
    public void IniciarVista(){
        view.setVisible(true);
        view.setTitle("Editor de servicios");
        view.setLocationRelativeTo(null);
        RefreshServicios();
    }

    //metodo para llenar el combo box de los servicios
    private void RefreshServicios() {
        view.listaServicios.removeAllItems();
        
        for (int i = 0; i < model.ContadorServicios(); i++) {
            view.listaServicios.addItem(libreria_servicios[i].getID()+" - "+libreria_servicios[i].getNombre_servicio());
        }
    }
    
    //metodo para actualizar la vista con los datos seleccionados
    private void ActualizarDatos(Servicios s1){
        view.txtnombre.setText(s1.getNombre_servicio());
        view.txtMarca.setText(s1.getMarca());
        view.txtModelo.setText(s1.getModelo());
        
        ActualizarRepuestos(s1);
        ActualizarTabla(s1);
    }
    
    //metodo para actualizar el combo box de repuestos disponibles
    private void ActualizarRepuestos(Servicios s1){
        view.listaRepuestos.removeAllItems();
        for (int i = 0; i < libreria_inventario.length; i++) {
            if (libreria_inventario[i]!=null) {
                if (libreria_inventario[i].getMarca().equalsIgnoreCase(s1.getMarca()) && libreria_inventario[i].getModelo().equalsIgnoreCase(s1.getModelo())) {
                    view.listaRepuestos.addItem(libreria_inventario[i].getNombre());
                }
            }
        }
    }
    
    //metodo para llenar la tabla de los repuestos del servicio seleccionado
    private void ActualizarTabla(Servicios s1){
        int contador=0;
        
        //obteniendo la lista de repuestos y buscandolos
        String[] listaRepuestos = s1.getLista_repuestos().split(";");
        String[] Repuestos= new String[listaRepuestos.length];
        
        
        for (int j = 0; j < libreria_inventario.length; j++) {
            if (libreria_inventario[j]!=null){
                for (int k = 0; k < listaRepuestos.length; k++) {
                    if (libreria_inventario[j].getID().equals(listaRepuestos[k])) {
                        Repuestos[contador]=libreria_inventario[j].getNombre();
                        contador++;
                    }
                }
            }
        }  
        
        //creando una instancia para poder agregar los datos en la tabla
        DefaultTableModel tablaNueva = (DefaultTableModel) view.tablaRepuestos.getModel();
        tablaNueva.setRowCount(0);
        for (int i = 0; i < Repuestos.length; i++) {
            tablaNueva.addRow(new Object[]{Repuestos[i]});
        }
        
    }
    //acciones que realizaran los botones
    public void actionPerformed(ActionEvent e){
        
        //variables de control
        String opcion = e.getActionCommand();
        int index = view.listaServicios.getSelectedIndex();
        
        switch(opcion){
            
            //accion del boton seleccionar
            case("Seleccionar"):
                ActualizarDatos(libreria_servicios[index]);
                
                break;
        }
    }
    
    
}
