
package Controller;

import Model.Usuarios;
import static Model.Usuarios.libreria_usuarios;
import View.EditarUsuarioVista;
import View.UsuariosAdminVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class EditarClientes implements ActionListener{
    private Usuarios model;
    private EditarUsuarioVista view;

    public EditarClientes(Usuarios model, EditarUsuarioVista view) {
        this.model = model;
        this.view = view;
        this.view.btnSeleccionar.addActionListener(this);
        this.view.btnBuscar.addActionListener(this);
        this.view.btnGuardar.addActionListener(this);
    }
    
    //metodo para inicializar la vista
    public void IniciarVista(){
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        view.setTitle("Editar Usuario");
        view.btnGuardar.setEnabled(false);
        RefreshListaClientes();
        
    }
    
    //metodo para actualizar el combo box
    private void RefreshListaClientes() {
        //vaciando el combo box
        view.listaClientes.removeAllItems();
        //ingresando cada cliente en el combo box
        for (int i = 0; i < model.ContadorUsuarios(); i++) {
            view.listaClientes.addItem(libreria_usuarios[i].getCui()+" - "+ libreria_usuarios[i].getNombre());
        }
    }
    
    //metodo para actualizar el combo box de vehiculos
    private void RefreshListaVehiculos(Usuarios p1){
        //borrando los datos anteriores del combo box
        view.listaVehiculos.removeAllItems();
        //obteniendo la lista de carros
        String[] listaConcatenada = p1.getVehiculos();
        //copiando cada elemento y agregandolo al combo box
        for (int i = 0; i < listaConcatenada.length; i++) {
            String[] CarroUnico = listaConcatenada[i].split(",");
            view.listaVehiculos.addItem(CarroUnico[0]+" - "+CarroUnico[1]);
        }
        
    }
    
    //metodo para actualizar las propiedades del vehiculo
    private void ActualizarPropiedades(int index, Usuarios p1){
        //obteniendo la lista de vehiculos
        String[] listaConcatenada=p1.getVehiculos();
        //obteniendo solo el vehiculo seleccionado y desconcatenando
        String[] vehiculoUnico=listaConcatenada[index].split(",");
        //actualizando la vista
        view.txtPlaca.setText(vehiculoUnico[0]);
        view.txtMarca.setText(vehiculoUnico[1]);
        view.txtModelo.setText(vehiculoUnico[2]);
    }
    
    //metodo para guardar los cambios realizados
    private void GuardarCambios(Usuarios p1, int index,int x){
        //guardando los datos personales
        p1.setNombre(view.txtnombre.getText());
        p1.setCui(Long.parseLong(view.txtCUI.getText()));
        p1.setNombre_usuario(view.txtUsuario.getText());
        
        
        //guardando los datos del vehiculo si se modificaron
        if (!view.txtPlaca.getText().equals("Placa")) {
            String[] listaConcatenada= p1.getVehiculos();
        
            String[] vehiculoUnico = listaConcatenada[index].split(",");

            vehiculoUnico[0]=view.txtPlaca.getText();
            vehiculoUnico[1]=view.txtMarca.getText();
            vehiculoUnico[2]=view.txtModelo.getText();
            //concatenando las propiedades para guardar
            String vehiculoConcatenado = String.join(",", vehiculoUnico);

            listaConcatenada[index]=vehiculoConcatenado;

            p1.setVehiculos(listaConcatenada); 
        }
        
        
        //ingresando el nuevo usuario a la libreria
        libreria_usuarios[x]=p1;
    }
    
    //metodo para verificar si todos los datos son correctos
    private boolean Verificador(){
        boolean condicion=false;
        if (!view.txtUsuario.getText().equals("") && view.txtCUI.getText().matches("\\d+") && !view.txtnombre.getText().equals("") && !view.txtPlaca.getText().equals("") && !view.txtMarca.getText().equals("") && !view.txtModelo.getText().equals("")) {
            condicion = true;
        }
        
        
        return condicion;
    }
    
    //asignando acciones a los botones
    public void actionPerformed (ActionEvent e){
        //obteniendo parametros de la vista
        int x = view.listaClientes.getSelectedIndex();
        String opcion = e.getActionCommand();
        //obteniendo el usuario seleccionado
        Usuarios p1= libreria_usuarios[x];
        //obteniendo el vehiculo que se desea editar
        int index=view.listaVehiculos.getSelectedIndex();
        
        switch(opcion){
            //accion del boton seleccionar
            case ("Seleccionar"):
                //cambiando los datos d ela vista
                view.txtnombre.setText(p1.getNombre());
                view.txtCUI.setText(String.valueOf(p1.getCui()));
                view.txtUsuario.setText(p1.getNombre_usuario());
                //bloqueando el boton para que no hayan errores
                view.btnSeleccionar.setEnabled(false);
                view.listaClientes.setEnabled(false);
                view.btnGuardar.setEnabled(true);
                RefreshListaVehiculos(p1);
                
                JOptionPane.showMessageDialog(view,"Cliente encontrado","INFO",JOptionPane.INFORMATION_MESSAGE);
                
                break;
            //accion del boton buscar
            case("Buscar"):
                if (x>-1) {
                    //actualizando las propiedades del vehiculo
                    ActualizarPropiedades(index,p1);
                    //bloqueando el boton para que no hayan errores
                    view.btnBuscar.setEnabled(false);
                    JOptionPane.showMessageDialog(view,"Vehiculo encontrado","INFO",JOptionPane.INFORMATION_MESSAGE);
                }else{
                   JOptionPane.showMessageDialog(view,"Selecciona a un cliente primero","ERROR",JOptionPane.ERROR_MESSAGE);
                }
                break;
            //accion del boton guardar
            case("Guardar"):
                //verificando si los campos llenados estan correctos
                if (Verificador()) {
                    GuardarCambios(p1,index,x);
                    JOptionPane.showMessageDialog(view,"Cambios realizados","INFO",JOptionPane.INFORMATION_MESSAGE);
                    view.dispose();

                    //regresando a la vista anterior
                    UsuariosAdminVista view2 = new UsuariosAdminVista();
                    UsuariosAdmin controller = new UsuariosAdmin(view2,model);
                    controller.IniciarVista();
                }else{
                    JOptionPane.showMessageDialog(view,"Datos incorrectos","ERROR",JOptionPane.ERROR_MESSAGE);
                }
                
                
                break;
        }
        
    
    }
    
}
