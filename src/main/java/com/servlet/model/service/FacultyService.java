package com.servlet.model.service;


import com.servlet.model.dao.DaoFactory;
import com.servlet.model.dao.FacultyDao;
import com.servlet.model.entity.Faculty;
import com.servlet.model.entity.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class FacultyService {

    DaoFactory daoFactory = DaoFactory.getInstance();

    public List<Faculty> getAllFaculties(int start, int total, String sortBy, String order) {
        try (FacultyDao dao = daoFactory.createFacultyDao()) {
            return dao.findAll(start, total, sortBy, order);
        }
    }

    public List<Faculty> getFaculties() {
        try (FacultyDao dao = daoFactory.createFacultyDao()) {
            return dao.findAll();
        }
    }

    public void createFaculty(Faculty faculty) throws SQLException {
        try (FacultyDao dao = daoFactory.createFacultyDao()) {
            dao.create(faculty);
        }
    }

    public Optional<Faculty> findById(int id) throws SQLException {
        try (FacultyDao dao = daoFactory.createFacultyDao()) {
            return dao.findById(id);
        }
    }

    public void updateFaculty(Faculty faculty) throws SQLException {
        try (FacultyDao dao = daoFactory.createFacultyDao()) {
            dao.update(faculty);
        }
    }

    public void deleteFaculty(int id) {
        try (FacultyDao dao = daoFactory.createFacultyDao()) {
            dao.delete(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Student> getAllStudents(int id) {
        try (FacultyDao dao = daoFactory.createFacultyDao()) {
            return dao.getAllStudents(id);
        }
    }
}