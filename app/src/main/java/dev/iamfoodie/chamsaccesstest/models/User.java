package dev.iamfoodie.chamsaccesstest.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "users")
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @Ignore
    private String _id;

    @Ignore
    private String password;

    private String first_name;
    private String last_name;
    private String email;
    private String mobile;

    public User() {
    }

    public User(String first_name, String last_name, String email, String password, String mobile) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
