package thiagoespindula00.emiteai.backend.trata_erros;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.stream.Collectors;

@RestControllerAdvice
public class TratadorDeErros {
    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<?> trataErroRegraDeNegocio(ValidacaoException ex) {
        return ResponseEntity.badRequest().body(new ErroResponseDto(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> trataErroRegraDeNegocioBeanValidation(MethodArgumentNotValidException exception) {
        var fieldError = exception.getBindingResult().getFieldErrors().get(0);

        return ResponseEntity.badRequest().body(new ErroResponseDto(fieldError.getField() + ": " + fieldError.getDefaultMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> trataErroEntidadeNaoEncontrada(EntityNotFoundException exception) {
        return ResponseEntity.notFound().build();
    }
}
