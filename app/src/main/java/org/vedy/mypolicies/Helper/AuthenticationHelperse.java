package org.vedy.mypolicies.Helper;

public class AuthenticationHelperse {
    String uid;
    String name;
    String age;
    String gender;
    String state;


    public AuthenticationHelperse(String uid, String name, String age, String Gender) {
        this.uid = uid;
        this.name = name;
        this.age = age;
        this.gender = Gender;
    }

    public AuthenticationHelperse() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return  name ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
