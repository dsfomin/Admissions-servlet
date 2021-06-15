package com.servlet.command;

import com.servlet.model.entity.Faculty;
import com.servlet.model.entity.Student;
import com.servlet.model.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ReturnHomeCommand implements Command {
    StudentService studentService;
    public ReturnHomeCommand(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String user = (String) request.getSession().getAttribute("login");
        if (user == null){
            return "/index.jsp";
        }
        if (user.equals("admin")) {
            request.setAttribute("id", 1);
            return "/WEB-INF/admin/adminbasis.jsp";
        }
        Optional<Student> student;
        try {
            student = studentService.findByLogin(user);
        } catch (SQLException e) {
            request.setAttribute("exception", "student does not exist");
            return "/WEB-INF/error.jsp";
        }
        int studentId = student.get().getId();
        request.setAttribute("faculties", studentService.getAllFaculties(studentId));
        request.setAttribute("id", studentId);
        return "/WEB-INF/user/userbasis.jsp";
    }
}
