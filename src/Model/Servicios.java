
package Model;

import static Model.Inventario.libreria_inventario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.JOptionPane;

public class Servicios implements Serializable{
    private static final long serialVersionUID = 2L;
    private String ID;
    private String nombre_servicio;
    private String marca;
    private String modelo;
    private String lista_repuestos;
    private float precio_mano_obra;
    private float precioTotal;
    
    //libreria donde se guardaran todos los servicios
    public static Servicios[] libreria_servicios = new Servicios[50];

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    
    public String getNombre_servicio() {
        return nombre_servicio;
    }

    public void setNombre_servicio(String nombre_servicio) {
        this.nombre_servicio = nombre_servicio;
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

    public String getLista_repuestos() {
        return lista_repuestos;
    }

    public void setLista_repuestos(String lista_repuestos) {
        this.lista_repuestos = lista_repuestos;
    }

    public float getPrecio_mano_obra() {
        return precio_mano_obra;
    }

    public void setPrecio_mano_obra(float precio_mano_obra) {
        this.precio_mano_obra = precio_mano_obra;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }
    
    //metodo para ingresar servicios en el sistema
    public void RegistroServicio(String ruta){
        
        
        //verificando la exencion del archivo
        if (ruta.contains(".tms")) {
            //leyendo el archivo
            try(BufferedReader lector = new BufferedReader(new FileReader(ruta))){
                try{
                    //registrando los datos del archivo
                    String[] listatemporal;
                    String listaConcatenada;
                    
                    //variables de control
                    boolean servicioRepetido=false;
                    int repuestosValido=0;
                    int servicioNoValido=0;
                    
                    //leyendo cada linea del archivo
                    while((listaConcatenada=lector.readLine())!=null){
                      
                        //restableciendo las variables de control variables de control
                        servicioRepetido=false;
                        repuestosValido=0;
                        
                        
                        //desconcatenando el servicio
                        listatemporal= listaConcatenada.split("-");
                        
                        //desconcatenando la lista de repuestos
                        String[] listaRepuestostemporal =listatemporal[3].split(";");
                        
                        //creando un objeto con caracteristicas de un servicio
                        Servicios s1 = new Servicios();
                        //guardando los datos
                        s1.nombre_servicio=listatemporal[0];
                        s1.setMarca(listatemporal[1]);
                        s1.modelo=listatemporal[2];
                        s1.lista_repuestos=listatemporal[3];
                        s1.precio_mano_obra=Float.parseFloat(listatemporal[4]);
                        s1.ID= String.valueOf(listaRepuestostemporal.length)+String.valueOf(ContadorServicios())+"-"+String.valueOf(s1.modelo.length())+s1.marca.charAt(1);
                        
                                                
                        if (listatemporal.length==5) {
                            //verificando si los repuestos son validos
                            for (int i = 0; i < libreria_inventario.length; i++) {
                                if (libreria_inventario[i]!=null) {
                                    for (int j = 0; j < listaRepuestostemporal.length; j++) {
                                        if (libreria_inventario[i].getID().equals(listaRepuestostemporal[j])) {
                                            //comparando la marca y modelo del repuesto con el del servicio
                                            if((libreria_inventario[i].getMarca().equalsIgnoreCase(s1.getMarca()) && libreria_inventario[i].getModelo().equalsIgnoreCase(s1.getModelo())) || libreria_inventario[i].getModelo().equalsIgnoreCase("cualquiera")){
                                                repuestosValido++;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        
                        if (listaRepuestostemporal.length!=0 && listaRepuestostemporal[0].equals("")) {
                            repuestosValido++;
                        }
                        
                        //verificando si el servicios existe
                        for (int i = 0; i < ContadorServicios(); i++) {
                            if (libreria_servicios[i].getID().equals(s1.ID)) {
                                servicioRepetido=true;
                                servicioNoValido++;
                            }
                        }   
                        
                        //verificando si es valido el servicio
                        if (repuestosValido!=listaRepuestostemporal.length) {
                            servicioNoValido++;
                        }
                        if(repuestosValido==listaRepuestostemporal.length && !servicioRepetido){
                            //registrando el servicio
                            libreria_servicios[ContadorServicios()]=s1;
                        }
                    }
                    //registramos el precio total
                    PrecioTotal();
                    if (servicioNoValido!=0) {
                        JOptionPane.showMessageDialog(null,servicioNoValido+" servicios no son validos", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                    
                }catch(IOException f){
                    JOptionPane.showMessageDialog(null,"Error al guardar los servicios", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }catch(IOException e){
                JOptionPane.showMessageDialog(null,"Error al leer el arhcivo", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null,"Archivo no valido", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //metodo para contar los servicios existentes
    public int ContadorServicios(){
        int numero=0;
        for (int i = 0; i < libreria_servicios.length; i++) {
            if (libreria_servicios[i]!=null) {
                numero++;
            }
        }
        return numero;
    }
    
    //metodo para contar el precio total
    public void PrecioTotal(){
        
        //copiando cada elemento de los servicios
        for (int i = 0; i < ContadorServicios(); i++) {
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
            
            s1.setPrecioTotal(precioTotal);
            libreria_servicios[i]=s1;
            
        }
    }
    
}
