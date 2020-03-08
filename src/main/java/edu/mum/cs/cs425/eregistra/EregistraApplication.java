package edu.mum.cs.cs425.eregistra;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.support.Repositories;

import edu.mum.cs.cs425.eregistra.model.Student;
import edu.mum.cs.cs425.eregistra.repository.StudentRepository;

@SpringBootApplication
public class EregistraApplication implements CommandLineRunner{
	@Autowired
	private StudentRepository studentRepository;
	public static void main(String[] args) {
		SpringApplication.run(EregistraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Student student = studentRepository.findById(2L).get();
		//student.setInternational(false);
		//studentRepository.save(student);
	}

}
