
package Controller;

import Model.HiloEstatico;
import Model.HiloTablas;
import Model.Servicios;
import static Model.Servicios.libreria_servicios;
import Model.Usuarios;
import Model.Vehiculos;
import static Model.Vehiculos.libreria_vehiculos;
import View.ProgresoUserVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

    public class TiemposEspera implements ActionListener{
   

    private Usuarios user;
    private Servicios s;
    private ProgresoUserVista viewUser;

    
    //capacidad de 50 carros en el taller para esperar
    public static Vehiculos[] colaEspera = new Vehiculos[50];
    //capacidad de 50 para hacer servicio
    public static Vehiculos[] colaServicio = new Vehiculos[50];
    
    //ticket del que esta siendo atendido
    public static int ticket=0;
    //indicator de carros en espera
    public static int contadorCola=0;
    
    //metodo para agregar vehiculos a la cola
    public void AgregarVehiculo(Vehiculos v1){
        
        //verificamos si es un vehiculo de un cliente oro
        if (!v1.isDueñoOro()) {
            //aumentamos el contador del cliente
            user.ClienteOro(user);
            // Guardamos el ID del vehículo en el arreglo
            colaEspera[contadorCola]=v1;
            v1.BorrarDatos(v1);
            contadorCola++;
        }else{
            if (ticket!=0) {
                //lo coloclamos al frente de la cola
                ticket--;
                colaEspera[ticket] = v1;
            }else{
               colaEspera[0]=v1;
               contadorCola++;
            }
            System.out.println("holasadfasdf");
        }
    }    
    
    //constructor
    public TiemposEspera(Usuarios user,Servicios s, ProgresoUserVista viewUser) {
        this.user=user;
        this.viewUser = viewUser;
        this.s=s;

        //acciones de los botones de la vista del cliente
        this.viewUser.btnAñadir.addActionListener(this);
        this.viewUser.btnProcesar.addActionListener(this);
    }
    //metodo para inciar la vista de user
    public void IniciarVistaUser(){
        viewUser.setVisible(true);
        viewUser.setTitle("Ver Progreso");
        viewUser.setLocationRelativeTo(null);
        MostrarDatos(user);
        
        //creando el hilo
        HiloEstatico.cargarColaEspera();
        
    
        //mostrando la tabla con vehiculos añadidos
        String[] vehiculos = user.getVehiculos();
        String[] placa;
        
        //creando una instancia para poder agregar los datos en la tabla
        DefaultTableModel tablaNueva = (DefaultTableModel) viewUser.TablaDatos.getModel();
        for (int i = 0; i < libreria_vehiculos.length; i++) {
            if (libreria_vehiculos[i]!=null) {
                for (int j = 0; j < vehiculos.length; j++) {
                    placa=vehiculos[j].split(",");
                    
                    if (libreria_vehiculos[i].getPlaca().equalsIgnoreCase(placa[0])) {
                        tablaNueva.addRow(new Object[]{
                            libreria_vehiculos[i].getPlaca(),
                            libreria_vehiculos[i].getMarca(),
                            libreria_vehiculos[i].getModelo(),
                            libreria_vehiculos[i].getSercicio().getNombre_servicio()
                        });
                    }
                }
            }
        }
    
    }    
    
    //metodo para mostrar los vehiculos del usuario
    private void MostrarDatos(Usuarios user) {
        
            //copiamos los vehiculos
            String[] vehiculos = user.getVehiculos();
            //removemos los items del combo box de vehiculos
            viewUser.ListaVehiculos.removeAllItems();
            for (int i = 0; i < vehiculos.length; i++) {
                String[] vehiculo= vehiculos[i].split(",");
                viewUser.ListaVehiculos.addItem(vehiculo[0] +" - "+ vehiculo[2]);
            }
            viewUser.ListaServicios.removeAllItems();
            for (int i = 0; i < s.ContadorServicios(); i++) {
                viewUser.ListaServicios.addItem(libreria_servicios[i].getNombre_servicio() + " - "+ libreria_servicios[i].getModelo());
            }
    }
    
    //metodo para verificar la marca del carro y el servicio
    private void CompararMarcas(){
        int carro = viewUser.ListaVehiculos.getSelectedIndex();
        int servicio = viewUser.ListaServicios.getSelectedIndex();
        
        //obteniendo las caracteristicas del carro selecionado
        String[] carros = user.getVehiculos();
        String[] caracteristicas = carros[carro].split(",");
        
        //si coinciden la marca y modelo
        if (caracteristicas[1].equalsIgnoreCase(libreria_servicios[servicio].getMarca()) && 
                caracteristicas[2].equalsIgnoreCase(libreria_servicios[servicio].getModelo())) {
            //creando una instancia para poder agregar los datos en la tabla
            DefaultTableModel tablaNueva = (DefaultTableModel) viewUser.TablaDatos.getModel();
           
            //guardando el vehiculo en el taller
            Vehiculos v1 = new Vehiculos();
            v1.setPlaca(caracteristicas[0]);
            v1.setMarca(caracteristicas[1]);    
            v1.setModelo(caracteristicas[2]);
            v1.setImagen( caracteristicas[3]);   
            v1.setDueño(user.getNombre());
            v1.setDueñoOro(user.isCliente_oro());
            v1.setSercicio(libreria_servicios[servicio]);
            
            //si no se a añadido algun carro
            if (tablaNueva.getRowCount()==0) {
                tablaNueva.setRowCount(0);
                tablaNueva.addRow(new Object[]{
                    v1.getPlaca(),
                    v1.getMarca(),
                    v1.getModelo(),
                    libreria_servicios[servicio].getNombre_servicio()
                });
            }else{
                tablaNueva.addRow(new Object[]{
                    v1.getPlaca(),
                    v1.getMarca(),
                    v1.getModelo(),
                    libreria_servicios[servicio].getNombre_servicio()
                });
            }
            v1.setCostoTotal(libreria_servicios[servicio].getPrecioTotal());
            libreria_vehiculos[v1.ContadorVehiculos()]=v1;
            
            
            JOptionPane.showMessageDialog(viewUser,"Agregado Correctamente", "INFO", JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(viewUser,"Marca y/o modelo no coinciden", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    
    }
    
    //acciones de los botones
    public void actionPerformed(ActionEvent e){
        String opcion = e.getActionCommand();
        
        switch(opcion){
            case ("Añadir"):
                CompararMarcas();
            break;
            case("Procesar"):
                if (viewUser.TablaDatos.getRowCount()!=0) {
                    Vehiculos v1= new Vehiculos();
                    String placa =(String) viewUser.TablaDatos.getValueAt(0, 0);
                    
                    DefaultTableModel tablanueva = (DefaultTableModel) viewUser.TablaDatos.getModel();
                    tablanueva.removeRow(0);
                    
                    AgregarVehiculo(v1.VehiculoEncontrado(placa));
                    JOptionPane.showMessageDialog(viewUser,"Vehiculo ingresado al taller", "INFO",JOptionPane.INFORMATION_MESSAGE);
                    
                }else{
                    JOptionPane.showMessageDialog(viewUser,"No tienes ningun vehiculo añadido", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                
            break;
        }
    }
}
