package io.plucen.repositories;

import io.plucen.entities.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MemoryStudentRepository implements StudentRepository {

  private final List<Student> students = new ArrayList<>();

  @Override
  public List<Student> index() {
    return List.copyOf(students);
  }

  @Override
  public Optional<Student> findById(UUID id) {
    return students.stream().filter(student -> student.getId() == id).findAny();
  }

  @Override
  public void create(Student student) {
    students.add(student);
  }
}
