package io.plucen.services;

import io.plucen.entities.Student;
import io.plucen.repositories.StudentRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StudentService {

  private final StudentRepository studentRepository;

  public Student create(String name) {
    // TODO: validate name
    Student student = new Student(UUID.randomUUID(), name);
    studentRepository.create(student);
    return student;
  }
}
