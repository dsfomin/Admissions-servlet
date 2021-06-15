package com.servlet.command;

import com.servlet.model.entity.Student;
import com.servlet.model.entity.enums.Roles;
import com.servlet.model.service.StudentService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class EditStudentCommand implements Command {
    private final StudentService studentService;

    static final Logger LOGGER = Logger.getLogger(EditStudentCommand.class);

    public EditStudentCommand(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = new Student();
        student.setId(id);
        updateStudent(request, student);
        try {
            studentService.updateStudent(student);
        } catch (SQLException e) {
            request.setAttribute("exception","Student exist");
            return "/WEB-INF/error.jsp";
        }
        LOGGER.info("edit student successfully");
        return "/WEB-INF/admin/adminbasis.jsp";
    }

    static void updateStudent(HttpServletRequest request, Student student) {
        AddStudentCommand.addStudent(request, student);
    }
}
