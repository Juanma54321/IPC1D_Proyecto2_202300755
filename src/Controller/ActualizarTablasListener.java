
package Controller;

import Model.Vehiculos;

public interface ActualizarTablasListener {
    void actualizarTablaEspera(Vehiculos[] colaEspera);
    void actualizarTablaServicio(Vehiculos[] colaServicio);
    void actualizarTablaListos(Vehiculos[] colaListos);
}
