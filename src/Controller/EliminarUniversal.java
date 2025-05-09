
package Controller;

import static Model.Inventario.libreria_inventario;
import static Model.Servicios.libreria_servicios;
import static Model.Usuarios.libreria_usuarios;

import View.EliminarVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class EliminarUniversal implements ActionListener{
    private EliminarVista view;
    private boolean LibreriaRepuestos=false;
    private boolean LibreriaUsuarios=false;
    private boolean LibreriaServicios=false;

    public EliminarUniversal(EliminarVista view) {
        this.view = view;
        this.view.BtnEliminar.addActionListener(this);
    }
    
    //metodo para iniciar la vista
    public void InicializarVista(){
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        view.setTitle("Eliminar Cuentas/Repuestos");
    }
    
    //metodo para poder ingresar la libreria del que se quiera eliminar un elemento
    public void RefrescarLista(Object[] lista, int numero){
        
        //removiendo los items en el combobox
        view.ListaEliminar.removeAllItems();
        
        //si se desea eliminar un repuesto
        if (libreria_inventario==lista) {
            for (int i = 0; i < numero; i++) {
                view.ListaEliminar.addItem(libreria_inventario[i].getID() +"  -  "+libreria_inventario[i].getNombre());
            }
            LibreriaRepuestos=true;
        }
        
        //si se desea eliminar un usuario
        if (libreria_usuarios==lista) {
            for (int i = 0; i < numero; i++) {
                view.ListaEliminar.addItem(libreria_usuarios[i].getCui()+"  -  "+ libreria_usuarios[i].getNombre());
            }
            LibreriaUsuarios=true;
        }
        
        //si se desea eliminar un servicio
        if (libreria_servicios==lista) {
            for (int i = 0; i < numero; i++) {
                view.ListaEliminar.addItem(libreria_servicios[i].getID()+" - "+libreria_servicios[i].getNombre_servicio());
            }
            LibreriaServicios=true;
        }
    
    }
    
    //metodo para eliminar un elemnto de las librerias
    public void EliminarElemento(int posicion, int numero){
        
        //si lo que esta dentro del combo box son los repuestos
        if (LibreriaRepuestos) {
            //eliminando el repuesto seleccionado
            libreria_inventario[posicion]=null;
            //corriendo los datos despues del elemento eliminado
            for (int i = 0; i < (numero-posicion); i++) {
                libreria_inventario[posicion+i]=libreria_inventario[posicion+i+1];
            }
        }
        
        //si lo que esta dentro del combo box son los usuarios
        if (LibreriaUsuarios) {
            //eliminando el usuario seleccionado
            libreria_usuarios[posicion]=null;
            //corriendo los datos despues del elemento eliminado
            for (int i = 0; i < (numero-posicion); i++) {
                libreria_usuarios[posicion+i]=libreria_usuarios[posicion+i+1];
            }
        }
        
        //si lo que esta dentro del combo box son los servicios
        if (LibreriaServicios) {
            //eliminando el servicios seleccionado
            libreria_servicios[posicion]=null;
            //corriendo los datos despues del elemento eliminado
            for (int i = 0; i < (numero-posicion); i++) {
                libreria_servicios[posicion+i]=libreria_servicios[posicion+i+1];
            }
        }
        
    }
    
    
    public void actionPerformed(ActionEvent e){
        //capturando los datos necesarios para eliminiar un dato
        int index = view.ListaEliminar.getSelectedIndex();
        int elementos = view.ListaEliminar.getItemCount();
    
        
        EliminarElemento(index,elementos);
        
        //mensaje de confirmacion
        JOptionPane.showMessageDialog(view,"elemento eliminado con exito","INFO", JOptionPane.INFORMATION_MESSAGE);
        
        
        view.setVisible(false);
        
    }
}
