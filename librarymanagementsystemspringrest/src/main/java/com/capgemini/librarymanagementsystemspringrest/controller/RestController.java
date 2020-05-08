package com.capgemini.librarymanagementsystemspringrest.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capgemini.librarymanagementsystemspringrest.dto.Response;
import com.capgemini.librarymanagementsystemspringrest.exception.LMSException;

@RestControllerAdvice
public class RestController {
	@ExceptionHandler
	public Response myExceptionHandler(LMSException lmsException) {
		Response response = new Response();
		response.setError(true);
		response.setMessage(lmsException.getMessage());
		return response;
	}

}
