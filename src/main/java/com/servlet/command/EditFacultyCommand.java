package com.servlet.command;

import com.servlet.model.entity.Faculty;
import com.servlet.model.service.FacultyService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class EditFacultyCommand implements Command{
    private final FacultyService facultyService;

    static final Logger LOGGER = Logger.getLogger(EditFacultyCommand.class);

    public EditFacultyCommand(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    public String execute(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        Faculty faculty = new Faculty();
        faculty.setFacultyId(id);
        AddFacultyCommand.updateFaculty(request, faculty);
        try {
            facultyService.updateFaculty(faculty);
        }
        catch (SQLException e){
            request.setAttribute("exception","Faculty exists");
            return "/WEB-INF/error.jsp";
        }
        LOGGER.info("Edit faculty successfully");
        return "/WEB-INF/admin/adminbasis.jsp";
    }
}
