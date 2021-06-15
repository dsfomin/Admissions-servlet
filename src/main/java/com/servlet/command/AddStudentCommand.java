package com.servlet.command;

import com.servlet.model.entity.Student;
import com.servlet.model.entity.enums.Roles;
import com.servlet.model.service.StudentService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class AddStudentCommand implements Command{
    private final StudentService studentService;

    static final Logger LOGGER = Logger.getLogger(AddStudentCommand.class);

    public AddStudentCommand(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Student student = new Student();
        addStudent(request, student);
        student.setInSearch(true);
        student.setOnBudget(false);
        try {
            studentService.createStudent(student);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
            request.setAttribute("exception", "Student exist or check credentials");
            return "/WEB-INF/error.jsp";
        }
        LOGGER.info("Add student successfully");
        return "/index.jsp";
    }

    static void addStudent(HttpServletRequest request, Student student) {
        student.setLogin(request.getParameter("login"));
        student.setEmail(request.getParameter("email"));
        student.setPassword(request.getParameter("password"));
        student.setCity(request.getParameter("city"));
        student.setDistrict(request.getParameter("district"));
        student.setSchool(request.getParameter("school"));
        student.setRoles(Roles.USER);
    }
}
