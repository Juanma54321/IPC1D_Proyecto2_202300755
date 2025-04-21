
package Controller;

import static Model.Inventario.libreria_inventario;
import Model.Servicios;
import static Model.Servicios.libreria_servicios;
import View.EliminarVista;
import View.ServiciosVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ServiciosAdmin implements ActionListener{
    private Servicios model;
    private ServiciosVista view;

    public ServiciosAdmin(Servicios model, ServiciosVista view) {
        this.model = model;
        this.view = view;
        this.view.BtnCargarServicios.addActionListener(this);
        this.view.BtnEliminar.addActionListener(this);
    }
    
    //metodo para iniciar la vista
    public void IniciarVista(){
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        view.setTitle("Servicios");
        RefreshTabla();
    
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
    
    //metodo para refrescar la tabla de servicios
    private void RefreshTabla(){
        int contador = model.ContadorServicios();
        
        //creando una instancia para poder agregar los datos en la tabla
        DefaultTableModel tablaNueva = (DefaultTableModel) view.tablaServicios.getModel();
        tablaNueva.setRowCount(0);
        //copiando cada elemento de los servicios
        for (int i = 0; i < contador; i++) {
            Servicios s1 =libreria_servicios[i];
            String Repuestos="";
            float precioTotal=s1.getPrecio_mano_obra();
            
            
            //obteniendo la lista de repuestos y buscandolos
            String[] listaRepuestos = s1.getLista_repuestos().split(";");
            for (int j = 0; j < libreria_inventario.length; j++) {
                if (libreria_inventario[j]!=null){
                    for (int k = 0; k < listaRepuestos.length; k++) {
                        if (libreria_inventario[j].getID().equals(listaRepuestos[k])) {
                            Repuestos=Repuestos+libreria_inventario[j].getNombre()+", ";
                            precioTotal= precioTotal+libreria_inventario[j].getPrecio();
                        }
                    }
                }
            }            
            
            //guardando los datos en la nueva tabla
            tablaNueva.addRow(new Object[]{
                s1.getID(),
                s1.getNombre_servicio(),
                s1.getMarca(),
                s1.getModelo(),
                Repuestos,
                s1.getPrecio_mano_obra(),
                precioTotal
                
            });
            
            
        }
    
    }
    
    //asignando acciones a los botones
    public void actionPerformed(ActionEvent e){
        
        String opcion = e.getActionCommand();
        
        switch (opcion){
            //accion del boton cargar
            case("Cargar"):
                //obteniendo la ruta y leyendo el archivo
                model.RegistroServicio(ObtenerRuta());
                RefreshTabla();
                JOptionPane.showMessageDialog(view,"Servicios cargados correctamente","INFO",JOptionPane.INFORMATION_MESSAGE);
                break;
        
            case("Eliminar"):
                
                if (model.ContadorServicios()>0) {
                    //iniciando la vista eliminar
                    EliminarVista view2 = new EliminarVista(null,true);
                    EliminarUniversal controller = new EliminarUniversal(view2);

                    controller.RefrescarLista(libreria_servicios, model.ContadorServicios());
                    controller.InicializarVista();
                    RefreshTabla();
                }else{
                    JOptionPane.showMessageDialog(view,"No existen servicios para borrar","ERROR",JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }
}
