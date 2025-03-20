package ma.enset.patientsmvc;

import ma.enset.patientsmvc.entities.Patient;
import ma.enset.patientsmvc.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientsMvcApplication.class, args);
	}
	@Bean
	CommandLineRunner start(PatientRepository patientRepository) {
		return args -> {
			patientRepository.save(new Patient(null,"ikrame",new Date(),false,92));
			patientRepository.save(new Patient(null,"imane",new Date(),true,22));
			patientRepository.save(new Patient(null,"safaa",new Date(),true,33));
			patientRepository.save(new Patient(null,"zak",new Date(),false,72));

			patientRepository.findAll()
					.forEach(p-> {
						System.out.println(p.getNom());


					});

		};
	}

}
