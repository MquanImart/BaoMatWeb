package Model;

import java.io.Serializable;

public class taikhoan implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String pass;
    private String matk;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    public String getMatk() {
        return matk;
    }

    public void setMatk(String matk) {
        this.matk = matk;
    }

    public taikhoan(String username, String pass) {
        this.username=username;
        this.pass=pass;
    }
    public taikhoan(String username, String pass, String matk) {
    	this.username=username;
    	this.pass=pass;
    	this.matk=matk;
    }

    public taikhoan() {}
}
