package edu.mum.cs.cs425.eregistra.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.mum.cs.cs425.eregistra.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long>{
	@Query(value = "SELECT * from students where cgpa = :cgpa", nativeQuery =  true)
	public List<Student> findStudentsByCgpa(@Param("cgpa") double cgpa);
	@Query(value = "SELECT * from students where student_number LIKE %:search% OR "
			+ "first_name LIKE %:search% OR middle_name LIKE %:search% OR "
			+ "last_name LIKE %:search%", nativeQuery = true)
	public List<Student> findStudentsByStringFields(@Param("search") String search);
	@Query(value = "Select * FROM students WHERE enrollment_date = :date", nativeQuery = true)
	public List<Student> findStudentsByEnrollmentDate(@Param("date") LocalDate date);
}
