package az.edu.itbrains.ecommerce.payloads.results.error;

import az.edu.itbrains.ecommerce.payloads.results.Result;
import lombok.Getter;
import lombok.Setter;


public class ErrorResult extends Result {
    public ErrorResult() {
        super(false);
    }

    public ErrorResult( String message) {
        super(false, message);
    }
}
