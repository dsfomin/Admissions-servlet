package com.servlet.model.dao;

import com.servlet.model.dao.impl.JDBCDaoFactory;
import com.servlet.model.dao.impl.JDBCFacultyDao;
import com.servlet.model.entity.Faculty;
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
public class FacultyDaoTest {

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
    JDBCFacultyDao facultyDao;

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        assertNotNull(connection);

        when(dataSource.getConnection()).thenReturn(connection);
        when(daoFactory.createFacultyDao()).thenReturn(new JDBCFacultyDao(connection));

        facultyDao = (JDBCFacultyDao) daoFactory.createFacultyDao();
    }


    @Test
    public void create() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(connection.createStatement()).thenReturn(statement);
        when(statement.execute()).thenReturn(true);
        when(statement.executeQuery(anyString())).thenReturn(resultSet);


        Faculty facultyFromDb = new Faculty("KNU", 2, 1, 1,
                "Math", "History", "Economics");
        facultyDao.create(facultyFromDb);

        verify(statement, times(1)).execute();
    }

}