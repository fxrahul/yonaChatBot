package com.example.a91755.yonachatbot;

public class StudentGeneralDetailsCopyUsers {
    public String student_details_id;
    public String attendanceID;
    private String marksID;
    private String id;
    private String name;
    private String email;
    private String password;
    private String username;
    private String uniquekey;
    private String rollNo;
    public StudentGeneralDetailsCopyUsers(){

    }
    public StudentGeneralDetailsCopyUsers(String IDDD,String IDD, String ID,String UID,String Email, String Name, String Password, String RollNo, String Uniquekey, String Username){
        this.student_details_id = IDDD;
        this.attendanceID = IDD;
        this.marksID = ID;
        this.id = UID;
        this.name = Name;
        this.email = Email;
        this.password = Password;
        this.username=Username;
        this.uniquekey=Uniquekey;
        this.rollNo = RollNo;

    }

    public String getStudent_details_id() {
        return student_details_id;
    }

    public String getAttendanceID() {
        return attendanceID;
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
