package IHM;

import Controller.Controller;
import Modele.Messages.Message;
import sun.applet.Main;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class IHMHandler {
    private Controller controller;
    private JFrame mainFrame;
    private Login login;
    private MainInterface mainInterface;

    public IHMHandler(Controller controller) {
        this.controller = controller;
        mainFrame = new JFrame("Casimir - Logiciel de Clavardage");
    }


    public void show_login() {
        login = new Login(controller);
        mainFrame.setContentPane(login.getPanel1());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
    public void show_main_interface(String username){
        mainInterface=new MainInterface(controller,username);
        mainFrame.setContentPane(mainInterface.getPanel1());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                controller.logout();
            }
        });
        mainFrame.pack();
        mainFrame.setVisible(true);

    }
    public void messageAdd(Message message){

    }
    public void userAdd(String nickname){
        mainInterface.addUser(nickname);
    }
    public void userRemove(String nickname){
        mainInterface.removeUser(nickname);
    }
    public void login_error(){
        login.show_error();
    }
    public void login_success(String username){
        show_main_interface(username);
    }
}
