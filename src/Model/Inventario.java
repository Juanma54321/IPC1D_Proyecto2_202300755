
package Model;

import static Model.Usuarios.libreria_usuarios;
import View.LoginVista;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Inventario {
    private String ID;
    private String nombre;
    private String marca;
    private String modelo;
    private byte existencia;
    private float precio;
    
    //libreria donde se llevara el inventario
     public static Inventario [] libreria_inventario= new Inventario[100];

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public byte getExistencia() {
        return existencia;
    }

    public void setExistencia(byte existencia) {
        this.existencia = existencia;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    
    
    
    //metodo para poder registrar usuarios en el sistema
    public void RegistroRepuestos(String ruta, LoginVista view){
        
        //verificando la extencion del archivo
        if (ruta.contains(".tmr")) {
            
            //leyendo el archivo
            try(BufferedReader lector = new BufferedReader(new FileReader(ruta))){
                try{
                    //registrando los datos del archivo
                    String[] listatemporal;
                    int contador=0;

                    //guardando los datos en una lista temporal
                    listatemporal= lector.readLine().split("-");

                    //creando el objeto con las caracteristicas de un clinte
                    Usuarios p1 = new Usuarios();
                    
                    //registrando los datos de la lista temporal
                    p1.cui=Long.getLong(listatemporal[0]);
                    p1.nombre=listatemporal[1];
                    p1.nombre_usuario=listatemporal[2];
                    p1.password=listatemporal[3];
                    if (listatemporal[4].equals("oro")){
                        p1.cliente_oro=true;
                    }
                    p1.vehiculos=listatemporal[5].split(";");
                    
                    //guardando al cliente en la libreria global
                    libreria_usuarios[this.contador] = p1;
                    
                    this.contador++;
                }
                catch(IOException f){
                    //mensaje de error
                    JOptionPane.showMessageDialog(view,"Error al guardar los datos","ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }catch(IOException e){
            
                //mensaje de error
                JOptionPane.showMessageDialog(view,"Error al leer el archivo","ERROR", JOptionPane.ERROR_MESSAGE);
            }  
        }else{
            //mensaje de error
            JOptionPane.showMessageDialog(view,"Archivo no valido","ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    
}
