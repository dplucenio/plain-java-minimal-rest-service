package io.plucen.controllers;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DashboardController implements Controller {
  @Override
  public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json; charset=UTF-8");
    response.getWriter().println("{\"message\": \"dashboard\"}");
  }
}
