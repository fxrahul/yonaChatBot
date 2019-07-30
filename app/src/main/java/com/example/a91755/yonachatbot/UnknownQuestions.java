package com.example.a91755.yonachatbot;

public class UnknownQuestions {
    private String id,questions;
    public  UnknownQuestions(){

    }
    public  UnknownQuestions(String ID,String Questions){
        this.id = ID;
        this.questions = Questions;
    }

    public String getId() {
        return id;
    }

    public String getQuestions() {
        return questions;
    }
}
