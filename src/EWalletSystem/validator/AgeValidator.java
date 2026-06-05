package EWalletSystem.validator;

import EWalletSystem.exception.ValidationException;

public class AgeValidator {
    public int getValidAge(String ageStr) throws ValidationException {
        try {
            int age = Integer.parseInt(ageStr.trim());
            if (age < 18) {
                throw new ValidationException("Age must be 18 or older");
            }
            return age;
        } catch (NumberFormatException e) {
            throw new ValidationException("Age must be a valid number");
        }
    }
}