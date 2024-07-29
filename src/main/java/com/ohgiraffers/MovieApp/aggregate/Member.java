package com.ohgiraffers.MovieApp.aggregate;

import java.io.Serializable;
import java.time.LocalDate;

public class Member implements Serializable {
    private String id;
    private String pwd;
    private String name;
    private LocalDate birthDate;

    public Member() {
    }

    public Member(String id, String pwd, String name, LocalDate birthDate) {
        this.id = id;
        this.pwd = pwd;
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
