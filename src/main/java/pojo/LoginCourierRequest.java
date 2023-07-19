package pojo;
public class LoginCourierRequest {
    private String login;
    private String password;
    public LoginCourierRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }
    public LoginCourierRequest() {
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

    public static LoginCourierRequest from(CourierCreateRequest courierCreateRequest) {
        return new LoginCourierRequest(courierCreateRequest.getLogin(), courierCreateRequest.getPassword());
    }
}

