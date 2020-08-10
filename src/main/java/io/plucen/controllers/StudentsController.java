package io.plucen.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.plucen.App;
import io.plucen.entities.Student;
import io.plucen.services.StudentService;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StudentsController {

  private final StudentService studentService;

  public void get(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ObjectMapper objectMapper = App.getObjectMapper();
    response.setContentType("application/json; charset=UTF-8");
    response.getWriter().println(objectMapper.writeValueAsString(studentService.index()));
  }

  public void post(HttpServletRequest request, HttpServletResponse response) throws IOException {
    StringBuilder json = new StringBuilder();
    BufferedReader reader = request.getReader();
    String line;
    while ((line = reader.readLine()) != null) {
      json.append(line).append("\n");
    }

    ObjectMapper objectMapper = App.getObjectMapper();
    Map<String, String> jsonMap = objectMapper.readValue(json.toString(), Map.class);
    Student student = studentService.create(jsonMap.get("name"));
    response.setContentType("application/json; charset=UTF-8");
    response.getWriter().println(objectMapper.writeValueAsString(student));
  }
}
