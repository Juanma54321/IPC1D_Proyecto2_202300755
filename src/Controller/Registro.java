
package Controller;

import Model.Usuarios;
import View.RegistroVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Registro implements ActionListener {
    private Usuarios p1;
    private RegistroVista view;

    public Registro(Usuarios p1, RegistroVista view) {
        this.p1 = p1;
        this.view = view;
        this.view.btnAñadir.addActionListener(this);
        this.view.btnBuscar.addActionListener(this);
    }
    
    //metodo para iniciar la vista 
    public void IniciarVista(){
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        view.setTitle("Registrar vehiculo");
    
    }
    
    //metodo para obtener la ruta de un archivo
    private String RutaArchivo(){
        JFileChooser buscador = new JFileChooser();
        int seleccion = buscador.showOpenDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File ruta = buscador.getSelectedFile();
            
            return ruta.getAbsolutePath();
        }else{
            return ("Archivo no encontrado");
        
        }
    
    }
    
    public void actionPerformed(ActionEvent e){
        
        String opcion = e.getActionCommand();
        
        //obteniendo los datos de la vista
        String placa = view.txtPlaca.getText();
        String marca = view.txtMarca.getText();
        String modelo = view.txtModelo.getText();
        String imagen;
        switch (opcion){
            //accion del boton buscar
            case("Buscar"):
                imagen=RutaArchivo();
                
                //verificando la extencion del archivo
                if (!imagen.contains(".jpg")) {
                    imagen="XXXX";
                    JOptionPane.showMessageDialog(view,"Imagen no valida, solo se perfime formato PNG o JPG", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                
                view.txtImg.setText(imagen);
                break;
            //accion del boton añadir vehiculo  
            case ("Añadir"):
                imagen = view.txtImg.getText();
                //verificando que no esten vacias las celdas
                boolean condicion =(!imagen.equals("") && !imagen.equals("XXXX")) && (!placa.equals("") && !placa.equals("000AAA")) && (!marca.equals("") && !marca.equals("Ingrese la marca")) && (!modelo.equals("") && !modelo.equals("Ingrese el modelo"));

                if (condicion) {
                    String concatenado=placa+","+marca+","+modelo+","+imagen;
                    //agregando el vehiculo
                    p1.AgregarVehiculo(p1, concatenado);
                    System.out.println(p1.getVehiculos()[0]);
                    
                    JOptionPane.showMessageDialog(view,"Vehiculo agregado correctamente", "INFO", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(view,"Porfavor llene los campos", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                
                break;
        }
        
        
    }
    
}
