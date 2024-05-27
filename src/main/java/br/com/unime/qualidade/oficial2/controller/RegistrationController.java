package br.com.unime.qualidade.oficial2.controller;

import br.com.unime.qualidade.oficial2.entity.Registration;
import br.com.unime.qualidade.oficial2.exception.RegistrationDeletedException;
import br.com.unime.qualidade.oficial2.exception.RegistrationInvalidException;
import br.com.unime.qualidade.oficial2.exception.RegistrationNotFoundException;
import br.com.unime.qualidade.oficial2.exception.RegistrationPaidDelayedException;
import br.com.unime.qualidade.oficial2.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("v1/matriculas")
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/{matricula}")
    public ResponseEntity<Registration> getRegistrationById(
            @PathVariable String matricula
    ) throws
            RegistrationDeletedException,
            RegistrationNotFoundException,
            RegistrationInvalidException,
            RegistrationPaidDelayedException {
        Registration registration = registrationService.getById(matricula);

        return ResponseEntity.ok(registration);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception e) {
        Map<String, String> retorno = new HashMap<>();
        HttpStatus status = HttpStatus.OK;

        if (e instanceof RegistrationNotFoundException || e instanceof RegistrationDeletedException) {
            status = HttpStatus.NOT_FOUND;
        }

        if (e instanceof RegistrationInvalidException) {
            status = HttpStatus.BAD_REQUEST;
        }

        retorno.put("mensagem", e.getMessage());

        return ResponseEntity.status(status).body(retorno);
    }

}
