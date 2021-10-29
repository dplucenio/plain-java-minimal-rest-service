package io.plucen;

import static io.plucen.utils.HttpMethods.GET;
import static io.plucen.utils.HttpMethods.POST;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.plucen.controllers.Controller;
import io.plucen.utils.HttpMethods;
import io.plucen.utils.Pair;
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

  @Getter() private static final ObjectMapper objectMapper = new ObjectMapper();

  private static final Map<Pair<String, HttpMethods>, Controller> controllers =
      Map.of(
          Pair.of("/", GET), Configuration.getDashBoardController()::get,
          Pair.of("/students", GET), Configuration.getStudentsController()::get,
          Pair.of("/students", POST), Configuration.getStudentsController()::post);

  public static void main(String[] args) throws LifecycleException {
    Tomcat tomcat = new Tomcat();
    tomcat.setPort(8081);
    tomcat.getConnector();
    Context context = tomcat.addContext("", null);

    Wrapper servlet = Tomcat.addServlet(context, "minimalServlet", new MyHttpServlet());
    servlet.setLoadOnStartup(1);
    servlet.addMapping("/*");

    tomcat.start();
  }

  private static class MyHttpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
      String url = request.getRequestURI().toLowerCase();
      controllers.getOrDefault(Pair.of(url, GET), (req, res) -> {}).execute(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
      String url = request.getRequestURI().toLowerCase();
      controllers.getOrDefault(Pair.of(url, POST), (req, res) -> {}).execute(request, response);
    }
  }
}
