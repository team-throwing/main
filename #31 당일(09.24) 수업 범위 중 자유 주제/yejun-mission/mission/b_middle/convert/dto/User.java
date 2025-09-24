package com.ohgiraffers.mission.b_middle.convert.dto;

public class User {
    private int id; // 접근불가하게 그냥 냅둬도 상관없나? getter/setter생성도 안하고?
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
