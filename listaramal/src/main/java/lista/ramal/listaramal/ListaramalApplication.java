package lista.ramal.listaramal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lista.ramal.listaramal.model.Ramal;
import lista.ramal.listaramal.repository.RamalRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;


@SpringBootApplication
public class ListaramalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ListaramalApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(RamalRepository ramalRepository) {
		return args -> {
			System.out.println("Executado");
			System.out.println(ramalRepository);
			Ramal a1 = new Ramal();
			a1.setNumRamal("1120");
			a1.setNomeCompleto("ti");
			a1.setEmailDepartamento("ti@gmail.com");
			a1.setNomeResponsavel("Luis Felipe");
	
			try {
				ramalRepository.save(a1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			a1.setId(1L);
			a1 = ramalRepository.save(a1);
			System.out.println("Ramal:" + a1);
			a1.setEmailDepartamento("Teste@teste.com.br");
			a1 = ramalRepository.save(a1);
			System.out.println("Ramal: " + a1);

			a1 = new Ramal();
			a1.setId(2L);
			a1.setNumRamal("0000");
			a1.setNomeCompleto("Financeiro");
			a1.setEmailDepartamento("Financeior@gamil.com.br");
			a1.setNomeResponsavel("Teste 2");
			boolean email_duplicado = false;
			Integer totalUsoEmail = ramalRepository.countUtilizacaoEmailDepartamento(a1.getEmailDepartamento());
			if (totalUsoEmail > 0) {
				System.out.println("O e-mail:" + a1.getEmailDepartamento() + " não pode ser utilizado!!");
				System.out.println("Total de utilização: " + totalUsoEmail);
			} else {
				ramalRepository.save(a1);
			}

			Optional<Ramal> alunoByEMail = ramalRepository.findRamalByemailDepartamento(a1.getEmailDepartamento());
			if (alunoByEMail.isPresent()) {
				Ramal ramal = alunoByEMail.get();
				System.out.println("Não é possível utilizar o emaili:" + a1.getEmailDepartamento());
				System.out.println("Porque ele pertence ao aluno:" + ramal.getNomeCompleto());
			} else {
				a1 = ramalRepository.save(a1);
			}
			try {
				a1 = ramalRepository.save(a1);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				email_duplicado = e.getMessage().contains(Ramal.UK_AMIGO_MAIL);
			}
			if (email_duplicado) {
				System.out.println("Email duplicado:" + a1.getEmailDepartamento());
			}

			System.out.println("Aluno2: " + a1);
			imprimirLista(ramalRepository);
			ramalRepository.delete(a1);
			imprimirLista(ramalRepository);

		};
	}

	private static void imprimirLista(RamalRepository ramalRepository) {
		List<Ramal> lista = ramalRepository.findAll();
		lista.forEach(item -> {
			System.out.println("Aluno: " + item);
		});
	}

}
