package com.crossover.trial.weather.exception;

/**
 * An internal exception marker
 */
public class WeatherException extends Exception {
    private String message = null;

    public WeatherException() {
        super();
    }

    public WeatherException(String message) {
        super(message);
        this.message = message;
    }

    public WeatherException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
