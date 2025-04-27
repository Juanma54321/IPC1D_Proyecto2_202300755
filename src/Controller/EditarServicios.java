
package Controller;

import static Model.Inventario.libreria_inventario;
import Model.Servicios;
import static Model.Servicios.libreria_servicios;
import View.EditarServicioVista;
import View.ServiciosVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EditarServicios implements ActionListener{
    private Servicios model;
    private EditarServicioVista view;

    public EditarServicios(Servicios model, EditarServicioVista view) {
        this.model = model;
        this.view = view;
        this.view.btnSeleccionar.addActionListener(this);
        this.view.btnAñadorRepuesto.addActionListener(this);
        this.view.btnBuscarRepuestos.addActionListener(this);
        this.view.btnGuardar.addActionListener(this);
        this.view.btnEliminar.addActionListener(this);
        
        // Agregar el listener para detectar cuando se presiona la X
        this.view.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Previene cierre automático
        //accion que se realizara antes de cerrar el programa
        this.view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                view.dispose();
                ServiciosVista view1 = new ServiciosVista();
                ServiciosAdmin controller1 = new ServiciosAdmin(model,view1);
                controller1.IniciarVista();
            }
        });
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
        view.txtID.setText(s1.getID());
        view.txtPrecio.setText(String.valueOf(s1.getPrecio_mano_obra()));
        
        ActualizarRepuestos(s1);
        ActualizarTabla(s1);
    }
    
    //metodo para actualizar el combo box de repuestos disponibles
    private void ActualizarRepuestos(Servicios s1){
        view.listaRepuestos.removeAllItems();
        for (int i = 0; i < libreria_inventario.length; i++) {
            if (libreria_inventario[i]!=null) {
                if (libreria_inventario[i].getMarca().equalsIgnoreCase(s1.getMarca()) && 
                        libreria_inventario[i].getModelo().equalsIgnoreCase(s1.getModelo())) {
                    view.listaRepuestos.addItem(libreria_inventario[i].getNombre());
                
                }else if(libreria_inventario[i].getMarca().equalsIgnoreCase("cualquiera")) {
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
                    if (!s1.getLista_repuestos().equals("")) {
                        if (libreria_inventario[j].getID().equals(listaRepuestos[k])) {
                        Repuestos[contador]=libreria_inventario[j].getNombre();
                        contador++;
                        
                        }
                    }
                }
            }
        }  
        
        //creando una instancia para poder agregar los datos en la tabla
        DefaultTableModel tablaNueva = (DefaultTableModel) view.tablaRepuestos.getModel();
        tablaNueva.setRowCount(0);
        if (!listaRepuestos[0].equals("")) {
            for (int i = 0; i < Repuestos.length; i++) {
                tablaNueva.addRow(new Object[]{Repuestos[i]});
            }
        }else{
            tablaNueva.setRowCount(0);
        }
        
    }
    
    //metodo para añadir el repuesto a la lista
    private void AñadirRepuesto(String repuestoBuscar, Servicios s1){ 
        //creando una instancia para poder agregar los datos en la tabla
        DefaultTableModel tablaNueva = (DefaultTableModel) view.tablaRepuestos.getModel();
                
        
        if (!(view.tablaRepuestos.getRowCount() > 0)) {
            tablaNueva.setRowCount(0);
            //buscamos en el inventario el repuesto
            for (int i = 0; i < libreria_inventario.length; i++) {
                if (libreria_inventario[i]!=null) {
                    if (libreria_inventario[i].getNombre().equalsIgnoreCase(repuestoBuscar)) {
                        if (libreria_inventario[i].getModelo().equalsIgnoreCase(view.txtModelo.getText()) || libreria_inventario[i].getModelo().equalsIgnoreCase("cualquiera")) {
                            //agregando los elementos a la tabla
                            tablaNueva.addRow(new Object[]{libreria_inventario[i].getNombre()});
                            JOptionPane.showMessageDialog(view,"Repuesto añadido correctamente", "INFO", JOptionPane.INFORMATION_MESSAGE);
                            break;  
                        }
                    }     
                }
            }  
        } else {
            
            //buscamos en el inventario el repuesto
            for (int i = 0; i < libreria_inventario.length; i++) {
                if (libreria_inventario[i]!=null) {
                    if (libreria_inventario[i].getNombre().equalsIgnoreCase(repuestoBuscar)) {
                        if (libreria_inventario[i].getModelo().equalsIgnoreCase(view.txtModelo.getText()) || libreria_inventario[i].getModelo().equalsIgnoreCase("cualquiera")) {
                            //agregando los elementos a la tabla
                            tablaNueva.addRow(new Object[]{libreria_inventario[i].getNombre()});
                            JOptionPane.showMessageDialog(view,"Repuesto añadido correctamente", "INFO", JOptionPane.INFORMATION_MESSAGE);
                            break;  
                        }
                    }       
                }
            } 
        }
    }
    
    //metodo para verificar la informacion
    private boolean Verificador(){
        boolean condicion;
        
        //verificando si estan llenados los campos
        if (view.txtID.getText().equals("") && view.txtMarca.getText().equals("") && view.txtModelo.getText().equals("") && view.txtnombre.getText().equals("")  && view.txtPrecio.getText().equals("")) {
            condicion=false;
        }
        else{
            if (!view.txtPrecio.getText().matches("^[0-9.]+$")) {
                return false;
            }
            if (Float.parseFloat(view.txtPrecio.getText())<=0) {
                return false;
            }
            condicion=true;
        }
        
        return condicion;
    }
    
    //metodo para guardar los datos ingresados
    private void GuardarCambios(Servicios s1, int posicion){
        s1.setID(view.txtID.getText());
        s1.setMarca(view.txtMarca.getText());
        s1.setModelo(view.txtModelo.getText());
        s1.setNombre_servicio(view.txtnombre.getText());
        s1.setPrecio_mano_obra(Float.parseFloat(view.txtPrecio.getText()));
    
        //obteniendo todos los repuestos
        int contador=view.tablaRepuestos.getRowCount();
        String[] lista_repuestos = new String[contador];
        String[] identificadores= new String[contador];
        
        for (int i = 0; i < contador; i++) {
            lista_repuestos[i]=view.tablaRepuestos.getValueAt(i,0).toString();
                    
                    }
        
        contador=0;
        
        for (int i = 0; i < libreria_inventario.length ; i++) {
            for (int j = 0; j < lista_repuestos.length; j++) {
                if (libreria_inventario[i]!=null) {
                    if (libreria_inventario[i].getNombre().equalsIgnoreCase(lista_repuestos[j])) {
                    identificadores[contador]=libreria_inventario[i].getID();
                    contador++;
                    }
                }  
            }
        }
            
        
        String repuestos= String.join(";", identificadores);
    
        s1.setLista_repuestos(repuestos);
        
        //guardando
        libreria_servicios[posicion]=s1;
    }
    
    //metodo para poder eliminar un repuestos
    public void Eliminador(){
        String RepuestoEliminar = view.txtRepuesto.getText().trim();

        DefaultTableModel modelo = (DefaultTableModel) view.tablaRepuestos.getModel();
        boolean encontrado = false;
        
        
        for (int i = 0; i < view.tablaRepuestos.getRowCount(); i++) {
            if (RepuestoEliminar.equalsIgnoreCase(view.tablaRepuestos.getValueAt(i,0).toString())) {
                modelo.removeRow(i);
                encontrado = true;
                JOptionPane.showMessageDialog(view,"Repuesto eliminado con exito", "INFO", JOptionPane.INFORMATION_MESSAGE);
                break; // salimos después de eliminar
            }
        }
        if (!encontrado) {
            JOptionPane.showMessageDialog(view,"Repuesto no encontrado", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    
    }
    
    //acciones que realizaran los botones
    public void actionPerformed(ActionEvent e){
        
        //variables de control
        String opcion = e.getActionCommand();
        int index = view.listaServicios.getSelectedIndex();
    
        //obtenemos el servicios para modificarlo
        Servicios s1 = libreria_servicios[index];
        
        switch(opcion){
            
            //accion del boton seleccionar
            case("Seleccionar"):
                ActualizarDatos(s1);
                //bloqueando botones para evitar errores
                view.btnSeleccionar.setEnabled(false);
                view.listaServicios.setEnabled(false);
                //activando todos los botones
                view.btnAñadorRepuesto.setEnabled(true);
                view.btnBuscarRepuestos.setEnabled(true);
                view.btnEliminar.setEnabled(true);
                view.btnGuardar.setEnabled(true);
                
                break;
                
            //accion del boton añadir
            case("Añadir"):
                String repuesto =view.listaRepuestos.getSelectedItem().toString();
                
                AñadirRepuesto(repuesto,s1);
                
                break;
                
            //accion del boton buscar
            case("Buscar"):
                int confirmar = JOptionPane.showConfirmDialog(
                                null,                          // Componente padre (null para centrar)
                                "Ya no podras modificar el MARCA y MODELO del servicios",          // Mensaje
                                "Confirmación",                // Título de la ventana
                                JOptionPane.OK_CANCEL_OPTION, // Tipo de opciones
                                JOptionPane.QUESTION_MESSAGE   // Tipo de ícono
                            );
                
                if (confirmar == JOptionPane.OK_OPTION) {
                    //acuatlizamos los datos del servicio
                    s1.setMarca(view.txtMarca.getText());
                    s1.setModelo(view.txtModelo.getText());
                    //actualizamos el combo box de lista de repuestos
                    ActualizarRepuestos(s1);
                    //bloqueando los campos para que no hayan errores
                    view.txtMarca.setEnabled(false);
                    view.txtModelo.setEnabled(false);
                    
                    //borramos los repuestos anteriores
                    DefaultTableModel modelo = (DefaultTableModel) view.tablaRepuestos.getModel();
                    modelo.setRowCount(0); // Esto elimina todas las filas
                }
                
                break;
                
            case ("Eliminar"):
                
                Eliminador();
                break;
                
            case("Guardar"):
                //verificando la informacion
                if (Verificador()) {
                    //guardando
                    GuardarCambios(s1,index);
                    JOptionPane.showMessageDialog(view,"Cambios realizados con exito", "INFO", JOptionPane.INFORMATION_MESSAGE);
                    view.dispose();
                    ServiciosVista view1 = new ServiciosVista();
                    ServiciosAdmin controller1 = new ServiciosAdmin(model,view1);
                    controller1.IniciarVista();
                    
                }else{
                    JOptionPane.showMessageDialog(view,"Informacion incorrecta", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }
    
    
}
