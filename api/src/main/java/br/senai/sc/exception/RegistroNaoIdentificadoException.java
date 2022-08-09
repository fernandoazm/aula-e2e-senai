package br.senai.sc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RegistroNaoIdentificadoException extends EntityNotFoundException {

    public RegistroNaoIdentificadoException(final UUID id) {
        super(String.format("Registro %s não identificado", id));
    }
}
