
package Model;

import View.UsuariosAdminVista;
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

    //usuario administrativo
    String usuariopriv= "Juanma321";
    String passwordpriv="hola";
        
    
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

    public String[] getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(String[] vehiculos) {
        this.vehiculos = vehiculos;
    }
    
    
    //metodo para poder registrar usuarios en el sistema
    public void RegistroUsuario(String ruta, UsuariosAdminVista view){
        boolean usuarioRepetido=false;
        
        
        //verificando la extencion del archivo
        if (ruta.contains(".tmca")) {
            
            //leyendo el archivo
            try(BufferedReader lector = new BufferedReader(new FileReader(ruta))){
                try{
                    //registrando los datos del archivo
                    String[] listatemporal;
                    String listaConcatenada;
                    
                    //leyendo cada linea del archivo
                    while((listaConcatenada=lector.readLine())!=null){
                        usuarioRepetido=false;
    
                        //guardando los datos en una lista temporal
                        listatemporal= listaConcatenada.split("-");
                        
                        //verificando si la lista esta correcta
                        if (listatemporal.length!=6) {
                            JOptionPane.showMessageDialog(view,"error al leer el archivo","ERROR", JOptionPane.ERROR_MESSAGE);
                            break;
                            
                        }
                        
                        //creando el objeto con las caracteristicas de un clinte
                        Usuarios p1 = new Usuarios();
                        //registrando los datos de la lista temporal
                        p1.cui=Long.parseLong(listatemporal[0]);
                        p1.nombre=listatemporal[1];
                        p1.nombre_usuario=listatemporal[2];
                        p1.password=listatemporal[3];
                        if (listatemporal[4].equals("oro")){
                            p1.cliente_oro=true;
                        }
                        p1.vehiculos=listatemporal[5].split(";");
                        
                        //verificando si no existe el usuario
                        for (int i = 0; i < ContadorUsuarios(); i++) {
                            if (libreria_usuarios[i].getCui()==p1.cui && libreria_usuarios[i].getNombre().equals(p1.getNombre())) {
                                usuarioRepetido=true;
                                break;
                            }
                        }
                        //si no existen usuarios repetidos, se guardan
                        if (!usuarioRepetido) {
                            //guardando al cliente en la libreria global
                            libreria_usuarios[ContadorUsuarios()] = p1;
                        }
                    }
                    
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
    
    //metodo para contar cuantos usuarios existen
    public int ContadorUsuarios(){
        int numero=0;
        for (int i = 0; i < 50; i++) {
            if (libreria_usuarios[i]!=null) {
                numero++;
            }
        }
    
        return numero;
    }
    
    //metodo para verificar los datos de login
    public byte ConfirmarInformacion(String user, String pass){
        byte condicion=0;
        
        //verificando si es un usuario administrativo
        if (user.equals("Juanma321") && pass.equals("hola")) {
            condicion=1;
            return condicion;
        }
        
        //recorriendo toda la libreria de usuarios registrados
        for (int i = 0; i < ContadorUsuarios(); i++) {
            
            //verificando si existe el usuario
            if(user.equals(libreria_usuarios[i].nombre_usuario) && pass.equals(libreria_usuarios[i].getPassword())){
                condicion=2;
                return condicion;
            }
        }
        return condicion;
    }
}
