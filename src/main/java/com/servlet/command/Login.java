package com.servlet.command;

import com.servlet.model.entity.Student;
import com.servlet.model.entity.enums.Roles;
import com.servlet.model.service.StudentService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Optional;

public class Login implements Command {
    StudentService studentService;

    static final Logger LOGGER = Logger.getLogger(Login.class);

    public Login() {
        studentService = new StudentService();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        LOGGER.info(name + " " + pass);
        if (name == null || name.equals("") || pass == null || pass.equals("")  ){
            return "/login.jsp";
        }
        StudentService service = new StudentService();
        Optional<Student> student;
        try {
            student = service.findByLogin(name);
        } catch (SQLException e) {
            request.setAttribute("exception", "student does not exist");
            return "/WEB-INF/error.jsp";
        }
        if (!student.isPresent()){
            request.setAttribute("exception", "student does not exist");
            return "/WEB-INF/error.jsp";
        }
        if (CommandUtility.checkUserIsLogged(request, name)) {
            request.setAttribute("exception", "User is already logged");
            return "/WEB-INF/error.jsp";
        }
        request.getSession().setAttribute("login", student.get().getLogin());
        if (name.equals("admin") && pass.equals(student.get().getPassword())) {
            CommandUtility.setUserRole(request, Roles.ADMIN, name);
            request.setAttribute("id", 1);
            return "/WEB-INF/admin/adminbasis.jsp";
        } else if (pass.equals(student.get().getPassword())) {
            CommandUtility.setUserRole(request, Roles.USER, name);
            request.setAttribute("login", name);
            int studentId = student.get().getId();
            request.setAttribute("faculties", studentService.getAllFaculties(studentId));
            request.setAttribute("id", studentId);
            return "/WEB-INF/user/userbasis.jsp";
        } else {
            request.setAttribute("error", "Incorrect login or password");
            return "/login.jsp";
        }
    }
}
