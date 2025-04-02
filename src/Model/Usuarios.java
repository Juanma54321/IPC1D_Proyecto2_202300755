
package Model;

import View.LoginVista;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Usuarios {
    private long cui;
    private String nombre;
    private String nombre_usuario;
    private String password;
    private boolean cliente_oro=false;
    private String [] vehiculos;
    
    //libreria donde se registraran los usuarios para poder ingresar
    public static Usuarios [] libreria_usuarios =new Usuarios[50];
    
    //contador didamico para poder ingresar usuarios
    private static byte contador=0;

    //set y get de la clase
    public long getCui() {
        return cui;
    }

    public void setCui(long cui) {
        this.cui = cui;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCliente_oro() {
        return cliente_oro;
    }

    public void setCliente_oro(boolean cliente_oro) {
        this.cliente_oro = cliente_oro;
    }
    
    //metodo para poder registrar usuarios en el sistema
    public void RegistroUsuario(String ruta, LoginVista view){
        
        //verificando la extencion del archivo
        if (ruta.contains(".tmca")) {
            
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
