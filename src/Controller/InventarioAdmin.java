
package Controller;

import Model.Inventario;
import static Model.Inventario.libreria_inventario;
import View.EliminarVista1;
import View.EliminiarVista;
import View.RepuestosVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class InventarioAdmin implements ActionListener {
    
    private Inventario model;
    private RepuestosVista view;

    public InventarioAdmin(Inventario model, RepuestosVista view) {
        this.model = model;
        this.view = view;
        this.view.BtnCargar.addActionListener(this);
        this.view.BtnEliminar.addActionListener(this);
        this.view.BtnGuardar.addActionListener(this);
    }
    
    //metodo para iniciar la vista
    public void IniciarRepuestos(){
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        view.setTitle("Inventario de repuestos");
        RefreshTabla();
    }
    
    //metodo para refrescar la tabla de los repuestos
    public void RefreshTabla(){
        int contador = model.ContadorRepuestos();
        
        //creando una instancia para poder agregar los datos en la tabla
        DefaultTableModel tablaNueva = (DefaultTableModel) view.TablaRepuestos.getModel();
        tablaNueva.setRowCount(0);
        //copiando cada elemento del inventario
        for (int i = 0; i < contador; i++) {
            Inventario b1 = new Inventario();
            b1=libreria_inventario[i];
            
            
            //guardando los datos en la nueva tabla
            tablaNueva.addRow(new Object[]{
                b1.getID(),
                b1.getNombre(),
                b1.getMarca(),
                b1.getModelo(),
                b1.getExistencia(),
                b1.getPrecio()
            });
        }
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
    
    //metodo para cambiar los datos de la tabla
    public void CambiarInventario(){
        int fila = view.TablaRepuestos.getSelectedRow();
        
        //Forzando a que deje de editar la celda para poder guardar
        if (view.TablaRepuestos.isEditing()) {
            view.TablaRepuestos.getCellEditor().stopCellEditing();
        }
        
    
        if (fila != -1) {
        // Obtener los datos de la fila modificada
        String nuevoNombre = view.TablaRepuestos.getValueAt(fila, 1).toString();
        String nuevaMarca = view.TablaRepuestos.getValueAt(fila, 2).toString();
        String nuevoModelo = view.TablaRepuestos.getValueAt(fila, 3).toString();
        int nuevaExistencia = Integer.parseInt(view.TablaRepuestos.getValueAt(fila, 4).toString());
        float nuevoPrecio = Float.parseFloat(view.TablaRepuestos.getValueAt(fila, 5).toString());
        
        // Actualizar la lista
        libreria_inventario[fila].setNombre(nuevoNombre);
        libreria_inventario[fila].setMarca(nuevaMarca);
        libreria_inventario[fila].setModelo(nuevoModelo);
        libreria_inventario[fila].setExistencia(nuevaExistencia);
        libreria_inventario[fila].setPrecio(nuevoPrecio);
        
        JOptionPane.showMessageDialog(null, "Producto actualizado correctamente");
        RefreshTabla(); // para refrescar visualmente
        } else {
        JOptionPane.showMessageDialog(null, "Seleccione un producto para actualizar");
        }
    }
    
    //acciones que realizaran los botones
    public void actionPerformed(ActionEvent e){
        String opcion = e.getActionCommand();
        
        switch(opcion){
            
            //accion del boton Cargar
            case ("Cargar"):
                //solicitando la ruta del listado de repuestos
                String ruta = ObtenerRuta();
                //registrando el listado de repuestos
                model.RegistroRepuestos(ruta, view);
                
                RefreshTabla();
                JOptionPane.showMessageDialog(view,"Repuestos agregados correctamente", "INFO", JOptionPane.INFORMATION_MESSAGE);
                break;
                
            //accion del boton guardar    
            case ("Guardar"):
                
                if (model.ContadorRepuestos()>0 && view.TablaRepuestos.isEditing()) {
                    
                    view.BtnGuardar.requestFocus();
                    CambiarInventario();
                    RefreshTabla();
                }else if(model.ContadorRepuestos()>0 && !view.TablaRepuestos.isEditing()){
                    JOptionPane.showMessageDialog(view, "Tienes que selecionar una fila a editar", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(view, "Inventario vacio", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                
                break;
            
            //accion del boton eleminar
            case ("Eliminar"):
                
                if (model.ContadorRepuestos()>0) {
                    
                    EliminiarVista view1 = new EliminiarVista(null,true);
                
                    EliminarUniversal control = new EliminarUniversal(view1);

                    //llenando el combo box de los repuestos existentes
                    control.RefrescarLista(libreria_inventario, model.ContadorRepuestos());
                    control.InicializarVista();
                    
                    RefreshTabla();
                    
                    
                }else{
                    JOptionPane.showMessageDialog(view, "Inventario vacio", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                
                break;
        }
        
    }
}
