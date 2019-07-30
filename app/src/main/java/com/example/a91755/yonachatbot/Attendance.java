package com.example.a91755.yonachatbot;

public class Attendance {
private String maths;
private String sem1,sem2,sem3,sem4,sem5,sem6;
private String id;
private String email;
    public Attendance(){

    }

public Attendance(String Id, String Email,String Maths,String Sem1,String Sem2,String Sem3,String Sem4,String Sem5,String Sem6){
        this.id = Id;
        this.maths = Maths;
        this.sem1 = Sem1;
        this.email = Email;
        this.sem2 = Sem2;
        this.sem3 = Sem3;
        this.sem4 = Sem4;
        this.sem5 = Sem5;
        this.sem6 = Sem6;
}
public String getId(){return id;}
public String getMaths(){return maths;}
public String getSem1(){return sem1;}

    public String getEmail() {
        return email;
    }

    public String getSem2() {
        return sem2;
    }

    public String getSem3() {
        return sem3;
    }

    public String getSem4() {
        return sem4;
    }

    public String getSem5() {
        return sem5;
    }

    public String getSem6() {
        return sem6;
    }

}
