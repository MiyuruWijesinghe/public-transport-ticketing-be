package com.csse.publictransport.core;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.csse.publictransport.exception.CodeUniqueException;
import com.csse.publictransport.exception.InvalidServiceIdException;
import com.csse.publictransport.exception.NoRecordFoundException;
import com.csse.publictransport.exception.UserNotFoundException;
import com.csse.publictransport.exception.ValidateRecordException;
import com.csse.publictransport.resource.BusResource;
import com.csse.publictransport.resource.BusRouteMapResource;
import com.csse.publictransport.resource.BusRouteResource;
import com.csse.publictransport.resource.BusTokenRequestResource;
import com.csse.publictransport.resource.InspectionResource;
import com.csse.publictransport.resource.LoginRequestResource;
import com.csse.publictransport.resource.PublicTransportAccountAddResource;
import com.csse.publictransport.resource.PublicTransportAccountResource;
import com.csse.publictransport.resource.PublicTransportAccountUpdateResource;
import com.csse.publictransport.resource.RolesAddResource;
import com.csse.publictransport.resource.RolesUpdateResource;
import com.csse.publictransport.resource.SignupRequestResource;
import com.csse.publictransport.resource.SuccessAndErrorDetailsResource;
import com.csse.publictransport.resource.TravelCardRequestResource;
import com.csse.publictransport.resource.ValidateResource;


