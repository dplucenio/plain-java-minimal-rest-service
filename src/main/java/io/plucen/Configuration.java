package io.plucen;

import io.plucen.controllers.DashboardController;
import io.plucen.controllers.StudentsController;
import io.plucen.repositories.MemoryStudentRepository;
import io.plucen.repositories.StudentRepository;
import io.plucen.services.StudentService;
import lombok.Getter;

public class Configuration {

  @Getter
  private static final StudentRepository studentRepository = new MemoryStudentRepository();
  @Getter
  private static final StudentService studentService = new StudentService(studentRepository);
  @Getter
  private static final StudentsController studentsController = new StudentsController(
      studentService);
  @Getter
  private static final DashboardController dashBoardController = new DashboardController();
}
