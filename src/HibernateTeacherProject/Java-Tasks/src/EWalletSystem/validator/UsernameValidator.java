package EWalletSystem.validator;

import EWalletSystem.exception.ValidationException;
import EWalletSystem.services.AccountService;

public class UsernameValidator {
    private final AccountService accountService;

    public UsernameValidator(AccountService accountService) {
        this.accountService = accountService;
    }

    public String getValidUsername(String username) throws ValidationException {
        if (username.trim().isEmpty()) {
            throw new ValidationException("Username cannot be empty");
        }
        if (!Character.isUpperCase(username.charAt(0))) {
            throw new ValidationException("Username must start with an uppercase letter");
        }
        for (char c : username.toCharArray()) {
            if (!Character.isLetter(c)) {
                throw new ValidationException("Username can only contain letters");
            }
        }
        if (!accountService.isUsernameUnique(username)) {
            throw new ValidationException("Username already exists");
        }
        return username;
    }
}