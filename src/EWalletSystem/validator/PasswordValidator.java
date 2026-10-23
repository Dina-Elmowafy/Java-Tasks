package EWalletSystem.validator;

import EWalletSystem.exception.ValidationException;

public class PasswordValidator {
    public String getValidPassword(String password) throws ValidationException {
        if (password.length() < 8) {
            throw new ValidationException("Password must be at least 8 characters");
        }
        boolean hasUpper = false, hasLower = false, hasDigit = false, hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else hasSpecial = true;
        }
        if (!(hasUpper && hasLower && hasDigit && hasSpecial)) {
            throw new ValidationException("Password must contain uppercase, lowercase, digit, and special character");
        }
        return password;
    }
}