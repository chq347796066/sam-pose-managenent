package com.sam.pose.bean;

public class User {
    private String id;
    private String name;
    private int age;
    private String imagePath;

    public User(String id, String name, int age,String imagePath) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.imagePath=imagePath;
    }


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

