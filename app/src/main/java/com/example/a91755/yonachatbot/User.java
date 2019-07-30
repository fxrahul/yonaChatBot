package com.example.a91755.yonachatbot;

public class User {

    private String id;
    private String name;
    private String email;
    private String password;
    private String username;
    private String uniquekey;
    private String rollNo;

    public User(){
        //this constructor is required
    }

    public User(String Id, String Username, String Uniquekey, String Name, String Email, String Password, String RollNo) {
       this.id =Id;
       this.name = Name;
       this.email = Email;
       this.password = Password;
        this.username=Username;
        this.uniquekey=Uniquekey;
        this.rollNo = RollNo;
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

    @Override
    public String toString() {
        return this.id + " " + this.name;
    }
}
