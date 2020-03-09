package dev.iamfoodie.chamsaccesstest.models;

public class LoginResponse {
    Meta _meta;
    User data;

    public LoginResponse(Meta _meta, User data) {
        this._meta = _meta;
        this.data = data;
    }

    public Meta get_meta() {
        return _meta;
    }

    public void set_meta(Meta _meta) {
        this._meta = _meta;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}

