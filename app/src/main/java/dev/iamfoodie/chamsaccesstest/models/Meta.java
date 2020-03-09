package dev.iamfoodie.chamsaccesstest.models;
public class Meta {
    String status_code;
    String success;
    String token;

    public Meta(String status_code, String success, String token) {
        this.status_code = status_code;
        this.success = success;
        this.token = token;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
