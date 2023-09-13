package br.com.fiap.doctorchat;

import br.com.fiap.doctorchat.models.Medico;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@SpringBootApplication
public class DoctorchatApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorchatApplication.class, args);
	}

	@Bean
	ApplicationListener<ApplicationReadyEvent> basicsApplicationListener(MedicoRepository repository) {
		return event -> repository.saveAll(List.of(
				Medico.builder().nome("João Ruiz").CMR("123").especialidade("Clinica Geral").ativo(true).build(),
				Medico.builder().nome("Mateus Marchetti").CMR("456").especialidade("Médico Endocrinologista").ativo(false).build(),
				Medico.builder().nome("Kaio Gomes").CMR("789").especialidade("Médico Gastro").ativo(false).build(),
				Medico.builder().nome("Jean Soares").CMR("098").especialidade("Médico Oftalmologista").ativo(false).build(),
				Medico.builder().nome("Amanda Mendes").CMR("735").especialidade("Médico Gonoaudóloga").ativo(false).build()
		));
	}

}

interface MedicoRepository extends JpaRepository<Medico, Long> {

}