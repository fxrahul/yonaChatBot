package com.example.a91755.yonachatbot;

public class Marks {
    private String cumm;
    private String monthly;
    private String id;
    private String email;
    public Marks(){

    }

    public Marks(String Id, String Maths,String Sem1,String Email){
        this.id = Id;
        this.cumm = Maths;
        this.monthly = Sem1;
        this.email = Email;
    }
    public String getId(){return id;}
    public String getCumm(){return cumm;}
    public String getMonthly(){return monthly;}

    public String getEmail() {
        return email;
    }
}