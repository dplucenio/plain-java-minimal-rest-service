package io.plucen.controllers;

import io.plucen.entities.Student;
import io.plucen.services.StudentService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StudentsController {

  private final StudentService studentService;

  public void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json; charset=UTF-8");
    response.getWriter().println("{\"message\": \"students\"}");
  }

  public void post(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Student duque = studentService.create("Duque");
    System.out.println(duque);
    response.setContentType("application/json; charset=UTF-8");
    response.getWriter().println("{\"message\": \"students post route\"}");

  }
}
