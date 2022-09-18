package com.backendchallenge.customException;

public class AlreadyExistsException extends Exception {
        private String message;

        public AlreadyExistsException(String message) {
                this.message = message;
        }
}