@RestControllerAdvice
public class BaseResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private Environment environment;
	
	
	@Override 
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) { 
		SuccessAndErrorDetailsResource successAndErrorDetailsDataObject = new SuccessAndErrorDetailsResource();
		successAndErrorDetailsDataObject.setMessages(environment.getProperty("common.invalid-url-pattern"));
		successAndErrorDetailsDataObject.setDetails(ex.getMessage());
		return new ResponseEntity<>(successAndErrorDetailsDataObject, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({MethodArgumentTypeMismatchException.class})
	public ResponseEntity<Object> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {
		SuccessAndErrorDetailsResource successAndErrorDetailsDataObject = new SuccessAndErrorDetailsResource();
		successAndErrorDetailsDataObject.setMessages(environment.getProperty("common.argument-type-mismatch"));
		successAndErrorDetailsDataObject.setDetails(ex.getMessage());
		return new ResponseEntity<>(successAndErrorDetailsDataObject, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({UserNotFoundException.class})
	public ResponseEntity<Object> userNotFoundException(UserNotFoundException ex, WebRequest request) {
		SuccessAndErrorDetailsResource successAndErrorDetailsResource = new SuccessAndErrorDetailsResource();
		successAndErrorDetailsResource.setMessages(environment.getProperty("common.user-not-found"));
		return new ResponseEntity<>(successAndErrorDetailsResource, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler({CodeUniqueException.class})
	public ResponseEntity<Object> codeUniqueException(CodeUniqueException ex, WebRequest request) {
		SuccessAndErrorDetailsResource successAndErrorDetailsDataObject = new SuccessAndErrorDetailsResource();
		successAndErrorDetailsDataObject.setMessages(environment.getProperty("common.code-duplicate"));
		return new ResponseEntity<>(successAndErrorDetailsDataObject, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler({NoRecordFoundException.class})
	public ResponseEntity<Object> noRecordFoundException(NoRecordFoundException ex, WebRequest request) {
		SuccessAndErrorDetailsResource successAndErrorDetailsDataObject = new SuccessAndErrorDetailsResource();
		successAndErrorDetailsDataObject.setMessages(environment.getProperty("common.record-not-found"));
		return new ResponseEntity<>(successAndErrorDetailsDataObject, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		try {
			Field sField = null;
			String fieldName = null;
			Integer index = null;
			Integer index1 = null;
			Integer atPoint = null;
			String className = ex.getBindingResult().getObjectName();
			
			switch (className) {

			case "rolesAddResource":
				RolesAddResource rolesAddResource = new RolesAddResource();
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					sField = rolesAddResource.getClass().getDeclaredField(error.getField());
					sField.setAccessible(true);
					sField.set(rolesAddResource.getClass().cast(rolesAddResource), error.getDefaultMessage());
				}
				return new ResponseEntity<>(rolesAddResource, HttpStatus.UNPROCESSABLE_ENTITY);
			case "rolesUpdateResource":
				RolesUpdateResource rolesUpdateResource = new RolesUpdateResource();
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					sField = rolesUpdateResource.getClass().getDeclaredField(error.getField());
					sField.setAccessible(true);
					sField.set(rolesUpdateResource.getClass().cast(rolesUpdateResource), error.getDefaultMessage());
				}
				return new ResponseEntity<>(rolesUpdateResource, HttpStatus.UNPROCESSABLE_ENTITY);
			case "signupRequestResource":
				SignupRequestResource signupRequestResource = new SignupRequestResource();
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					sField = signupRequestResource.getClass().getDeclaredField(error.getField());
					sField.setAccessible(true);
					sField.set(signupRequestResource.getClass().cast(signupRequestResource), error.getDefaultMessage());
				}
				return new ResponseEntity<>(signupRequestResource, HttpStatus.UNPROCESSABLE_ENTITY);
			case "loginRequestResource":
				LoginRequestResource loginRequestResource = new LoginRequestResource();
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					sField = loginRequestResource.getClass().getDeclaredField(error.getField());
					sField.setAccessible(true);
					sField.set(loginRequestResource.getClass().cast(loginRequestResource), error.getDefaultMessage());
				}
				return new ResponseEntity<>(loginRequestResource, HttpStatus.UNPROCESSABLE_ENTITY);	
			case "busResource":
				BusResource busResource = new BusResource();
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					sField = busResource.getClass().getDeclaredField(error.getField());
					sField.setAccessible(true);
					sField.set(busResource.getClass().cast(busResource), error.getDefaultMessage());
				}
				return new ResponseEntity<>(busResource, HttpStatus.UNPROCESSABLE_ENTITY);
			case "busRouteResource":
				BusRouteResource busRouteResource = new BusRouteResource();
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					sField = busRouteResource.getClass().getDeclaredField(error.getField());
					sField.setAccessible(true);
					sField.set(busRouteResource.getClass().cast(busRouteResource), error.getDefaultMessage());
				}
				return new ResponseEntity<>(busRouteResource, HttpStatus.UNPROCESSABLE_ENTITY);	
			case "busRouteMapResource":
				BusRouteMapResource busRouteMapResource = new BusRouteMapResource();
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					sField = busRouteMapResource.getClass().getDeclaredField(error.getField());
					sField.setAccessible(true);
					sField.set(busRouteMapResource.getClass().cast(busRouteMapResource), error.getDefaultMessage());
				}
				return new ResponseEntity<>(busRouteMapResource, HttpStatus.UNPROCESSABLE_ENTITY);	
			case "inspectionResource":
				InspectionResource inspectionResource = new InspectionResource();
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					sField = inspectionResource.getClass().getDeclaredField(error.getField());
					sField.setAccessible(true);
					sField.set(inspectionResource.getClass().cast(inspectionResource), error.getDefaultMessage());
				}
				return new ResponseEntity<>(inspectionResource, HttpStatus.UNPROCESSABLE_ENTITY);
			case "publicTransportAccountResource":
				PublicTransportAccountResource publicTransportAccountResource = new PublicTransportAccountResource();
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					sField = publicTransportAccountResource.getClass().getDeclaredField(error.getField());
					sField.setAccessible(true);
					sField.set(publicTransportAccountResource.getClass().cast(publicTransportAccountResource), error.getDefaultMessage());
				}
				return new ResponseEntity<>(publicTransportAccountResource, HttpStatus.UNPROCESSABLE_ENTITY);
			case "publicTransportAccountAddResource":
				PublicTransportAccountAddResource publicTransportAccountAddResource = new PublicTransportAccountAddResource();
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					sField = publicTransportAccountAddResource.getClass().getDeclaredField(error.getField());
					sField.setAccessible(true);
					sField.set(publicTransportAccountAddResource.getClass().cast(publicTransportAccountAddResource), error.getDefaultMessage());
				}
				return new ResponseEntity<>(publicTransportAccountAddResource, HttpStatus.UNPROCESSABLE_ENTITY);
			case "publicTransportAccountUpdateResource":
				PublicTransportAccountUpdateResource publicTransportAccountUpdateResource = new PublicTransportAccountUpdateResource();
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					sField = publicTransportAccountUpdateResource.getClass().getDeclaredField(error.getField());
					sField.setAccessible(true);
					sField.set(publicTransportAccountUpdateResource.getClass().cast(publicTransportAccountUpdateResource), error.getDefaultMessage());
				}
				return new ResponseEntity<>(publicTransportAccountUpdateResource, HttpStatus.UNPROCESSABLE_ENTITY);
			case "travelCardRequestResource":
				TravelCardRequestResource travelCardRequestResource = new TravelCardRequestResource();
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					sField = travelCardRequestResource.getClass().getDeclaredField(error.getField());
					sField.setAccessible(true);
					sField.set(travelCardRequestResource.getClass().cast(travelCardRequestResource), error.getDefaultMessage());
				}
				return new ResponseEntity<>(travelCardRequestResource, HttpStatus.UNPROCESSABLE_ENTITY);	
			case "busTokenRequestResource":
				BusTokenRequestResource busTokenRequestResource = new BusTokenRequestResource();
				for (FieldError error : ex.getBindingResult().getFieldErrors()) {
					sField = busTokenRequestResource.getClass().getDeclaredField(error.getField());
					sField.setAccessible(true);
					sField.set(busTokenRequestResource.getClass().cast(busTokenRequestResource), error.getDefaultMessage());
				}
				return new ResponseEntity<>(busTokenRequestResource, HttpStatus.UNPROCESSABLE_ENTITY);
			default:
				return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
			}
		} catch (Exception e) {
			SuccessAndErrorDetailsResource successAndErrorDetailsResource = new SuccessAndErrorDetailsResource();
			successAndErrorDetailsResource.setMessages(environment.getProperty("common.internal-server-error"));
			successAndErrorDetailsResource.setDetails(e.getMessage());
			return new ResponseEntity<>(successAndErrorDetailsResource, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ExceptionHandler({InvalidServiceIdException.class})
	public ResponseEntity<Object> invalidServiceIdException(InvalidServiceIdException ex, WebRequest request) {
		ValidateResource validateResource=new ValidateResource();
		switch(ex.getServiceEntity()) 
        { 
            case USER_ID:
            	validateResource.setUserId(ex.getMessage());
            	break;
            default: 
 
        } 
        return new ResponseEntity<>(validateResource, HttpStatus.UNPROCESSABLE_ENTITY);
    }

	@ExceptionHandler({ValidateRecordException.class})
	public ResponseEntity<Object> validateRecordException(ValidateRecordException ex, WebRequest request) {
		try {
			ValidateResource typeValidation = new ValidateResource();		
			Class validationDetailClass = typeValidation.getClass();
			Field sField = validationDetailClass.getDeclaredField(ex.getField());
			sField.setAccessible(true);
			sField.set(typeValidation, ex.getMessage());		
			return new ResponseEntity<>(typeValidation, HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (Exception e) {
			SuccessAndErrorDetailsResource successAndErrorDetailsDto = new SuccessAndErrorDetailsResource();
			successAndErrorDetailsDto.setMessages(environment.getProperty("common.internal-server-error"));
			successAndErrorDetailsDto.setDetails(e.getMessage());
			return new ResponseEntity<>(successAndErrorDetailsDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
