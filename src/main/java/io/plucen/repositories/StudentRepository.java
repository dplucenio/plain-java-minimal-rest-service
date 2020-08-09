package io.plucen.repositories;

import io.plucen.entities.Student;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentRepository {

  List<Student> index();

  Optional<Student> findById(UUID id);

  void create(Student student);
}
