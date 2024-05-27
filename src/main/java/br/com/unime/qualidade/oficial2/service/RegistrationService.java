package br.com.unime.qualidade.oficial2.service;

import br.com.unime.qualidade.oficial2.entity.Registration;
import br.com.unime.qualidade.oficial2.entity.Student;
import br.com.unime.qualidade.oficial2.entity.Tuition;
import br.com.unime.qualidade.oficial2.enums.Status;
import br.com.unime.qualidade.oficial2.exception.RegistrationDeletedException;
import br.com.unime.qualidade.oficial2.exception.RegistrationInvalidException;
import br.com.unime.qualidade.oficial2.exception.RegistrationNotFoundException;
import br.com.unime.qualidade.oficial2.exception.RegistrationPaidDelayedException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegistrationService {

    public List<Registration> getAll() {
        Registration registration1 = Registration
                .builder()
                .id("4653421")
                .courseName("Sistemas de Informação")
                .tuition(Tuition
                        .builder()
                        .amount(1390.00)
                        .formattedAmount("R$ 1390,00")
                        .dueDate(LocalDate.of(2024, 6, 10))
                        .status(Status.EM_DIA)
                        .build())
                .student(Student
                        .builder()
                        .firstName("Mariana")
                        .lastName("Santana de Jesus")
                        .birthDate(LocalDate.of(2004, 5, 12))
                        .cpf("89098765422")
                        .build())
                .build();

        Registration registration2 = Registration
                .builder()
                .id("7890123")
                .courseName("Engenharia de Software")
                .tuition(Tuition
                        .builder()
                        .amount(1500.00)
                        .formattedAmount("R$ 1500,00")
                        .dueDate(LocalDate.of(2024, 6, 10))
                        .status(Status.BOLSISTA_100)
                        .build())
                .student(Student
                        .builder()
                        .firstName("Carlos")
                        .lastName("Oliveira da Silva")
                        .birthDate(LocalDate.of(1995, 3, 15))
                        .cpf("98765432100")
                        .build())
                .build();

        Registration registration3 = Registration
                .builder()
                .id("1122334")
                .courseName("Medicina Veterinária")
                .tuition(Tuition
                        .builder()
                        .amount(6600.00)
                        .formattedAmount("R$ 6600,00")
                        .dueDate(LocalDate.of(2024, 6, 10))
                        .status(Status.CONTRATO_QUITADO)
                        .build())
                .student(Student
                        .builder()
                        .firstName("Ana")
                        .lastName("Pereira Lima")
                        .birthDate(LocalDate.of(1990, 4, 25))
                        .cpf("12345678900")
                        .build())
                .build();

        Registration registration4 = Registration
                .builder()
                .id("5566778")
                .courseName("Engenharia Elétrica")
                .tuition(Tuition
                        .builder()
                        .amount(0.00)
                        .formattedAmount("R$ 0,00")
                        .dueDate(null)
                        .status(Status.EM_DIA)
                        .build())
                .student(Student
                        .builder()
                        .firstName("Pedro")
                        .lastName("Gomes de Souza")
                        .birthDate(LocalDate.of(1985, 5, 20))
                        .cpf("32165498700")
                        .build())
                .build();

        Registration registration5 = Registration
                .builder()
                .id("4653499")
                .courseName("Ciências Contábeis")
                .tuition(Tuition
                        .builder()
                        .amount(990.00)
                        .formattedAmount("R$ 990,00")
                        .dueDate(LocalDate.of(2024, 6, 10))
                        .status(Status.EM_DIA)
                        .build())
                .student(Student
                        .builder()
                        .firstName("Jackson")
                        .lastName("Pereira de Jesus")
                        .birthDate(LocalDate.of(2024, 6, 10))
                        .cpf("89098765422")
                        .build())
                .build();

        Registration registration6 = Registration
                .builder()
                .id("1113499")
                .courseName("")
                .tuition(Tuition
                        .builder()
                        .amount(0.00)
                        .formattedAmount("R$ 0,00")
                        .dueDate(null)
                        .status(Status.EM_DIA)
                        .build())
                .student(Student
                        .builder()
                        .firstName("")
                        .lastName("")
                        .birthDate(null)
                        .cpf("76598765412")
                        .build())
                .build();

        return List.of(registration1, registration2, registration3, registration4, registration5, registration6);
    }

    public boolean registrationHasDelayedPayment(Registration registration) {
        return registration.getTuition().getStatus().equals(Status.PAGAMENTO_EM_ATRASO);
    }

    public boolean registrationHasScholarship(Registration registration) {
        return registration.getTuition().getStatus().equals(Status.BOLSISTA_100);
    }

    public boolean registrationHasPaidMonthlyFees(Registration registration) {
        return registration.getTuition().getStatus().equals(Status.CONTRATO_QUITADO);
    }

    public boolean registrationIsDeleted(Registration registration) {
        return registration.getTuition().getStatus().equals(Status.MATRICULA_EXCLUIDA);
    }

    public boolean registrationNumberIsValid(String matricula) {
        String regex = "^\\d{7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(matricula);

        return matcher.matches();
    }

    public Registration getById(String id) throws RegistrationDeletedException, RegistrationInvalidException, RegistrationNotFoundException, RegistrationPaidDelayedException {
        // Se a matrícula é inválida...
        if (!registrationNumberIsValid(id)) {
            throw new RegistrationInvalidException();
        }

        Optional<Registration> registrationOptional = getAll().stream().filter(
                (Registration registration) -> registration.getId().equals(id)
        ).findFirst();

        if(registrationOptional.isEmpty()) {
            throw new RegistrationNotFoundException();
        }

        Registration registration = registrationOptional.get();

        // Se a matrícula está com pagamento atrasado...
        if (registrationHasDelayedPayment(registration)) {
            throw new RegistrationPaidDelayedException();
        }

        // Se a matrícula foi excluída...
        if (registrationIsDeleted(registration)) {
            throw new RegistrationDeletedException();
        }

        // Se a matrícula possui bolsa de estudos 100%...
        if (registrationHasScholarship(registration)) {
            registration.getTuition().setAmount(0.00);
            registration.getTuition().setFormattedAmount("R$ 0.00");
            registration.getTuition().setDueDate(null);
        }

        // Se a matrícula tem pagamento concluído
        if (registrationHasPaidMonthlyFees(registration)) {
            registration.getTuition().setDueDate(null);
        }

        return registration;
    }

}
