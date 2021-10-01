package com.mwguerra.jpm1restapi;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/students")
@Api(tags = "Students")
public class StudentController {
  private final List<Student> students = new ArrayList<>();
  private Integer id = 0;

  @GetMapping
  public ResponseEntity<List<Student>> index(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age) {
    Stream<Student> filtered = students.stream();

    if (name != null) {
      filtered = filtered.filter(student -> student.getName().contains(name));
    }

    if (age != null) {
      filtered = filtered.filter(student -> student.getAge().equals(age));
    }

    return new ResponseEntity<>(filtered.collect(Collectors.toList()), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Student> create(@RequestBody Student student) {
    id++;
    Student newStudent = new Student(id, student.getName(), student.getAge());
    students.add(newStudent);

    return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
  }

  @PostMapping("/{id}")
  public ResponseEntity<Student> show(@PathVariable("id") Integer id) {
    Student student = students.stream()
      .filter(s -> s.getId().equals(id))
      .findFirst()
      .orElse(null);

    return new ResponseEntity<>(student, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody Student student) {
    students.stream()
      .filter(s -> s.getId().equals(id))
      .forEach(s -> {
        s.setName(student.getName());
        s.setAge(student.getAge());
      });
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable("id") Integer id) {
    students.removeIf(student -> student.getId().equals(id));
    return new ResponseEntity(HttpStatus.NO_CONTENT);
  }
}
