
package Model;

import View.ProgresoAdminVista;
import View.ProgresoUserVista;

public class HiloColaEspera implements Runnable {
    private Usuarios user;
    private Servicios s;
    private ProgresoAdminVista viewAdmin;
    private ProgresoUserVista viewUser;
    
    //capacidad de 50 carros en el taller para esperar
    private static Usuarios[] colaEspera = new Usuarios[50];
    //indicator de carros en espera
    private int contadorCola=0;

    public HiloColaEspera(Usuarios user,Servicios s , ProgresoAdminVista viewAdmin, ProgresoUserVista viewUser) {
        this.user = user;
        this.viewAdmin = viewAdmin;
        this.viewUser = viewUser;
        this.s=s;
    }
    
    @Override
    public void run(){
        //añadiendo el carro a la lista de espera
        colaEspera[contadorCola]=user;
        contadorCola++;
    
    }
    
    //metodo donde se ingresaran los vehiculos
    private void Esperando(){
        for (int i = 0; i < contadorCola; i++) {
            if (contadorCola>1) {
                try{
                    Thread.sleep(i);
                }
            }
        }
    
    }
}
