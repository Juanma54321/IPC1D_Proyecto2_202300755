
package Controller;

import Model.Usuarios;
import static Model.Usuarios.libreria_usuarios;
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
    }
    
    //metodo para inicializar la vista de usuarios
    public void IniciarVista(){
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        view.setTitle("Usuarios y Vehiculos");
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
                lista_temp[2],
                lista_temp[3]
            });
        }
    
    
    }
    
    //metodo para llenar la lista de clientes
    public void RefreshListaDueños(){
        view.ListaClientes.removeAllItems();
        
        for (int i = 0; i <model.ContadorUsuarios(); i++) {
            String dueño = libreria_usuarios[i].getNombre();
            
            view.ListaClientes.addItem(dueño);
        }
    
    }
    
    public void actionPerformed(ActionEvent e){
        String opcion = e.getActionCommand();
        
        switch (opcion){
            case ("Cargar"):
                model.RegistroUsuario(ObtenerRuta(), view);
                RefreshTabla();
                
                JOptionPane.showMessageDialog(view,"Usuarios registrados con exito", "INFO", JOptionPane.INFORMATION_MESSAGE);
                
                RefreshListaDueños();
                break;
        
            case ("Buscar"):
                int index = view.ListaClientes.getSelectedIndex();
                
                RefreshVehiculos(libreria_usuarios[index]);
                
                
                
                break;
                
        }
    }
}
