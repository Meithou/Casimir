package Controller;

import IHM.IHMHandler;
import Modele.Casimir;
import Modele.Messages.Message;
import Modele.User.User;

public class Controller {
    private IHMHandler ihmHandler;
    private Casimir casimir; // anchor
    public Controller(Casimir casimir){
        this.casimir = casimir;
        ihmHandler = new IHMHandler(this);
    }

    public void start_ihm(){
        ihmHandler.show_login();
    }
    public void loginError(){
        ihmHandler.login_error();
    }
    public void loginSuccess(String username){ihmHandler.login_success(username);}
    public void login(String nickname){
        casimir.login(nickname);
    }
    public void userLeave(String nickname){
        ihmHandler.userRemove(nickname);
    }
    public void logout(){casimir.logout();}
    public void userArrive(String nickname){
        ihmHandler.userAdd(nickname);
    }
    public void messageReceive(Message message){
        messageAdd(message);
    }
    public void messageSend(String nickname,String message){
        casimir.messageSend(nickname,message);
    }
    private void messageAdd(Message message){
        ihmHandler.messageAdd(message);
    };
}
