package com.servlet.model.dao.impl;


import com.servlet.model.dao.StudentDao;
import com.servlet.model.dao.mapper.FacultyMapper;
import com.servlet.model.dao.mapper.StudentMapper;
import com.servlet.model.entity.Faculty;
import com.servlet.model.entity.Student;

import java.sql.*;
import java.util.*;

public class JDBCStudentDao implements StudentDao {
    private final Connection connection;


    public JDBCStudentDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Student entity) throws SQLException {
        final String query = "INSERT INTO students (login,email,password,city,district,school,inSearch,onBudget,role)" +
                " VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, entity.getLogin());
        statement.setString(2, entity.getEmail());
        statement.setString(3, entity.getPassword());
        statement.setString(4, entity.getCity());
        statement.setString(5, entity.getDistrict());
        statement.setString(6, entity.getSchool());
        statement.setBoolean(7, entity.isInSearch());
        statement.setBoolean(8, entity.isOnBudget());
        statement.setString(9, entity.getRoles().name());
        statement.execute();

    }

    @Override
    public Optional<Student> findById(int id) throws SQLException {
        final String query = "select * from students where studentid=?";
        StudentMapper studentMapper = new StudentMapper();

        Optional<Student> student = Optional.empty();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            student = Optional.ofNullable(studentMapper.extractFromResultSet(resultSet));
        }
        return student;
    }


    @Override
    public Optional<Student> findByLogin(String login) throws SQLException {
        final String query = "select * from students where login=?";
        StudentMapper studentMapper = new StudentMapper();

        Optional<Student> student = Optional.empty();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, login);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            student = Optional.ofNullable(studentMapper.extractFromResultSet(resultSet));
        }
        return student;
    }

    @Override
    public void setOneFaculty(int studentid, int facultyid) {
        final String query = "DELETE FROM student_faculties WHERE studentid=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, studentid);
            statement.execute();
            statement = connection.prepareStatement("INSERT INTO student_faculties set studentid=?,facultyid=?");
            statement.setInt(1, studentid);
            statement.setInt(2, facultyid);
            statement.execute();
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    @Override
    public List<Student> findAll(int start, int total, String sortBy, String order) {
        Map<Integer, Student> students = new HashMap<>();

        final String query = "select * from students " + " GROUP BY " + sortBy + " ORDER BY " + sortBy + " " + order +
                " LIMIT " + (start - 1) + "," + total;
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            StudentMapper studentMapper = new StudentMapper();
            List<Student> list = new ArrayList<>();
            while (rs.next()) {
                Student student = studentMapper
                        .extractFromResultSet(rs);
                studentMapper.makeUnique(students, student);
                list.add(student);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Student> findAll() {
        Map<Integer, Student> students = new HashMap<>();

        final String query = "select * from students";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            StudentMapper studentMapper = new StudentMapper();

            while (rs.next()) {
                Student student = studentMapper
                        .extractFromResultSet(rs);
                studentMapper.makeUnique(students, student);
            }
            return new ArrayList<>(students.values());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void update(Student entity) throws SQLException {
        final String query = "UPDATE students set login=?,email=?,password=?,city=?" +
                ",district=?,school=?,role=?,inSearch=?,onBudget=?,firstGrade=?,secondGrade=?,thirdGrade=? where studentid=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, entity.getLogin());
        statement.setString(2, entity.getEmail());
        statement.setString(3, entity.getPassword());
        statement.setString(4, entity.getCity());
        statement.setString(5, entity.getDistrict());
        statement.setString(6, entity.getSchool());
        statement.setString(7, entity.getRoles().name());
        statement.setBoolean(8, entity.isInSearch());
        statement.setBoolean(9, entity.isOnBudget());
        statement.setInt(10, entity.getFirstGrade());
        statement.setInt(11, entity.getSecondGrade());
        statement.setInt(12, entity.getThirdGrade());
        statement.setInt(13, entity.getId());
        statement.executeUpdate();
    }

    @Override
    public void delete(int id) {
        final String query = "DELETE FROM students WHERE studentid=?";
        JDBCFacultyDao.deleteEntity(id, query, connection);
    }


    @Override
    public void addFaculty(Student student, Faculty faculty) {
        final String query = "INSERT INTO student_faculties set studentid=?,facultyid=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, student.getId());
            statement.setInt(2, faculty.getFacultyId());
            statement.execute();
        } catch (SQLException s) {
            s.printStackTrace();
        }

    }

    @Override
    public boolean checkIfApplied(Student student, Faculty faculty) throws SQLException {
        final String checkIfApplied = "SELECT * from student_faculties where studentid=? and facultyid=?";
        PreparedStatement statement = connection.prepareStatement(checkIfApplied);
        statement.setInt(1, student.getId());
        statement.setInt(2, faculty.getFacultyId());
        ResultSet resultSet = statement.executeQuery();
        return resultSet.next();

    }

    @Override
    public List<Faculty> getAllFaculties(int id) {
        final String query = "" +
                "select facultyid,title,totalPlaces,budgetPlaces,contractPlaces,firstSubject,secondSubject,thirdSubject" +
                " from students \n" +
                "left join student_faculties using (studentid)\n" +
                "left join faculties using (facultyid) where studentid=? and facultyid is not null";
        FacultyMapper facultyMapper = new FacultyMapper();
        List<Faculty> faculties = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                faculties.add(facultyMapper.extractFromResultSet(resultSet));
            }
            return faculties;
        } catch (SQLException s) {
            s.printStackTrace();
            return null;
        }

    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
