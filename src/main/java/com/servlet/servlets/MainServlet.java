package com.servlet.servlets;

import com.servlet.command.*;
import com.servlet.model.service.FacultyService;
import com.servlet.model.service.StudentService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainServlet extends HttpServlet {
    private final Map<String, Command> commands = new HashMap<>();

    static final Logger LOGGER = Logger.getLogger(MainServlet.class);

    public MainServlet() {super();}

    public void init() {
        commands.put("logout", new LogOut());
        commands.put("login", new Login());
        commands.put("registration", new Registration());
        commands.put("studentList", new StudentListCommand(new StudentService()));
        commands.put("facultyList", new FacultyListCommand(new FacultyService()));
        commands.put("addStudent", new AddStudentCommand(new StudentService()));
        commands.put("showEditStudentPage", new EditStudentPageCommand(new StudentService()));
        commands.put("editStudent", new EditStudentCommand(new StudentService()));
        commands.put("deleteStudent", new DeleteStudentCommand(new StudentService()));
        commands.put("addFacultyPage", new AddFacultyPageCommand());
        commands.put("addFaculty", new AddFacultyCommand(new FacultyService()));
        commands.put("showEditFacultyPage", new EditFacultyPageCommand(new FacultyService()));
        commands.put("editFaculty", new EditFacultyCommand(new FacultyService()));
        commands.put("deleteFaculty", new DeleteFacultyCommand(new FacultyService()));
        commands.put("applyOnFaculty", new ApplyOnFacultyPageCommand(new StudentService(), new FacultyService()));
        commands.put("applyStudentOnFaculty", new ApplyOnFacultyCommand(new StudentService(), new FacultyService()));
        commands.put("showStudentsOnFaculty", new StudentsOnFacultyCommand(new FacultyService()));
        commands.put("submitFaculty", new SubmitFacultyCommand(new StudentService()));
        commands.put("enableStudent", new EnableStudentCommand(new StudentService()));
        commands.put("disableStudent", new DisableStudentCommand(new StudentService()));
        commands.put("userProfile", new UserProfilePageCommand(new StudentService()));
        commands.put("home", new ReturnHomeCommand(new StudentService()));
        commands.put("finalize", new FinalizeResultCommand(new StudentService(), new FacultyService()));
        LOGGER.info("***************************INIT***************************");
    }

    public void init(StudentService studentService, FacultyService facultyService) {
        commands.put("logout", new LogOut());
        commands.put("login", new Login());
        commands.put("registration", new Registration());
        commands.put("studentList", new StudentListCommand(studentService));
        commands.put("facultyList", new FacultyListCommand(facultyService));
        commands.put("addStudent", new AddStudentCommand(studentService));
        commands.put("showEditStudentPage", new EditStudentPageCommand(studentService));
        commands.put("editStudent", new EditStudentCommand(studentService));
        commands.put("deleteStudent", new DeleteStudentCommand(studentService));
        commands.put("addFacultyPage", new AddFacultyPageCommand());
        commands.put("addFaculty", new AddFacultyCommand(facultyService));
        commands.put("showEditFacultyPage", new EditFacultyPageCommand(facultyService));
        commands.put("editFaculty", new EditFacultyCommand(facultyService));
        commands.put("deleteFaculty", new DeleteFacultyCommand(facultyService));
        commands.put("applyOnFaculty", new ApplyOnFacultyPageCommand(studentService, facultyService));
        commands.put("applyStudentOnFaculty", new ApplyOnFacultyCommand(studentService, facultyService));
        commands.put("showStudentsOnFaculty", new StudentsOnFacultyCommand(facultyService));
        commands.put("submitFaculty", new SubmitFacultyCommand(studentService));
        commands.put("enableStudent", new EnableStudentCommand(studentService));
        commands.put("disableStudent", new DisableStudentCommand(studentService));
        commands.put("userProfile", new UserProfilePageCommand(studentService));
        commands.put("home", new ReturnHomeCommand(studentService));
        commands.put("finalize", new FinalizeResultCommand(studentService, facultyService));
        LOGGER.info("***************************INIT***************************");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        LOGGER.info("***************************GET***************************");
        processRequest(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        LOGGER.info("***************************POST***************************");
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        LOGGER.info(path);
        path = path.replaceAll(".*/app/", "");
        LOGGER.info(path);
        Command command = commands.getOrDefault(path, (r) -> "/index.jsp");
        String page = command.execute(request);
        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", "/"));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}