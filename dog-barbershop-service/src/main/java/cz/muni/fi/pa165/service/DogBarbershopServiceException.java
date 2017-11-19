package cz.muni.fi.pa165.service;

public class DogBarbershopServiceException extends RuntimeException {

    public DogBarbershopServiceException() {
    }

    public DogBarbershopServiceException(String message) {
        super(message);
    }

    public DogBarbershopServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DogBarbershopServiceException(Throwable cause) {
        super(cause);
    }
}
