package com.usermanagement.data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;

public class UserData implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String id;
    @NotNull
    @Size(min = 3,max = 15)
    private String userName;
    @NotNull
    @Size(min = 3,max = 15)
    private String name;
    @NotNull
    @Size(min = 3,max = 50)
    private String lastName;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String phone;
    @NotNull
    @Size(min = 3,max = 10)
    private String passwd;
    @NotNull
    private String birthDate;
    private boolean isAdmin;

    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
