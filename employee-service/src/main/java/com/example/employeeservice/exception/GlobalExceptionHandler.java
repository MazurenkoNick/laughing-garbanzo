package com.example.employeeservice.exception;

//@RestControllerAdvice
//@Log4j2
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
//            MethodArgumentNotValidException exception) {
//
//        List<ObjectError> errors =  exception.getAllErrors();
//        Map<String, String> fieldErrors = new HashMap<>();
//
//        errors.forEach(
//                error -> fieldErrors.put(
//                        ((FieldError) error).getField(),
//                        error.getDefaultMessage()
//                )
//        );
//
//        return new ResponseEntity<>(fieldErrors, HttpStatus.BAD_REQUEST);
//    }
//
//    @ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleEntityNotFoundException(EntityNotFoundException exception) {
//
//        log.error(exception);
//
//        var errorDetails = ErrorDetails.builder()
//                .dateTime(LocalDateTime.now())
//                .message(exception.getMessage())
//                .errorCode("ENTITY_NOT_FOUND_ERROR")
//                .build();
//
//        return ResponseEntity
//                .status(HttpStatus.BAD_REQUEST)
//                .body(errorDetails);
//
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorDetails> handleException(Exception exception) {
//
//        log.error(exception);
//
//        var errorDetails = ErrorDetails.builder()
//                .dateTime(LocalDateTime.now())
//                .message(exception.getMessage())
//                .errorCode("INTERNAL_ERROR")
//                .build();
//
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(errorDetails);
//    }
//}