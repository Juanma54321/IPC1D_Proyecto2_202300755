
package Controller;

import Model.Inventario;
import static Model.Inventario.libreria_inventario;
import View.RepuestosVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;


public class InventarioAdmin implements ActionListener {
    
    private Inventario model;
    private RepuestosVista view;

    public InventarioAdmin(Inventario model, RepuestosVista view) {
        this.model = model;
        this.view = view;
        this.view.BtnCargar.addActionListener(this);
    }
    
    //metodo para iniciar la vista
    public void IniciarRepuestos(){
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        view.setTitle("Inventario de repuestos");
        
        RefreshTabla();
    }
    
    //metodo para refrescar la tabla de los repuestos
    public void RefreshTabla(){
        int contador = model.ContadorRepuestos();
        
        
        
        //creando una instancia para poder agregar los datos en la tabla
        DefaultTableModel tablaNueva = (DefaultTableModel) view.TablaRepuestos.getModel();
        tablaNueva.setRowCount(0);
        //copiando cada elemento del inventario
        for (int i = 0; i < contador; i++) {
            Inventario b1 = new Inventario();
            b1=libreria_inventario[i];
            
            
            //guardando los datos en la nueva tabla
            tablaNueva.addRow(new Object[]{
                b1.getID(),
                b1.getNombre(),
                b1.getMarca(),
                b1.getModelo(),
                b1.getExistencia(),
                b1.getPrecio()
            });
        }
    }
    
    //metodo para obtener la ruta del archivo
    public String ObtenerRuta(){
    JFileChooser buscador = new JFileChooser();
    int seleccion = buscador.showOpenDialog(null);
    
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File ruta = buscador.getSelectedFile();
            
            return ruta.getAbsolutePath();
        }else{
            return ("Archivo no encontrado");
        
        }
    }
    
    //acciones que realizaran los botones
    public void actionPerformed(ActionEvent e){
        String opcion = e.getActionCommand();
        
        switch(opcion){
            case ("Cargar"):
                String ruta = ObtenerRuta();
                
                model.RegistroRepuestos(ruta, view);
                RefreshTabla();
                
                break;
        
        
        }
        
    }
}
