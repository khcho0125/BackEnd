public class Main {

    public static void main(String[] args) {

        IDandPasswords iDandPasswords = new IDandPasswords();

        iDandPasswords.getLoginInfo();

        LoginPage loginPage = new LoginPage(iDandPasswords.getLoginInfo());
    }
}
