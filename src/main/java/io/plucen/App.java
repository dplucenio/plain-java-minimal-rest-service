package io.plucen;

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

    HttpServlet minimalServlet = new HttpServlet() {
      @Override
      protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().println("{\"message\": \"hello world\"}");
      }
    };

    Tomcat tomcat = new Tomcat();
    tomcat.setPort(8080);
    tomcat.getConnector();

    Context context = tomcat.addContext("", null);
    Wrapper servlet = Tomcat.addServlet(context, "myFirstServlet", minimalServlet);
    servlet.setLoadOnStartup(1);
    servlet.addMapping("/*");

    tomcat.start();
  }
}