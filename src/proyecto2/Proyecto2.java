
package proyecto2;

import Controller.Login;
import Model.Usuarios;
import View.LoginVista;


public class Proyecto2 {

   
    public static void main(String[] args) {
        Usuarios model = new Usuarios();
        LoginVista view = new LoginVista();
        
        Login controller= new Login(model,view);
        
        controller.IniciarVista();
    }
    
}
