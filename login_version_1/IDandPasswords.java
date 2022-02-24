import java.util.HashMap;

public class IDandPasswords {

    HashMap<String, String> logininfo = new HashMap<String,String>();

    IDandPasswords(){

        logininfo.put("khcho0125","050125");
        logininfo.put("Brometheus","PASSWORD");
        logininfo.put("Brocode","abc123");
    }

    protected HashMap getLoginInfo(){
        return logininfo;
    }
}
