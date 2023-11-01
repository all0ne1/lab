package com.example.laba;

public class Students {
    int id;
    String name,surname;
    int age,mark;

    public Students(int id, String name, String surname,int age, int mark) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.mark = mark;

    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public int getMark() {
        return mark;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
