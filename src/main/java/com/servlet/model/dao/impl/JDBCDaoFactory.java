package com.servlet.model.dao.impl;


import com.servlet.model.dao.DaoFactory;
import com.servlet.model.dao.FacultyDao;
import com.servlet.model.dao.StudentDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private final DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public FacultyDao createFacultyDao() {
        return new JDBCFacultyDao(getConnection());
    }
    @Override
    public StudentDao createStudentDao() {
        return new JDBCStudentDao(getConnection());
    }

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
