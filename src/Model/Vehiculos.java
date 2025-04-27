
package Model;

import java.io.Serializable;

public class Vehiculos implements Serializable{
    private static final long serialVersionUID = 1L;
    private String placa;
    private String marca;
    private String modelo;
    private String imagen;
    private String dueño;
    private Servicios sercicio;
    private boolean dueñoOro;
    
    //la capacidad del taller
    public static Vehiculos[] libreria_vehiculos= new Vehiculos[50];

    public boolean isDueñoOro() {
        return dueñoOro;
    }

    public void setDueñoOro(boolean dueñoOro) {
        this.dueñoOro = dueñoOro;
    }
    
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setDueño(String dueño) {
        this.dueño = dueño;
    }

    public void setSercicio(Servicios sercicio) {
        this.sercicio = sercicio;
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getImagen() {
        return imagen;
    }

    public String getDueño() {
        return dueño;
    }

    public Servicios getSercicio() {
        return sercicio;
    }
    
    //metodo para contar cuantos vehiculos existen
    public int ContadorVehiculos(){
        int contador=0;
        for (int i = 0; i < libreria_vehiculos.length; i++) {
            if (libreria_vehiculos[i]!=null) {
                contador++;
            }
        }
        return contador;
    }
    
    //metodo para devolver un vehiculo
    public Vehiculos VehiculoEncontrado(String placa){
        for (int i = 0; i < ContadorVehiculos(); i++) {
            if (libreria_vehiculos[i].getPlaca().equalsIgnoreCase(placa)) {
                return libreria_vehiculos[i];
            }
        }
        return null;
    }
    
    //metodo para borrar el vehiculo de la lista
    public void BorrarDatos(Vehiculos v1){
        int x = ContadorVehiculos();
        
        for (int i = 0; i < x; i++) {
            if (libreria_vehiculos[i]==v1) {
                // Correr todos los elementos a la izquierda
                for (int j = i; j < x-1; j++) {
                    libreria_vehiculos[j]=libreria_vehiculos[j+1];
                }
                libreria_vehiculos[x - 1] = null; // El último queda vacío
                break; // Solo eliminamos el primer encontrado
            }
        }
    }
    
}
