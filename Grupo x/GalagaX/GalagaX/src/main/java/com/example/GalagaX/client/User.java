package com.example.GalagaX.client;

public class User {
    private String nickName;
    private int score;

    public User(String nickName, int score) {
        this.nickName = nickName;
        this.score = score;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
