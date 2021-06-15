package com.servlet.command;

import com.servlet.model.entity.Student;
import com.servlet.model.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Optional;

public class EditStudentPageCommand implements Command{
    private final StudentService studentService;

    public EditStudentPageCommand(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public String execute(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Optional<Student> student = studentService.findById(id);
            if (!student.isPresent()){
                request.setAttribute("exception","Student does not exist");
                return "/WEB-INF/error.jsp";
            }
            request.setAttribute("id",student.get().getId());
            request.setAttribute("login",student.get().getLogin());
            request.setAttribute("email",student.get().getEmail());
            request.setAttribute("password",student.get().getPassword());
            request.setAttribute("city",student.get().getCity());
            request.setAttribute("district",student.get().getDistrict());
            request.setAttribute("school",student.get().getSchool());
            return "/studentEdit.jsp";
        } catch (SQLException e) {
            request.setAttribute("exception","Student exist");
            return "/WEB-INF/error.jsp";
        }

    }
}
