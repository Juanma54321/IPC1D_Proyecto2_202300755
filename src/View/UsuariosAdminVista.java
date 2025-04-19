/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

/**
 *
 * @author manum
 */
public class UsuariosAdminVista extends javax.swing.JFrame {

    /**
     * Creates new form UsuariosAdminVista
     */
    public UsuariosAdminVista() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        BtnCargarClientes = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        BtnGuardar = new javax.swing.JButton();
        tabla = new javax.swing.JScrollPane();
        TablaClientes = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaVehiculos = new javax.swing.JTable();
        ListaClientes = new javax.swing.JComboBox<>();
        BtnBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BtnCargarClientes.setBackground(new java.awt.Color(0, 51, 255));
        BtnCargarClientes.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        BtnCargarClientes.setForeground(new java.awt.Color(255, 255, 255));
        BtnCargarClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/CLientes1.png"))); // NOI18N
        BtnCargarClientes.setText("<html><center>Cargar Clientes</center></html>");
        BtnCargarClientes.setActionCommand("Cargar");
        BtnCargarClientes.setBorder(null);
        jPanel1.add(BtnCargarClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 120, 40));

        BtnEliminar.setBackground(new java.awt.Color(0, 51, 255));
        BtnEliminar.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        BtnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        BtnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Cambios.png"))); // NOI18N
        BtnEliminar.setText("Eliminar");
        BtnEliminar.setBorder(null);
        jPanel1.add(BtnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 120, 40));

        BtnGuardar.setBackground(new java.awt.Color(0, 51, 255));
        BtnGuardar.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        BtnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        BtnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Cambios.png"))); // NOI18N
        BtnGuardar.setText("Guardar");
        BtnGuardar.setBorder(null);
        jPanel1.add(BtnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 120, 40));

        TablaClientes.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        TablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CUI", "Nombre", "Usuario"
            }
        ));
        TablaClientes.setGridColor(new java.awt.Color(0, 0, 0));
        tabla.setViewportView(TablaClientes);

        jPanel1.add(tabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 90, 610, 150));

        TablaVehiculos.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 14)); // NOI18N
        TablaVehiculos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Pacla", "Marca", "Modelo", "Año"
            }
        ));
        TablaVehiculos.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(TablaVehiculos);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 440, 150));

        ListaClientes.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        ListaClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(ListaClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 140, 20));

        BtnBuscar.setBackground(new java.awt.Color(255, 0, 0));
        BtnBuscar.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 14)); // NOI18N
        BtnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        BtnBuscar.setText("<html><center>Buscar Vehiculos</center></html>");
        BtnBuscar.setActionCommand("Buscar");
        BtnBuscar.setBorder(null);
        jPanel1.add(BtnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 90, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UsuariosAdminVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UsuariosAdminVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UsuariosAdminVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UsuariosAdminVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UsuariosAdminVista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton BtnBuscar;
    public javax.swing.JButton BtnCargarClientes;
    public javax.swing.JButton BtnEliminar;
    public javax.swing.JButton BtnGuardar;
    public javax.swing.JComboBox<String> ListaClientes;
    public javax.swing.JTable TablaClientes;
    public javax.swing.JTable TablaVehiculos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JScrollPane tabla;
    // End of variables declaration//GEN-END:variables
}
