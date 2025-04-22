
package Controller;

import Model.Inventario;
import static Model.Inventario.libreria_inventario;
import Model.Usuarios;
import static Model.Usuarios.libreria_usuarios;
import View.AdminVista;
import View.LoginVista;
import View.UserVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class Login implements ActionListener{
    
    private Usuarios model;
    private LoginVista view;

    public Login(Usuarios model, LoginVista view) {
        this.model = model;
        this.view = view;
        this.view.btnLogin.addActionListener(this);
        // Agregar el listener para detectar cuando se presiona la X
        this.view.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Previene cierre automático
        //accion que se realizara antes de cerrar el programa
        this.view.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Serializador();
                System.exit(0);             // Cierra el programa
            }
        });
    }
    
    //metodo inicializar la vista login
    public void IniciarVista(){
        view.setVisible(true);
        view.setLocationRelativeTo(null);
        view.setTitle("LOGIN");
        
        Deserializador();
    }
    
    //metodo para serializar
    private void Serializador(){
        try{
                FileOutputStream archivo = new FileOutputStream("usuarios.tms");
                ObjectOutputStream salida = new ObjectOutputStream(archivo);
                //objetos a serailizar 
                salida.writeObject(libreria_usuarios);
                salida.writeObject(libreria_inventario);
                System.out.println("Objeto serializado Correctamente");
            }catch (IOException l){
                System.out.println("el error es: "+ l.toString());
            }
    }
    
    //metodo para deserializar
    private void Deserializador(){
        try{
            FileInputStream archivoD = new FileInputStream ("usuarios.tms");
            ObjectInputStream entrada = new ObjectInputStream(archivoD);
            //deserializadon los objetos
            libreria_usuarios = (Usuarios[]) entrada.readObject();
            libreria_inventario=(Inventario[]) entrada.readObject();
            entrada.close();

            System.out.println("Objetos deserializados");
        }catch (IOException|ClassNotFoundException e){
            System.out.println("hola");
        }
    
    }
    
    public void actionPerformed(ActionEvent e){
        String user = view.txtUsuario.getText();
        String pass = view.txtContrasena.getText();
        
        byte contador=model.ConfirmarInformacion(user, pass);
        
        //si es un admin
        if (contador==1) {
            AdminVista view1 = new AdminVista();
            
            Admin controller = new Admin(view1);
            
            view.dispose();
            controller.IniciarVista();
        //si es un cliente
        }else if (contador==2) {
            UserVista view2= new UserVista();
            Usuarios p1= model.BuscadorDeUsuarios(user, pass);
            
            User controller1 = new User(p1,view2);
            
            controller1.IniciarVista();
            
            view.dispose();
            
        }
        else{
            JOptionPane.showMessageDialog(view, "Usuario o contraseña incorrectas", "ERROR", JOptionPane.ERROR_MESSAGE);
            view.txtUsuario.setText("Ingrese su usuario");
            view.txtContrasena.setText("**********");
        }
        
        
        
    }
}
