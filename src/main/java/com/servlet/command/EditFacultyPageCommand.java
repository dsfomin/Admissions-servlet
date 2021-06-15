package com.servlet.command;

import com.servlet.model.entity.Faculty;
import com.servlet.model.service.FacultyService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Optional;

public class EditFacultyPageCommand implements Command{
    private final FacultyService facultyService;

    public EditFacultyPageCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("facultyid"));

        try {
            Optional<Faculty> faculty = facultyService.findById(id);
            if (!faculty.isPresent()){
                request.setAttribute("exception","Faculty does not exist");
                return "/WEB-INF/error.jsp";
            }
            request.setAttribute("id",faculty.get().getFacultyId());
            request.setAttribute("title",faculty.get().getTitle());
            request.setAttribute("totalPlaces",faculty.get().getTotalPlaces());
            request.setAttribute("budgetPlaces",faculty.get().getBudgetPlaces());
            request.setAttribute("contractPlaces",faculty.get().getContractPlaces());
            request.setAttribute("firstSubject",faculty.get().getFirstSubject());
            request.setAttribute("secondSubject",faculty.get().getSecondSubject());
            request.setAttribute("thirdSubject",faculty.get().getThirdSubject());
            return "/facultyEdit.jsp";
        } catch (SQLException e) {
            request.setAttribute("exception","Faculty exist");
            return "/WEB-INF/error.jsp";
        }

    }
}
