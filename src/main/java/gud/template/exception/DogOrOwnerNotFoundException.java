package gud.template.exception;

public class DogOrOwnerNotFoundException extends RuntimeException {

    public DogOrOwnerNotFoundException() {
        super("The dog or the owner is not found");
    }
}
