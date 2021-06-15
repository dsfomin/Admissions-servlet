package com.servlet.model.dao.impl;

import com.servlet.model.dao.FacultyDao;
import com.servlet.model.dao.mapper.FacultyMapper;
import com.servlet.model.dao.mapper.StudentMapper;
import com.servlet.model.entity.Faculty;
import com.servlet.model.entity.Student;

import java.sql.*;
import java.util.*;

public class JDBCFacultyDao implements FacultyDao {
    private final Connection connection;

    public JDBCFacultyDao(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void create(Faculty entity) throws SQLException {
        final String query =
                "INSERT INTO faculties (title, totalPlaces,budgetPlaces," +
                        "contractPlaces,firstSubject,secondSubject,thirdSubject) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, entity.getTitle());
            statement.setInt(2, entity.getTotalPlaces());
            statement.setInt(3, entity.getBudgetPlaces());
            statement.setInt(4, entity.getContractPlaces());
            statement.setString(5, entity.getFirstSubject());
            statement.setString(6, entity.getSecondSubject());
            statement.setString(7, entity.getThirdSubject());
            statement.execute();
    }

    @Override
    public Optional<Faculty> findById(int id) throws SQLException {
        final String query = "select * from faculties where facultyid=?";
        FacultyMapper facultyMapper = new FacultyMapper();

        Optional<Faculty> faculty = Optional.empty();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            faculty = Optional.ofNullable(facultyMapper.extractFromResultSet(resultSet));
        }
        return faculty;
    }

    @Override
    public List<Faculty> findAll(int currentPage, int recordsPerPage,String sortBy, String order) {
        Map<Integer, Faculty> facultyMap = new HashMap<>();

        int start = currentPage * recordsPerPage - recordsPerPage;


        final String query = "select * from faculties" + " ORDER BY " + sortBy
                + " " + order + " LIMIT " + start + "," + recordsPerPage;
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            FacultyMapper facultyMapper = new FacultyMapper();
            List<Faculty> list = new ArrayList<>();
            while (rs.next()) {
                Faculty faculty = facultyMapper
                        .extractFromResultSet(rs);
                facultyMapper.makeUnique(facultyMap, faculty);
                list.add(faculty);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Faculty> findAll() {
        Map<Integer, Faculty> faculties = new HashMap<>();

        final String query = "select * from faculties";
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(query);

            FacultyMapper facultyMapper = new FacultyMapper();

            while (rs.next()) {
                Faculty faculty = facultyMapper
                        .extractFromResultSet(rs);
                facultyMapper.makeUnique(faculties, faculty);
            }
            return new ArrayList<>(faculties.values());
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
    public void update(Faculty entity) throws SQLException {
        final String query = "UPDATE faculties set title=?,totalPlaces=?,budgetPlaces=?," +
                "contractPlaces=?" +
                ",firstSubject=?,secondSubject=?,thirdSubject=? where facultyid=?";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, entity.getTitle());
        statement.setInt(2, entity.getTotalPlaces());
        statement.setInt(3, entity.getBudgetPlaces());
        statement.setInt(4, entity.getContractPlaces());
        statement.setString(5, entity.getFirstSubject());
        statement.setString(6, entity.getSecondSubject());
        statement.setString(7, entity.getThirdSubject());
        statement.setInt(8, entity.getFacultyId());
        statement.executeUpdate();

    }

    @Override
    public void delete(int id) {
        final String query = "DELETE FROM faculties WHERE facultyid=?";
        deleteEntity(id, query, connection);
    }

    static void deleteEntity(int id, String query, Connection connection) {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public List<Student> getAllStudents(int id) {
        final String query = "" +
                "select studentid,login,email,password,city,district,school,role,inSearch,onBudget,firstGrade,secondGrade,thirdGrade" +
                " from faculties \n" +
                "left join student_faculties using (facultyid)\n" +
                "left join students using (studentid) where facultyid=? and studentid is not null";
        StudentMapper studentMapper = new StudentMapper();
        List<Student> students = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                students.add(studentMapper.extractFromResultSet(resultSet));
            }
            return students;
        }catch (SQLException s){
            s.printStackTrace();
            return null;
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
