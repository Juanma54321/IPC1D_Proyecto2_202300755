
package Model;

public class Vehiculos {
    private String placa;
    private String marca;
    private String modelo;
    private int anno;
    private String imagen;
    private String dueño;

    public static Vehiculos[] libreria_vehiculos= new Vehiculos[50];
    
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
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

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDueño() {
        return dueño;
    }

    public void setDueño(String dueño) {
        this.dueño = dueño;
    }
    
    //metodo para agregar vehiculos a libreria
    public void AgregarVehiculos(String listaConcatenada, String dueño){
        String[] lista_temp = listaConcatenada.split(";");
        
        Vehiculos v1 = new Vehiculos();
        
        v1.placa= lista_temp[0];
        v1.marca=lista_temp[1];
        v1.modelo=lista_temp[2];
        v1.anno=Integer.parseInt(lista_temp[3]);
        v1.imagen=lista_temp[4];
        v1.dueño=dueño;
        
        libreria_vehiculos[ContadorVehiculos()]=v1;
    }

    //metodo para contar cuantos vehiculos existentes hay en el sistema
    public int ContadorVehiculos() {
        int numero = 0;
        for (int i = 0; i <50; i++) {
            if (libreria_vehiculos[i]!=null) {
                numero++;
            }
        }
        return numero;
    }
    
}
