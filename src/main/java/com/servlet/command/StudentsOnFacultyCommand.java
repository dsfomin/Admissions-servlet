package com.servlet.command;

import com.servlet.model.entity.Faculty;
import com.servlet.model.entity.Student;
import com.servlet.model.service.FacultyService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class StudentsOnFacultyCommand implements Command {
    private final FacultyService facultyService;

    public StudentsOnFacultyCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int facultyId = Integer.parseInt(request.getParameter("facultyid"));
        try {
            Optional<Faculty> faculty = facultyService.findById(facultyId);
            if (!faculty.isPresent()){
                request.setAttribute("exception", "faculty does not exist");
                return "/WEB-INF/error.jsp";
            }
            List<Student> allStudents = facultyService.getAllStudents(faculty.get().getFacultyId());
            request.setAttribute("facultyid",faculty.get().getFacultyId());
            request.setAttribute("students",allStudents);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "/studentOnFacultyList.jsp";
    }
}
