package com.servlet.servlets;

import com.servlet.model.dao.DaoFactory;
import com.servlet.model.dao.StudentDao;
import com.servlet.model.entity.Student;
import com.servlet.model.entity.enums.Roles;
import com.servlet.model.service.FacultyService;
import com.servlet.model.service.StudentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MainServletTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    RequestDispatcher dispatcher;

    @Mock
    StudentService studentService;

    @Mock
    DaoFactory daoFactory;

    @Mock
    StudentDao studentDao;

    @Mock
    DataSource dataSource;

    @Mock
    Connection connection;

    MainServlet servlet;

    @Before
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        dispatcher = mock(RequestDispatcher.class);
        studentService = mock(StudentService.class);
        daoFactory = mock(DaoFactory.class);

        servlet = new MainServlet();
        servlet.init();
    }

    @Test
    public void addFacultyPageTest() throws IOException, ServletException {
        when(request.getRequestURI()).thenReturn("addFacultyPage");
        when(request.getRequestDispatcher("/addFaculty.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void loginTest() throws IOException, ServletException, SQLException {
        Student studentFromDb = new Student("Bob", "bob@mail.ru",
                "11111", "kiev", "kiev",
                "171", Roles.USER, true);
        studentDao.create(studentFromDb);

        when(daoFactory.createStudentDao()).thenReturn(studentDao);
        when(dataSource.getConnection()).thenReturn(connection);
        when(request.getRequestURI()).thenReturn("login");
        when(request.getRequestDispatcher("/WEB-INF/error.jsp")).thenReturn(dispatcher);
        when(request.getParameter("name")).thenReturn("Bob");
        when(request.getParameter("pass")).thenReturn("11111");
        when(studentService.findByLogin(anyString())).thenReturn(Optional.of(studentFromDb));

        servlet.doGet(request, response);

        verify(dispatcher).forward(request, response);
        Student student = studentService.findByLogin("Bob").get();
        Assert.assertEquals(studentFromDb, student);
    }

    @Test
    public void registrationTest() throws IOException, ServletException {
        when(request.getRequestURI()).thenReturn("registration");
        when(request.getRequestDispatcher("/registration.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);
        verify(dispatcher).forward(request, response);
    }
}