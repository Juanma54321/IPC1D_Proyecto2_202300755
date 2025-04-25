
package Controller;

import Model.Usuarios;
import View.VerVehiculosVista;
import java.awt.Component;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class VerVehiculo {
    private Usuarios user;
    private VerVehiculosVista view;

    public VerVehiculo(Usuarios user, VerVehiculosVista view) {
        this.user = user;
        this.view = view;
    }
    
    //metodo para iniciar la vista
    public void IniciarVista(){
     view.setVisible(true);
     view.setLocationRelativeTo(null);
     view.setTitle("Ver vehiculos");
     RefreshTabla(user);
     configurarTablaImagenes();
    }
    
    //metodo para llenar la tabla de datos
    public void RefreshTabla(Usuarios p1){
        String[] vehiculos=p1.getVehiculos();
        String[] vehiculo;
        
        //creando una instancia para poder agregar los datos en la tabla
        DefaultTableModel tablaNueva = (DefaultTableModel) view.tablaVehiculos.getModel();
        tablaNueva.setRowCount(0);
        
        
        for (int i = 0; i < vehiculos.length; i++) {
            vehiculo=vehiculos[i].split(",");
            
            // Preparar imagen
            String rutaImagen = vehiculo[3];
            ImageIcon icono = null;
            
            File archivo = new File(rutaImagen);
            
            if (archivo.exists()) {
                icono = new ImageIcon(rutaImagen);
                Image imagenEscalada = icono.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                icono = new ImageIcon(imagenEscalada);
            } else {
                System.out.println("No se encontró la imagen: " + rutaImagen);
            }
            
            //guardando los datos en la nueva tabla
             tablaNueva.addRow(new Object[]{
                vehiculo[0], // Placa
                vehiculo[1], // Marca
                vehiculo[2], // Modelo
                icono        // Imagen
            });
        }   
    }
    
    // Método para configurar la tabla y renderizar imágenes
    private void configurarTablaImagenes() {
        view.tablaVehiculos.setRowHeight(60); // Ajustamos espacio suficiente

        view.tablaVehiculos.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                            boolean isSelected, boolean hasFocus,
                                                            int row, int column) {
                JLabel label = new JLabel();
                label.setHorizontalAlignment(JLabel.CENTER);

                if (value instanceof ImageIcon) {
                    label.setIcon((ImageIcon) value);
                } else {
                    label.setText((value != null) ? value.toString() : "");
                }

                if (isSelected) {
                    label.setOpaque(true);
                    label.setBackground(table.getSelectionBackground());
                }

                return label;
            }
        });
    }
}
