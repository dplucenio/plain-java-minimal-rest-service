package io.plucen;

import static io.plucen.HttpMethods.GET;
import static io.plucen.HttpMethods.POST;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.plucen.controllers.Controller;
import io.plucen.controllers.DashboardController;
import io.plucen.controllers.StudentsController;
import io.plucen.repositories.MemoryStudentRepository;
import io.plucen.repositories.StudentRepository;
import io.plucen.services.StudentService;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;

public class App {

  @Getter()
  private static final ObjectMapper objectMapper = new ObjectMapper();

  private static final StudentRepository studentRepository = new MemoryStudentRepository();
  private static final StudentService studentService = new StudentService(studentRepository);
  private static final StudentsController studentsController = new StudentsController(
      studentService);
  private static final DashboardController dashBoardController = new DashboardController();

  private static final Map<Pair<String, HttpMethods>, Controller> controllers = Map
      .of(Pair.of("/", GET), dashBoardController::get,
          Pair.of("/students", GET), studentsController::get,
          Pair.of("/students", POST), studentsController::post
      );

  public static void main(String[] args) throws LifecycleException {
    Tomcat tomcat = new Tomcat();
    tomcat.setPort(8081);
    tomcat.getConnector();
    Context context = tomcat.addContext("", null);

    Wrapper servlet = Tomcat.addServlet(context, "minimalServlet", new HttpServlet() {
      @Override
      protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws IOException {
        String url = request.getRequestURI().toLowerCase();
        controllers.getOrDefault(Pair.of(url, GET), (req, res) -> {
        }).execute(request, response);
      }

      @Override
      protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws IOException {
        String url = request.getRequestURI().toLowerCase();
        controllers.getOrDefault(Pair.of(url, POST), (req, res) -> {
        }).execute(request, response);
      }
    });
    servlet.setLoadOnStartup(1);
    servlet.addMapping("/*");

    tomcat.start();
  }
}
