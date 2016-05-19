package csonline.net.br.smsgateway.model;

import java.io.Serializable;

public class LoginVO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String login;

    private String password;

    private String repeat;

    private String newPassword;

    private String newRepeat;

    private String email;

    public LoginVO() {
    }

    public LoginVO(String login, String password){
        this.login = login;
        this.password =  password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewRepeat() {
        return newRepeat;
    }

    public void setNewRepeat(String newRepeat) {
        this.newRepeat = newRepeat;
    }
    
    public String getEmail() {
            return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
