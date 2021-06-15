package com.servlet.model.service;


import com.servlet.model.dao.DaoFactory;
import com.servlet.model.dao.StudentDao;
import com.servlet.model.entity.Faculty;
import com.servlet.model.entity.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class StudentService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Faculty> getAllFaculties(int id) {
        try (StudentDao dao = daoFactory.createStudentDao()) {
            return dao.getAllFaculties(id);
        }
    }

    public List<Student> getStudents() {
        try (StudentDao dao = daoFactory.createStudentDao()) {
            return dao.findAll();
        }
    }

    public List<Student> getAllStudents(int start, int total, String sortBy, String order) {
        try (StudentDao dao = daoFactory.createStudentDao()) {
            return dao.findAll(start, total, sortBy, order);
        }
    }

    public void createStudent(Student student) throws SQLException {
        try (StudentDao dao = daoFactory.createStudentDao()) {
            dao.create(student);
        }
    }

    public Optional<Student> findById(int id) throws SQLException {
        try (StudentDao dao = daoFactory.createStudentDao()) {
            return dao.findById(id);
        }
    }

    public void updateStudent(Student student) throws SQLException {
        try (StudentDao dao = daoFactory.createStudentDao()) {
            dao.update(student);
        }
    }

    public void deleteStudent(int id) {
        try (StudentDao dao = daoFactory.createStudentDao()) {
            dao.delete(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Optional<Student> findByLogin(String login) throws SQLException {
        try (StudentDao dao = daoFactory.createStudentDao()) {
            return dao.findByLogin(login);
        }
    }

    public void addFaculty(Student student, Faculty faculty) throws SQLException {
        try (StudentDao dao = daoFactory.createStudentDao()) {
            dao.addFaculty(student, faculty);
        }
    }

    public void setOneFaculty(int studentid, int facultyid) {
        try (StudentDao dao = daoFactory.createStudentDao()) {
            dao.setOneFaculty(studentid, facultyid);
        }
    }

    public boolean checkIfApplied(Student student, Faculty faculty) throws SQLException {
        try (StudentDao dao = daoFactory.createStudentDao()) {
            return dao.checkIfApplied(student, faculty);
        }
    }
}
