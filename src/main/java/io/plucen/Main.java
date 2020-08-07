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
    Tomcat tomcat = new Tomcat();
    tomcat.setPort(8080);
    tomcat.getConnector();

    Context context = tomcat.addContext("", null);
    Wrapper servlet = Tomcat.addServlet(context, "myFirstServlet", new MinimalServlet());
    servlet.setLoadOnStartup(1);
    servlet.addMapping("/*");

    tomcat.start();
  }

  private static class MinimalServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException {
      res.setContentType("application/json; charset=UTF-8");
      res.getWriter().println("{\"message\": \"hello world\"}");
    }
  }
}