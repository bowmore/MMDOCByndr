package be.degreyt.mmdoc.exceptions;

import java.io.IOException;

public class UnderlyingIOException extends RuntimeException {
    public UnderlyingIOException(IOException cause) {
        super(cause.getMessage(), cause);
    }
}
