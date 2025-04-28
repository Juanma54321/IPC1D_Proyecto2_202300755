
package Model;

import Controller.ActualizarTablasListener;
import static Model.HiloEstatico.colaListos;
import static Controller.TiemposEspera.colaEspera;
import static Controller.TiemposEspera.colaServicio;


public class HiloTablas {
   private static Thread hiloActualizarTablas;
    private static boolean corriendoTablas = false;
    private static ActualizarTablasListener listenerTablas;
    
    public static void setListenerTablas(ActualizarTablasListener nuevoListenerTablas) {
    listenerTablas = nuevoListenerTablas;
    }
    
    public static void actualizarTablas() {
        if (corriendoTablas) return; // Ya está corriendo, no crear otro
        corriendoTablas = true;
        hiloActualizarTablas = new Thread(() -> {
            while (true) {
                if (listenerTablas != null) {
                    // Actualizar las tres tablas enviando los arreglos actuales
                    listenerTablas.actualizarTablaEspera(colaEspera);
                    listenerTablas.actualizarTablaServicio(colaServicio);
                    listenerTablas.actualizarTablaListos(colaListos);
                }
                try {
                    Thread.sleep(200); // Actualiza cada 200ms para no saturar
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        hiloActualizarTablas.start();
    }
}
