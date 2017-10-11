package za.ac.tut.usedbook.usedbook.viewModel;

/**
 * Created by gracem on 2017/10/04.
 */
public class CredentialModel {
    private int username;
    private String password;

    public CredentialModel() {
    }

    public CredentialModel(int username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getUsername() {
        return username;
    }

    public void setUsername(int username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
