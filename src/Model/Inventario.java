
package Model;

import View.RepuestosVista;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Inventario {
    private String ID;
    private String nombre;
    private String marca;
    private String modelo;
    private int existencia;
    private float precio;
    
    //libreria donde se llevara el inventario
    public static Inventario [] libreria_inventario= new Inventario[100];
     
    private static int contador_dinamico=0;

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

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    
    
    
    //metodo para poder registrar usuarios en el sistema
    public void RegistroRepuestos(String ruta, RepuestosVista view){
        
        //verificando la extencion del archivo
        if (ruta.contains(".tmr")) {
            
            //leyendo el archivo
            try(BufferedReader lector = new BufferedReader(new FileReader(ruta))){
                try{
                    //registrando los datos del archivo
                    String[] listatemporal;
                    String listaConcatenada;

                    //leyendo cada liena del archivo
                    while((listaConcatenada=lector.readLine())!=null){
                        boolean repuesto_existente=true;
                        
                        //guardando los datos en una lista temporal
                        listatemporal= listaConcatenada.split("-");
                        
                        //verificando que la lista este correcta
                        if (listatemporal.length!=5) {
                            JOptionPane.showMessageDialog(view,"Error al guardar los datos","ERROR", JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        
                        //creando el objeto con las caracteristicas de un clinte
                        Inventario b1 = new Inventario();
                        
                        //registrando los datos de la lista temporal
                        b1.nombre=listatemporal[0];
                        b1.marca=listatemporal[1];
                        b1.modelo=listatemporal[2];
                        b1.existencia=Integer.parseInt(listatemporal[3]);
                        b1.precio=Float.parseFloat(listatemporal[4]);
                        
                        //buscando si el repuesto ya existe en el inventario
                        for (int i = 0; i < ContadorRepuestos(); i++) {
                            //si existe, se sumara a la existencia
                            if (libreria_inventario[i].getNombre().equals(b1.nombre ) && libreria_inventario[i].getMarca().equals(b1.marca) && libreria_inventario[i].getModelo().equals(b1.modelo) && libreria_inventario[i].getPrecio()==b1.precio) {
                                int existencia_original= libreria_inventario[i].getExistencia();
                                //sumamdo la nueva existencia
                                existencia_original= existencia_original+b1.existencia;
                                
                                libreria_inventario[i].setExistencia(existencia_original);
                                
                                repuesto_existente=false;
                                break;
                            }
                        }
                        
                        //si el repuesto no existe
                        if (repuesto_existente) {
                            b1.ID=String.valueOf(b1.nombre.length())+b1.modelo.charAt(0)+String.valueOf(b1.marca.length());

                            //guardando el repuesto en la libreria global
                            libreria_inventario[ContadorRepuestos()]=b1;
                            this.contador_dinamico++;
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
    
    
    //metodo para contar cuantos repuestos existen
    public int ContadorRepuestos(){
        int numero=0;
        for (int i = 0; i < 50; i++) {
            if (libreria_inventario[i]!=null) {
                numero++;
            }
        }
        return numero;
    }
    
    
    
    
    
}
