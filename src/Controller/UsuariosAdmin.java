
package Controller;

import Model.Usuarios;
import static Model.Usuarios.libreria_usuarios;
import View.EditarUsuarioVista;
import View.EliminarVista;
import View.UsuariosAdminVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class UsuariosAdmin implements ActionListener{
    private UsuariosAdminVista view;
    private Usuarios model;

    public UsuariosAdmin(UsuariosAdminVista view, Usuarios model) {
        this.view = view;
        this.model = model;
        this.view.BtnCargarClientes.addActionListener(this);
        this.view.BtnBuscar.addActionListener(this);
        this.view.BtnEliminar.addActionListener(this);
        this.view.btnEditar.addActionListener(this);
    }
    
    //metodo para inicializar la vista de usuarios
    public void IniciarVista(){
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        view.setTitle("Usuarios y Vehiculos");
        RefreshListaDueños();
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
    
    //metodo para cargar la tabla con los usuarios
    public void RefreshTabla(){
        int numero = model.ContadorUsuarios();
        
        //creando una instancia para poder agregar los datos en la tabla
        DefaultTableModel tablaNueva = (DefaultTableModel) view.TablaClientes.getModel();
        tablaNueva.setRowCount(0);
        
        //copiando cada usuario
        for (int i = 0; i < numero; i++) {
            Usuarios p1 = libreria_usuarios[i];
            
            //guardando los datos en la nueva tabla
            tablaNueva.addRow(new Object[]{
                p1.getCui(),
                p1.getNombre(),
                p1.getNombre_usuario(),
            });
            
        }
    }
    
    //metodo para cargar los vehiculos de sus dueños
    public void RefreshVehiculos(Usuarios p1){
        
        //creando una isntacia para poder agregar los datos en la tabla
        DefaultTableModel tablaNueva = (DefaultTableModel) view.TablaVehiculos.getModel();
        tablaNueva.setRowCount(0);
        
        //copiando las propiedades de los carros concatenados
        String[] lista_concatenada = p1.getVehiculos();
        
        //copiando cada usuario
        for (int i = 0; i < lista_concatenada.length; i++) {
            
            //desconcatenado las propiedades del carro
            String[] lista_temp=lista_concatenada[i].split(",");
            
            //guardando los datos en la nueva tabla
            tablaNueva.addRow(new Object[]{
                lista_temp[0],
                lista_temp[1],
                lista_temp[2]
            });
        }
    
    
    }
    
    //metodo para llenar la lista de clientes
    public void RefreshListaDueños(){
        view.ListaClientes.removeAllItems();
        
        for (int i = 0; i <model.ContadorUsuarios(); i++) {
            view.ListaClientes.addItem(String.valueOf(libreria_usuarios[i].getCui())+" - "+libreria_usuarios[i].getNombre());
        }
    
    }
    
    //acciones que realizaran los botones
    public void actionPerformed(ActionEvent e){
        
        String opcion = e.getActionCommand();
        
        switch (opcion){
            //accion del boton cargar clientes
            case ("Cargar"):
                //abriendo el buscador de archivos y leyendo el archivo
                model.RegistroUsuario(ObtenerRuta(), view);
                //acutalizando la vista de la tabla
                RefreshTabla();
                //mensaje de infromacion
                JOptionPane.showMessageDialog(view,"Usuarios registrados con exito", "INFO", JOptionPane.INFORMATION_MESSAGE);
                //actualizando el combo box para la lista de vehiculos
                RefreshListaDueños();
                break;
            //accion del boton buscar
            case ("Buscar"):
                //obteniendo el numero de cliente que se desea buscar del combo box
                int index = view.ListaClientes.getSelectedIndex();
                if (index>=0 && libreria_usuarios[index].getVehiculos()!=null) {
                    JOptionPane.showMessageDialog(view,"Vehiculos encontrados","INFO",JOptionPane.INFORMATION_MESSAGE);
                    //actualizando la tabla de vehiculos
                    RefreshVehiculos(libreria_usuarios[index]);
                }else{
                    JOptionPane.showMessageDialog(view,"No se encontraron vehiculos","ERROR",JOptionPane.ERROR_MESSAGE);
                }
                break;
                
            //accion del boton eliminar    
            case ("Eliminar"):
                //verificando si ya existen clientes que eliminar
                if (model.ContadorUsuarios()>0) {
                    //iniciando la vista eliminar
                    EliminarVista view2 = new EliminarVista(null,true);
                    EliminarUniversal controller = new EliminarUniversal(view2);
                    
                    controller.RefrescarLista(libreria_usuarios, model.ContadorUsuarios());
                    controller.InicializarVista();
                    //actualizando la lista de clientes
                    RefreshListaDueños();
                    RefreshTabla();
                }else{
                    JOptionPane.showMessageDialog(view,"No existen usuarios registrados","ERROR",JOptionPane.ERROR_MESSAGE);
                }
                break;
            //accion del boton editar
            case ("Editar"):
                //verificando si ya existen clientes que editar
                if (model.ContadorUsuarios()>0) {
                    EditarUsuarioVista view3 = new EditarUsuarioVista();
                    
                    EditarClientes controller1 = new EditarClientes(model,view3);
                    //inciando la vista editar
                    controller1.IniciarVista();
                    view.dispose();
                    
                }else{
                    JOptionPane.showMessageDialog(view,"No existen usuarios registrados","ERROR",JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }
}
