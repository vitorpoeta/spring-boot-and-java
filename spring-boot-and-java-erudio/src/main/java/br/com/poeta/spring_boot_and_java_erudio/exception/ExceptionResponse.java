package br.com.poeta.spring_boot_and_java_erudio.exception;

import java.util.Date;

public record ExceptionResponse(Date timeStamp, String message, String details) { }
