package com.servlet.model.entity;

import com.servlet.model.entity.enums.Roles;

import java.util.ArrayList;
import java.util.List;

public class Student implements Comparable<Student> {

    private int id;
    private String login;
    private String email;
    private String password;
    private String city;
    private String district;
    private String school;
    List<Faculty> faculties = new ArrayList<>();
    private int firstGrade;
    private int secondGrade;
    private int thirdGrade;
    private Roles roles;
    private boolean inSearch = true;
    private boolean onBudget = false;

    public Student() {}

    public Student(String login, String email) {
        this.login = login;
        this.email = email;
    }

    public Student(String login, String email, String password,
                   String city, String district, String school,
                   Roles roles, boolean inSearch) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.city = city;
        this.district = district;
        this.school = school;
        this.roles = roles;
        this.inSearch = inSearch;
    }

    public double getAverageGrade() {
        return (double) (firstGrade + secondGrade + thirdGrade) / 3;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getSchool() {
        return school;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<Faculty> faculties) {
        this.faculties = faculties;
    }

    public boolean isInSearch() {
        return inSearch;
    }

    public void setInSearch(boolean inSearch) {
        this.inSearch = inSearch;
    }

    public int getFirstGrade() {
        return firstGrade;
    }

    public void setFirstGrade(int firstGrade) {
        this.firstGrade = firstGrade;
    }

    public int getSecondGrade() {
        return secondGrade;
    }

    public void setSecondGrade(int secondGrade) {
        this.secondGrade = secondGrade;
    }

    public int getThirdGrade() {
        return thirdGrade;
    }

    public void setThirdGrade(int thirdGrade) {
        this.thirdGrade = thirdGrade;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public boolean isOnBudget() {
        return onBudget;
    }

    public void setOnBudget(boolean onBudget) {
        this.onBudget = onBudget;
    }

    @Override
    public int compareTo(Student o) {
        double avgThisGrade = (this.firstGrade + this.secondGrade + this.thirdGrade) / 3.0;
        double avgThatGrade = (o.firstGrade + o.secondGrade + o.thirdGrade) / 3.0;
        return -Double.compare(avgThisGrade, avgThatGrade);
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", school='" + school + '\'' +
                ", faculties=" + faculties +
                ", firstGrade=" + firstGrade +
                ", secondGrade=" + secondGrade +
                ", thirdGrade=" + thirdGrade +
                ", roles=" + roles +
                ", inSearch=" + inSearch +
                ", onBudget=" + onBudget +
                '}';
    }
}
