
package Controller;

import static Controller.TiemposEspera.colaEspera;
import static Controller.TiemposEspera.colaServicio;
import static Controller.TiemposEspera.contadorCola;
import static Controller.TiemposEspera.ticket;
import Model.Vehiculos;


public class HiloEstatico {
    private static Thread hilo;
    private static Thread hilo2;
    private static Thread hilo3;
    private static boolean corriendo = false;
    private static boolean corriendoServicio = false;
    private static boolean corriendoListo = false;
    private static ProgresoListener listener;
    private static int progreso = 0;
    private static int progresoServicio=0;
    public static int ticket2=0;
    public static int contadorColaServicio=0;
    public static int ticket3=0;
    public static int contadorColaListo=0;
    public static int progresoListo=0;
    public static Vehiculos[] colaListos = new Vehiculos[50];
    
    
    public static void setListener(ProgresoListener nuevoListener) {
        listener = nuevoListener;

        // Si ya llevábamos progreso, actualizar de inmediato la nueva vista
        if (listener != null) {
            //barra de espera
            listener.actualizarProgreso(progreso);
            //vehiculo en espera
            if (colaEspera[ticket]!=null) {
               listener.actualizarPlaca(colaEspera[ticket].getPlaca());
            }
            //barra de servicio
            listener.actualizarServicio(progresoServicio);
            //vehiculo en servicio
            if (colaServicio[ticket2]!=null) {
                listener.actualizarPlacaServicio(colaServicio[ticket2].getPlaca());
            }
            //barra de vehiculos listos
            listener.actualizarListo(progresoListo);
            //vahiculo listo
            if (colaListos[ticket3]!=null) {
                listener.actualizarPlacaListo(colaListos[ticket3].getPlaca());
            }
        }
    }
    
    
    public static void cargarColaEspera(){
        
        if (corriendo) return; // Si ya corre, no crear otro

        corriendo = true;
        
        hilo = new Thread(() -> {
            
        //el taller estara abierto siempre, esperado que entren clientes
        while(true){
            
            // verificar que aun queden por procesar en la cola, comparando el numero de registro con el numero total de registros
            if (ticket<contadorCola) {
                // obtener el vehiculo que se va a procesar
                Vehiculos v1 = colaEspera[ticket];
                //borramos el vehiculo de la cola de espera para pasalo a la cola de servicio
                colaEspera[ticket]=null;
                ticket++;
                //si esta vacio la cola de servicio pasa directo
                if (contadorColaServicio!=0) {
                    // simular la barra de 0 a 100
                    for (int i = 0; i < 100; i++) {
                        final int revisando = i; // valor que se asigna a la barra de progreso
                        progreso=revisando;
                        //mostramos que vehiculo esta esperando
                        listener.actualizarProgreso(progreso);
                        listener.actualizarPlaca(v1.getPlaca());
                        try {
                            Thread.sleep(110); // Espera entre cada incremento, para que la espera total sean 11 segundos 

                        } catch (InterruptedException e) {

                            Thread.currentThread().interrupt(); //si falla colocar el hilo como interrumpido

                        }

                    }
                }
                // -- mandar los registros o vehiculos a otro vector para poder pasar a otro proceso--
                colaServicio[contadorColaServicio]=v1;    
                contadorColaServicio++;
                
                //iniciamos el otro hilo
                cargaColaServicio();
                
            } else {
                //reiniciamos los valores de control si no hay nadie en la cola
                ticket=0;
                contadorCola=0;
                
                try {
                    Thread.sleep(500); // espera 500 ms si no hay nada que procesar
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // interrumpir hilo
                }
            }
        
            listener.actualizarProgreso(0);
            listener.actualizarPlaca("000XXX");
            }
        });
        hilo.start();
    }
    
    public static void cargaColaServicio(){
        
        if(corriendoServicio) return; //si ya corre, no crear otro
        corriendoServicio=true;
        
        //lo que realizara el hilo
        hilo2 = new Thread(() -> {
        while(true){
            // verificar que aun queden por procesar en la cola, comparando el numero de registro con el numero total de registros
            if (ticket2<contadorColaServicio) {
                //obtener el vehiculo que se va a procesar
                Vehiculos v2 = colaServicio[ticket2];
                 
                // simular la barra de 0 a 100
                for (int i = 0; i < 100; i++) {
                    progresoServicio=i;// valor que se asigna a la barra de progreso
                    //actualizamos la vista
                    listener.actualizarServicio(progresoServicio);
                    listener.actualizarPlacaServicio(v2.getPlaca());
                    try{
                        Thread.sleep(50); // Espera entre cada incremento, para que la espera total sean 5 segundos 
                    }catch(InterruptedException a){
                        Thread.currentThread().interrupt(); //si falla colocar el hilo como interrumpido
                    }
                }
                //mandamos el vehiculo al siguiente proceso
                colaListos[contadorColaListo]=v2;
                //borramos el vehiculo de la cola de espera para pasalo a la cola de listos
                colaServicio[ticket2]=null;
                contadorColaListo++;
                ticket2++;
                
                //iniciamos el otro hilo
                cargaColaListo();
                
            }else{
                //reiniciamos los valores de control si no hay nadie en la cola
                ticket2=0;
                contadorColaServicio=0;
                try {
                    Thread.sleep(500); // espera 500 ms si no hay nada que procesar
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // interrumpir hilo
                }
            }
        //si no hay nadie en la cola, restablecemos los datos
        listener.actualizarServicio(0);
        listener.actualizarPlacaServicio("000XXX");
        
        
        }
        });
        //iniciamos el hilo
        hilo2.start();
    
    }

    public static void cargaColaListo(){
        if(corriendoListo)return; // si ya corre el hilo, no crear otro
        corriendoListo=true;
        
        hilo3 = new Thread(() -> {
            while(true){
                // verificar que aun queden por procesar en la cola, comparando el numero de registro con el numero total de registros
                if (ticket3<contadorColaListo) {
                    //obtener el vehiculo que se va a procesar
                    Vehiculos v3 = colaListos[ticket3];
                    
                    // simular la barra de 0 a 100
                    for (int i = 0; i < 100; i++) {
                        progresoListo=i;// valor que se asigna a la barra de progreso
                        //actualizamos la vista
                        listener.actualizarListo(progresoListo);
                        listener.actualizarPlacaListo(v3.getPlaca());
                        
                        try{
                            Thread.sleep(20);// Espera entre cada incremento, para que la espera total sean 2 segundos 
                        }catch(InterruptedException f){
                            Thread.currentThread().interrupt(); //si falla colocar el hilo como interrumpido
                        } 
                    }
                    ticket3++;
                }else{
                     //reiniciamos los valores de control si no hay nadie en la cola
                    ticket3=0;
                    contadorColaListo=0;
                    try {
                        Thread.sleep(500); // espera 500 ms si no hay nada que procesar
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // interrumpir hilo
                    }
                
                }
                //si no hay nadie en la cola, restablecemos los datos
                listener.actualizarListo(0);
                listener.actualizarPlacaListo("000XXX");
                
            
            }
        });
        hilo3.start();
        
        
    }
}
