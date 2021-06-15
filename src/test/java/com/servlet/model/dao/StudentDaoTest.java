package com.servlet.model.dao;

import com.servlet.model.dao.impl.JDBCDaoFactory;
import com.servlet.model.dao.impl.JDBCStudentDao;
import com.servlet.model.entity.Student;
import com.servlet.model.entity.enums.Roles;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StudentDaoTest {

    @Mock
    DataSource dataSource;

    @Mock
    Connection connection;

    @Mock
    PreparedStatement statement;

    @Mock
    ResultSet resultSet;

    @Mock
    JDBCDaoFactory daoFactory;

    @InjectMocks
    JDBCStudentDao studentDao;

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        assertNotNull(connection);

        when(dataSource.getConnection()).thenReturn(connection);
        when(daoFactory.createStudentDao()).thenReturn(new JDBCStudentDao(connection));

        studentDao = (JDBCStudentDao) daoFactory.createStudentDao();
    }


    @Test
    public void create() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(connection.createStatement()).thenReturn(statement);
        when(statement.execute()).thenReturn(true);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);


        Student studentFromDb = new Student("Bob", "bob@mail.ru",
                "11111", "kiev", "kiev",
                "171", Roles.USER, true);
        studentDao.create(studentFromDb);

        verify(statement, times(1)).execute();
    }

}
