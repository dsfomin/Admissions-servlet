package com.servlet.command;

import com.servlet.model.entity.Faculty;
import com.servlet.model.entity.Student;
import com.servlet.model.service.FacultyService;
import com.servlet.model.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Optional;

public class ApplyOnFacultyPageCommand implements Command {

    StudentService studentService;
    FacultyService facultyService;

    public ApplyOnFacultyPageCommand(StudentService studentService, FacultyService facultyService) {
        this.studentService = studentService;
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int facultyId = Integer.parseInt(request.getParameter("facultyid"));
        String user = (String) request.getSession().getAttribute("login");
        try {
            Optional<Student> student = studentService.findByLogin(user);
            if (!student.isPresent()) {
                request.setAttribute("exception", "student does not exist");
                return "/WEB-INF/error.jsp";
            }
            if (!student.get().isInSearch()) {
                request.setAttribute("exception", "You are already applied");
                return "/WEB-INF/error.jsp";
            }
            Optional<Faculty> faculty = facultyService.findById(facultyId);
            if (!faculty.isPresent()) {
                request.setAttribute("exception", "faculty does not exist");
                return "/WEB-INF/error.jsp";
            }
            if (studentService.checkIfApplied(student.get(), faculty.get())) {
                request.setAttribute("exception", "You are already applied on that faculty");
                return "/WEB-INF/error.jsp";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Optional<Faculty> faculty = facultyService.findById(facultyId);
            if (!faculty.isPresent()){
                request.setAttribute("exception", "faculty does not exist");
                return "/WEB-INF/error.jsp";
            }
            request.setAttribute("facultyid", faculty.get().getFacultyId());
            request.setAttribute("title", faculty.get().getTitle());
            request.setAttribute("totalPlaces", faculty.get().getTotalPlaces());
            request.setAttribute("budgetPlaces", faculty.get().getBudgetPlaces());
            request.setAttribute("contractPlaces", faculty.get().getContractPlaces());
            request.setAttribute("firstSubject", faculty.get().getFirstSubject());
            request.setAttribute("secondSubject", faculty.get().getSecondSubject());
            request.setAttribute("thirdSubject", faculty.get().getThirdSubject());
        } catch (SQLException e) {
            request.setAttribute("exception", "You are already applied on that faculty");
            return "/WEB-INF/error.jsp";
        }
        return "/applyOnFaculty.jsp";
    }
}
