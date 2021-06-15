package com.servlet.command;

import javax.servlet.http.HttpServletRequest;

public class AddFacultyPageCommand implements Command{

    @Override
    public String execute(HttpServletRequest request){
        return "/addFaculty.jsp";
    }
}
