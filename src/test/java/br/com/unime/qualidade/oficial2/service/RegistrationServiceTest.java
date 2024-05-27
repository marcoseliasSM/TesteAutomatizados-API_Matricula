package br.com.unime.qualidade.oficial2.service;

import br.com.unime.qualidade.oficial2.entity.Registration;
import br.com.unime.qualidade.oficial2.entity.Tuition;
import br.com.unime.qualidade.oficial2.enums.Status;
import br.com.unime.qualidade.oficial2.exception.RegistrationDeletedException;
import br.com.unime.qualidade.oficial2.exception.RegistrationInvalidException;
import br.com.unime.qualidade.oficial2.exception.RegistrationNotFoundException;
import br.com.unime.qualidade.oficial2.exception.RegistrationPaidDelayedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class RegistrationServiceTest {
    @InjectMocks
    private RegistrationService registrationService;

    @Mock
    private Registration registration;

    @Mock
    private Tuition tuition;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        registrationService = Mockito.spy(new RegistrationService());
    }

    @Test
    public void testRegistrationHasDelayedPayment_WhenStatusIsDelayed() {
        // Arrange
        when(registration.getTuition()).thenReturn(tuition);
        when(tuition.getStatus()).thenReturn(Status.PAGAMENTO_EM_ATRASO);

        // Act
        boolean actualResult = registrationService.registrationHasDelayedPayment(registration);

        // Assert
        assertTrue(actualResult);
    }

    @Test
    public void testRegistrationHasDelayedPayment_WhenStatusIsNotDelayed() {
        // Arrange
        when(registration.getTuition()).thenReturn(tuition);
        when(tuition.getStatus()).thenReturn(Status.EM_DIA);

        // Act
        boolean actualResult = registrationService.registrationHasDelayedPayment(registration);

        // Assert
        assertFalse(actualResult);
    }

    @Test
    public void testRegistrationHasScholarship_WhenStatusIsBOLSISTA_100() {
        // Arrange
        when(registration.getTuition()).thenReturn(tuition);
        when(tuition.getStatus()).thenReturn(Status.BOLSISTA_100);

        // Act
        boolean actualResult = registrationService.registrationHasScholarship(registration);

        // Assert
        assertTrue(actualResult);
    }

    @Test
    public void testRegistrationHasScholarship_WhenStatusIsNotBOLSISTA_100() {
        // Arrange
        when(registration.getTuition()).thenReturn(tuition);
        when(tuition.getStatus()).thenReturn(Status.EM_DIA);

        // Act
        boolean actualResult = registrationService.registrationHasScholarship(registration);

        // Assert
        assertFalse(actualResult);
    }

    @Test
    public void testRegistrationHasPaidMonthlyFees_WhenHasPaidMonthlyFees() {
        // Arrange
        when(registration.getTuition()).thenReturn(tuition);
        when(tuition.getStatus()).thenReturn(Status.CONTRATO_QUITADO);

        // Act
        boolean actualResult = registrationService.registrationHasPaidMonthlyFees(registration);

        // Assert
        assertTrue(actualResult);
    }

    @Test
    public void testRegistrationHasPaidMonthlyFees_WhenHasNoPaidMonthlyFees() {
        // Arrange
        when(registration.getTuition()).thenReturn(tuition);
        when(tuition.getStatus()).thenReturn(Status.EM_DIA);

        // Act
        boolean actualResult = registrationService.registrationHasPaidMonthlyFees(registration);

        // Assert
        assertFalse(actualResult);
    }

    @Test
    public void testRegistrationIsDeleted_WhenRegistrationIsDeleted() {
        // Arrange
        when(registration.getTuition()).thenReturn(tuition);
        when(tuition.getStatus()).thenReturn(Status.MATRICULA_EXCLUIDA);

        // Act
        boolean actualResult = registrationService.registrationIsDeleted(registration);

        // Assert
        assertTrue(actualResult);
    }

    @Test
    public void testRegistrationIsDeleted_WhenRegistrationIsActive() {
        // Arrange
        when(registration.getTuition()).thenReturn(tuition);
        when(tuition.getStatus()).thenReturn(Status.EM_DIA);

        // Act
        boolean actualResult = registrationService.registrationIsDeleted(registration);

        // Assert
        assertFalse(actualResult);
    }

    @Test
    public void testRegistrationNumberIsValid_WhenRegistrationNumberIsValid() {
        // Arrange
        String expectedRegistrationNumber = "1234567";
        when(registration.getId()).thenReturn(expectedRegistrationNumber);

        // Act
        boolean actualResult = registrationService.registrationNumberIsValid(registration.getId());

        // Assert
        assertTrue(actualResult);
    }

    @Test
    public void testRegistrationNumberIsValid_WhenRegistrationNumberIsInValid() {
        // Arrange
        String expectedRegistrationNumber = "99";
        when(registration.getId()).thenReturn(expectedRegistrationNumber);

        // Act
        boolean actualResult = registrationService.registrationNumberIsValid(registration.getId());

        // Assert
        assertFalse(actualResult);
    }

    @Test
    public void testGetAll_WhenHasRegisters() {
        // Arrange
        int registrationListSizeExpected = 1;
        List<Registration> registrationList = List.of(registration);
        doReturn(registrationList).when(registrationService).getAll();

        // Act
        List<Registration> registrationListActual = registrationService.getAll();

        // Assert
        assertEquals(registrationListSizeExpected, registrationListActual.size());
    }

    @Test
    public void testGetAll_WhenHasNoRegisters() {
        // Arrange
        int registrationListSizeExpected = 0;
        doReturn(new ArrayList<>()).when(registrationService).getAll();

        // Act
        List<Registration> registrationListActual = registrationService.getAll();

        // Assert
        assertEquals(registrationListSizeExpected, registrationListActual.size());
    }

    @Test
    public void testGetById_WhenRegistrationOk() throws RegistrationInvalidException, RegistrationNotFoundException, RegistrationDeletedException, RegistrationPaidDelayedException {
        // Arrange
        String registrationNumberExpected = "1234567";

        when(registration.getTuition()).thenReturn(tuition);
        when(registration.getId()).thenReturn(registrationNumberExpected);
        when(registration.getTuition().getStatus()).thenReturn(Status.EM_DIA);

        List<Registration> registrationList = List.of(registration);
        doReturn(registrationList).when(registrationService).getAll();

        // Act
        Registration registrationActual = registrationService.getById(registrationNumberExpected);

        // Assert
        assertEquals(registration, registrationActual);
    }

    @Test
    public void testGetById_WhenRegistrationPaidIsDelayed() {
        // Arrange
        String registrationNumberExpected = "1234567";

        when(registration.getTuition()).thenReturn(tuition);
        when(registration.getId()).thenReturn(registrationNumberExpected);
        when(registration.getTuition().getStatus()).thenReturn(Status.PAGAMENTO_EM_ATRASO);

        List<Registration> registrationList = List.of(registration);
        doReturn(registrationList).when(registrationService).getAll();

        // Act & Assert
        assertThrows(
                RegistrationPaidDelayedException.class,
                () -> registrationService.getById(registrationNumberExpected)
        );
    }

    @Test
    public void testGetById_WhenRegistrationIsDeleted() {
        // Arrange
        String registrationNumberExpected = "1234567";

        when(registration.getTuition()).thenReturn(tuition);
        when(registration.getId()).thenReturn(registrationNumberExpected);
        when(registration.getTuition().getStatus()).thenReturn(Status.MATRICULA_EXCLUIDA);

        List<Registration> registrationList = List.of(registration);
        doReturn(registrationList).when(registrationService).getAll();

        // Act & Assert
        assertThrows(
                RegistrationDeletedException.class,
                () -> registrationService.getById(registrationNumberExpected)
        );
    }

    @Test
    public void testGetById_WhenRegistrationNumberIsNotValid() {
        // Arrange
        String registrationNumberExpected = "123456";

        when(registration.getTuition()).thenReturn(tuition);
        when(registration.getId()).thenReturn(registrationNumberExpected);
        when(registration.getTuition().getStatus()).thenReturn(Status.MATRICULA_EXCLUIDA);

        List<Registration> registrationList = List.of(registration);
        doReturn(registrationList).when(registrationService).getAll();

        // Act & Assert
        assertThrows(
                RegistrationInvalidException.class,
                () -> registrationService.getById(registrationNumberExpected)
        );
    }

    @Test
    public void testGetById_WhenRegistrationIsNotFound() {
        // Arrange
        String registrationNumberExpected = "1234567";

        when(registration.getTuition()).thenReturn(tuition);
        when(registration.getId()).thenReturn("9999999");
        when(registration.getTuition().getStatus()).thenReturn(Status.MATRICULA_EXCLUIDA);

        List<Registration> registrationList = List.of(registration);
        doReturn(registrationList).when(registrationService).getAll();

        // Act & Assert
        assertThrows(
                RegistrationNotFoundException.class,
                () -> registrationService.getById(registrationNumberExpected)
        );
    }

    @Test
    public void testGetById_WhenRegistrationHasScholarship() throws RegistrationInvalidException, RegistrationNotFoundException, RegistrationPaidDelayedException, RegistrationDeletedException {
        // Arrange
        String registrationNumberExpected = "1234567";
        double tuitionAmountExpected = 0.00;
        String tuitionAmountFormattedExpected = "R$ 0.00";

        when(registration.getTuition()).thenReturn(tuition);
        when(registration.getId()).thenReturn(registrationNumberExpected);
        when(registration.getTuition().getFormattedAmount()).thenReturn(tuitionAmountFormattedExpected);
        when(registration.getTuition().getStatus()).thenReturn(Status.BOLSISTA_100);

        List<Registration> registrationList = List.of(registration);
        doReturn(registrationList).when(registrationService).getAll();

        // Act
        Registration registrationActual = registrationService.getById(registrationNumberExpected);

        // Assert
        assertEquals(registration, registrationActual);
        assertEquals(tuitionAmountExpected, registration.getTuition().getAmount());
        assertEquals(tuitionAmountFormattedExpected, registration.getTuition().getFormattedAmount());
    }

    @Test
    public void testGetById_WhenRegistrationHasPaidMonthlyFees() throws RegistrationInvalidException, RegistrationNotFoundException, RegistrationPaidDelayedException, RegistrationDeletedException {
        // Arrange
        String registrationNumberExpected = "1234567";
        LocalDate setDueDateExpected = null;

        when(registration.getTuition()).thenReturn(tuition);
        when(registration.getId()).thenReturn(registrationNumberExpected);
        when(registration.getTuition().getStatus()).thenReturn(Status.CONTRATO_QUITADO);

        List<Registration> registrationList = List.of(registration);
        doReturn(registrationList).when(registrationService).getAll();

        // Act
        Registration registrationActual = registrationService.getById(registrationNumberExpected);

        // Assert
        assertEquals(registration, registrationActual);
        assertEquals(setDueDateExpected, registration.getTuition().getDueDate());
    }
}
