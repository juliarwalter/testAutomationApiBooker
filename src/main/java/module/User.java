package module;

public class User {
    private String username;
    private String password;
    public User(){
        this.username = "admin";
        this.password = "password123";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
