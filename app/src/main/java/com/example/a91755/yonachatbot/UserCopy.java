package com.example.a91755.yonachatbot;

public class UserCopy {
    private String marksID;
    private String id;
    private String name;
    private String email;
    private String password;
    private String username;
    private String uniquekey;
    private String rollNo;
    public UserCopy(){

    }
    public UserCopy(String ID,String UID,String Email, String Name, String Password, String RollNo, String Uniquekey, String Username){
        this.marksID = ID;
        this.id = UID;
        this.name = Name;
        this.email = Email;
        this.password = Password;
        this.username=Username;
        this.uniquekey=Uniquekey;
        this.rollNo = RollNo;

    }

    public String getMarksID() {
        return marksID;
    }

    public String getId() {
        return id;
    }
    public String getUsername(){return username;}

    public String getName() {
        return name;
    }

    public String getRollNo(){ return rollNo; }

    public String getEmail(){
        return  email;
    }
    public String getPassword(){
        return  password;
    }

    public String getUniquekey(){return uniquekey;}
}
