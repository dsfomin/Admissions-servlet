package com.servlet.command;

import com.servlet.model.entity.Faculty;
import com.servlet.model.entity.Student;
import com.servlet.model.service.FacultyService;
import com.servlet.model.service.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ApplyOnFacultyCommand implements Command{

    StudentService studentService;
    FacultyService facultyService;
    public ApplyOnFacultyCommand(StudentService studentService, FacultyService facultyService){
        this.studentService = studentService;
        this.facultyService = facultyService;
    }
    @Override
    public String execute(HttpServletRequest request) {
        int facultyId = Integer.parseInt(request.getParameter("facultyid"));
        String user = (String) request.getSession().getAttribute("login");
        Optional<Student> student;
        try {
            Optional<Faculty> faculty = facultyService.findById(facultyId);
            if (!faculty.isPresent()){
                request.setAttribute("exception", "faculty does not exist");
                return "/WEB-INF/error.jsp";
            }
            student = studentService.findByLogin(user);
            if (!student.isPresent()){
                request.setAttribute("exception", "student does not exist");
                return "/WEB-INF/error.jsp";
            }
            student.get().getFaculties().add(faculty.get());
            student.get().setFirstGrade(Integer.parseInt(request.getParameter("firstGrade")));
            student.get().setSecondGrade(Integer.parseInt(request.getParameter("secondGrade")));
            student.get().setThirdGrade(Integer.parseInt(request.getParameter("thirdGrade")));
            studentService.updateStudent(student.get());
            studentService.addFaculty(student.get(), faculty.get());
        } catch (SQLException e) {
            request.setAttribute("exception", "Grades should be in range 0 and 200");
            return "/WEB-INF/error.jsp";
        }
        if (user.equals("admin")) {
            return "/WEB-INF/admin/adminbasis.jsp";

        }
        List<Faculty> allFaculties = studentService.getAllFaculties(student.get().getId());
        request.setAttribute("faculties", allFaculties);
        return "/WEB-INF/user/userbasis.jsp";
    }
}
