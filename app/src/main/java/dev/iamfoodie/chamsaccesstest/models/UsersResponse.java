package dev.iamfoodie.chamsaccesstest.models;

import java.util.List;

public class UsersResponse {

    Meta _meta;
    List<User> data;

    public UsersResponse(Meta _meta, List<User> data) {
        this._meta = _meta;
        this.data = data;
    }

    public List<User> getData() {
        return data;
    }

    public Meta get_meta() {
        return _meta;
    }
}
