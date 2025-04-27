
package Controller;

import Model.Vehiculos;


public interface  ProgresoListener {
    void actualizarProgreso(int valor);
    void actualizarPlaca(String placa);
    void actualizarServicio(int valor2);
    void actualizarPlacaServicio(String placa2);
    void actualizarListo(int valor3);
    void actualizarPlacaListo(String placa3);
}
