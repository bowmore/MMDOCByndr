package be.degreyt.mmdoc.exceptions;

import java.net.MalformedURLException;

public class UnderlyingUrlException extends RuntimeException {

    public UnderlyingUrlException(MalformedURLException cause) {
        super(cause);
    }
}
