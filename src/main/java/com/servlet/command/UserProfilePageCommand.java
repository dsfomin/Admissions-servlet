package com.servlet.command;

import com.servlet.model.entity.Faculty;
import com.servlet.model.entity.Student;
import com.servlet.model.service.StudentService;
import com.servlet.servlets.MainServlet;
import com.sun.media.jfxmedia.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserProfilePageCommand implements Command {
    private final StudentService studentService;
    static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(UserProfilePageCommand.class);

    public UserProfilePageCommand(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int studentId = Integer.parseInt(request.getParameter("id"));
        try {
            Optional<Student> student = studentService.findById(studentId);
            if (!student.isPresent()){
                request.setAttribute("exception", "student does not exist");
                return "/WEB-INF/error.jsp";
            }
            request.setAttribute("id", student.get().getId());
            request.setAttribute("login", student.get().getLogin());
            request.setAttribute("email", student.get().getEmail());
            request.setAttribute("firstGrade", student.get().getFirstGrade());
            request.setAttribute("secondGrade", student.get().getSecondGrade());
            request.setAttribute("thirdGrade", student.get().getThirdGrade());
            request.setAttribute("myLogin", request.getSession().getAttribute("login"));
            request.setAttribute("myId",
                    studentService.findByLogin((String) request.getSession().getAttribute("login")).get().getId());
        } catch (SQLException e) {
            request.setAttribute("exception", "student does not exist");
            return "/WEB-INF/error.jsp";
        }
        if (request.getSession().getAttribute("login").equals("admin")) {
            request.setAttribute("isAdmin", true);
        }
        request.setAttribute("faculties", studentService.getAllFaculties(studentId));
        return "/userProfile.jsp";
    }
}
