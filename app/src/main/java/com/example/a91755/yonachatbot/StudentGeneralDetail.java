package com.example.a91755.yonachatbot;

public class StudentGeneralDetail {
    public StudentGeneralDetail(){

    }
    private String id,admission_type,age,branch,contact_number,fees_payment_method,hsc_marks,ssc_marks,email;
    public StudentGeneralDetail(String Id, String Admission_type,String Age,String Branch,String Contact_number,String Fees_payment_method,String Hsc_marks,String Ssc_marks,String Email){
        this.id = Id;
        this.admission_type = Admission_type;;
        this.age = Age;
        this.branch = Branch;
        this.contact_number = Contact_number;
        this.fees_payment_method = Fees_payment_method;
        this.hsc_marks = Hsc_marks;
        this.ssc_marks = Ssc_marks;
        this.email = Email;

    }

    public String getId() {
        return id;
    }

    public String getAdmission_type() {
        return admission_type;
    }

    public String getAge() {
        return age;
    }

    public String getBranch() {
        return branch;
    }

    public String getContact_number() {
        return contact_number;
    }

    public String getFees_payment_method() {
        return fees_payment_method;
    }

    public String getHsc_marks() {
        return hsc_marks;
    }

    public String getSsc_marks() {
        return ssc_marks;
    }

    public String getEmail() {
        return email;
    }
}
