package com.servlet.command;

import com.servlet.model.entity.Student;
import com.servlet.model.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Optional;

public class DisableStudentCommand implements Command {
    private final StudentService studentService;

    public DisableStudentCommand(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String user = (String) request.getSession().getAttribute("login");
        int studentId = Integer.parseInt(request.getParameter("id"));
        try {
            Optional<Student> student = studentService.findById(studentId);
            student.get().setInSearch(false);
            studentService.updateStudent(student.get());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (user == null) {
            request.setAttribute("exception", "User is not logged");
            return "/WEB-INF/error.jsp";
        }
        if (user.equals("admin"))
            return "/WEB-INF/admin/adminbasis.jsp";
        return "/WEB-INF/user/userbasis.jsp";
    }
}
