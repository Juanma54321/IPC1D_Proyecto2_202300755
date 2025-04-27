
package Controller;

import Model.Servicios;
import static Model.Servicios.libreria_servicios;
import Model.Usuarios;
import Model.Vehiculos;
import static Model.Vehiculos.libreria_vehiculos;
import View.ProgresoAdminVista;
import View.ProgresoUserVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

    public class TiemposEspera implements ActionListener{
   
    private Usuarios model;
    private Usuarios user;
    private Servicios s;
    private ProgresoAdminVista viewAdmin;
    private ProgresoUserVista viewUser;
    private Vehiculos veh;
    
    //capacidad de 50 carros en el taller para esperar
    private static Vehiculos[] colaEspera = new Vehiculos[50];
    //ticket del que esta siendo atendido
    private static int ticket=0;
    //indicator de carros en espera
    private static int contadorCola=0;
    
    //metodo para agregar vehiculos a la cola
    public void AgregarVehiculo(Vehiculos v1){
        // Guardamos el ID del vehículo en el arreglo
        colaEspera[contadorCola]=v1;
        System.out.println("vehiculo agregado: "+ v1.getPlaca());
        v1.BorrarDatos(v1);
        contadorCola++;
    }
    
    public TiemposEspera(Usuarios user,Usuarios model,Servicios s,Vehiculos veh, ProgresoAdminVista viewAdmin, ProgresoUserVista viewUser) {
        this.model = model;
        this.user=user;
        this.viewAdmin = viewAdmin;
        this.viewUser = viewUser;
        this.s=s;
        this.veh=veh;
        //acciones de los botones de la vista del cliente
        this.viewUser.btnAñadir.addActionListener(this);
        this.viewUser.btnProcesar.addActionListener(this);
    }
    
    //metodo para iniciar la vista de Adim
    public void InciarVistaAdmin(){
        viewAdmin.setVisible(true);
        viewAdmin.setTitle("Progreso Autos");
        viewAdmin.setLocationRelativeTo(null);
    
    }
    
    //metodo para inciar la vista de user
    public void IniciarVistaUser(){
        viewUser.setVisible(true);
        viewUser.setTitle("Ver Progreso");
        viewUser.setLocationRelativeTo(null);
        MostrarDatos(user);
    
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
                    
                    AgregarVehiculo(v1.VehiculoEncontrado(placa));
                    JOptionPane.showMessageDialog(viewUser,"Vehiculo ingresado al taller", "INFO",JOptionPane.INFORMATION_MESSAGE);
                    new Thread(this::cargarColaEspera).start();
                }else{
                    JOptionPane.showMessageDialog(viewUser,"No tienes ningun vehiculo añadido", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                
            break;
        }
    }
    
    
    private void cargarColaEspera(){
        //el taller estara abierto siempre, esperado que entren clientes
        while(true){
            
            // verificar que aun queden por procesar en la cola, comparando el numero de registro con el numero total de registros
            if (ticket<contadorCola) {
                System.out.println("hola");
                // obtener el vehiculo que se va a procesar
                Vehiculos v1 = colaEspera[ticket];
                //mostramos que vehiculo esta esperando
                viewUser.PlacaEsperando.setText(v1.getPlaca());
                // simular la barra de 0 a 100
                for (int i = 0; i < 100; i++) {
                    final int progreso = i; // valor que se asigna a la barra de progreso
                    SwingUtilities.invokeLater(() -> viewUser.BarraEspera.setValue(progreso));
                    try {
                        Thread.sleep(100); // Espera entre cada incremento 
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); //si falla colocar el hilo como interrumpido
                    }
                }
                // -- mandar los registros o vehiculos a otro vector para poder pasar a otro proceso--
                ticket++;
                
            } else {
                try {
                    Thread.sleep(500); // espera 500 ms si no hay nada que procesar
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // interrumpir hilo
                }
            }
            
        
        
        }
    }
    
}
