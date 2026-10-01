package com.example.pc.exception;

import org.springframework.beans.factory.parsing.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({UserAlreadyExistsException.class})
    public ProblemDetail handleConflict(UserAlreadyExistsException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT,
                ex.getMessage()
        );
        problem.setTitle("Conflicto");
        return problem;
    }
    @ExceptionHandler({StoreNotFoundException.class})
    public ProblemDetail handleStoreNotFoundException(StoreNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
        problem.setTitle("No Encontrado");
        return problem;
    }
    @ExceptionHandler({ProductNotFoundException.class})
    public ProblemDetail handleProductNotFoundException(ProductNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
        problem.setTitle("No Encontrado");
        return problem;
    }

    @ExceptionHandler({ForbiddenStoreActionException.class})
    public ProblemDetail handleForbiddenStoreActionException(ForbiddenStoreActionException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.FORBIDDEN,
                ex.getMessage()
        );
        problem.setTitle("Acción Prohibida");
        return problem;
    }







}
