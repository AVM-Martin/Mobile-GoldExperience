package id.my.avmmartin.goldexperience.exception;

public class DuplicateUserException extends GeneralException {
    public DuplicateUserException() {
        super("User not found");
    }
}
