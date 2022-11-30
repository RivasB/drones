package com.musala.drones.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

/* (non-Javadoc)
 * @see java.lang.Throwable#toString()
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DroneControlException extends RuntimeException {
    
    private String message;
    private HttpStatus httpStatus;

    public DroneControlException(String message, HttpStatus httpStatus){
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;

    }
}
