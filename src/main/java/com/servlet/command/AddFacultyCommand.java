package com.servlet.command;

import com.servlet.model.entity.Faculty;
import com.servlet.model.service.FacultyService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class AddFacultyCommand implements Command{
    private final FacultyService facultyService;

    static final Logger LOGGER = Logger.getLogger(AddFacultyCommand.class);

    public AddFacultyCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request){
        Faculty faculty = new Faculty();
        updateFaculty(request, faculty);
        try {
            facultyService.createFaculty(faculty);
        } catch (SQLException e) {
            request.setAttribute("exception","Faculty exist");
            return "/WEB-INF/error.jsp";
        }
        LOGGER.info("Add faculty successfully");
        return "/index.jsp";
    }

    static void updateFaculty(HttpServletRequest request, Faculty faculty) {
        faculty.setTitle(request.getParameter("title"));
        faculty.setTotalPlaces(Integer.parseInt(request.getParameter("totalPlaces")));
        faculty.setBudgetPlaces(Integer.parseInt(request.getParameter("budgetPlaces")));
        faculty.setContractPlaces(Integer.parseInt(request.getParameter("contractPlaces")));
        faculty.setFirstSubject(request.getParameter("firstSubject"));
        faculty.setSecondSubject(request.getParameter("secondSubject"));
        faculty.setThirdSubject(request.getParameter("thirdSubject"));
    }
}
