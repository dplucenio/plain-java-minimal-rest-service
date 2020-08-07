package io.plucen;

import io.plucen.controllers.DashboardController;
import io.plucen.controllers.StudentsController;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;

class App {

  public static void main(String[] args) throws LifecycleException {

    HttpServlet minimalServlet =
        new HttpServlet() {
          @Override
          protected void doGet(HttpServletRequest request, HttpServletResponse response)
              throws IOException {

            if (request.getRequestURI().equalsIgnoreCase("/")) {
              new DashboardController().execute(request, response);
            } else if (request.getRequestURI().equalsIgnoreCase("/students")) {
              new StudentsController().execute(request, response);
            }
          }
        };

    Tomcat tomcat = new Tomcat();
    tomcat.setPort(8081);
    tomcat.getConnector();
    Context context = tomcat.addContext("", null);
    Wrapper servlet = Tomcat.addServlet(context, "minimalServlet", minimalServlet);
    servlet.setLoadOnStartup(1);
    servlet.addMapping("/*");
    tomcat.start();
  }
}
