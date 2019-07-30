package com.example.a91755.yonachatbot;

public class Student {
    private String email;
    private String name;
    public Student (){

    }
    public Student(String Email,String Name){
        this.email = Email;
        this.name  = Name;
    }

    public String getEmail() {
        return email;
    }
    public String getName(){return name;}
}
