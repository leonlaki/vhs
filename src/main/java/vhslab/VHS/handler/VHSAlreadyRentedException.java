package vhslab.VHS.handler;

public class VHSAlreadyRentedException extends RuntimeException {

    public VHSAlreadyRentedException() {
        super("This VHS tape has already been rented.");
    }

}
