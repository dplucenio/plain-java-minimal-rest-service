package io.plucen;

import io.plucen.controllers.Controller;
import io.plucen.controllers.DashboardController;
import io.plucen.controllers.StudentsController;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;

import static io.plucen.App.HttpMethods.*;

class App {

  private static final Map<Pair<String, HttpMethods>, Controller> controllerss = Map
      .of(Pair.of("/", GET), new DashboardController(),
          Pair.of("/students", GET), new StudentsController()
      );

  public static void main(String[] args) throws LifecycleException {

    HttpServlet minimalServlet =
        new HttpServlet() {
          @Override
          protected void doGet(HttpServletRequest request, HttpServletResponse response)
              throws IOException {
            String url = request.getRequestURI().toLowerCase();
            controllerss.getOrDefault(Pair.of(url, GET), (req, res) -> {
            }).execute(request, response);
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

  @Data
  @RequiredArgsConstructor
  private static class Pair<T, U> {

    private final T first;
    private final U second;

    public static <T, U> Pair<T, U> of(T t, U u) {
      return new Pair<>(t, u);
    }
  }

  public enum HttpMethods {
    GET,
    POST
  }
}
