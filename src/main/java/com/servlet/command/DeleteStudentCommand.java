package com.servlet.command;

import com.servlet.model.service.StudentService;

import javax.servlet.http.HttpServletRequest;

public class DeleteStudentCommand implements Command{
    private final StudentService studentService;

    public DeleteStudentCommand(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public String execute(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        studentService.deleteStudent(id);
        return "/WEB-INF/admin/studentList.jsp";
    }
}
