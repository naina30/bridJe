package com.example.bridje;

public class User { //Creating a class for storing custom fields

    //Not storing password
    public String enroll, dateofBirth, email;

    public User() //Blank constructor to read the values back
    {

    }

    public User(String enroll, String dateofBirth, String email) {
        this.enroll = enroll;
        this.dateofBirth = dateofBirth;
         this.email = email;
    }
}
