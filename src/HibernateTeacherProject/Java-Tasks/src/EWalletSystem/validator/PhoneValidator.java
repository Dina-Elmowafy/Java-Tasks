package EWalletSystem.validator;

import EWalletSystem.exception.ValidationException;
import EWalletSystem.services.AccountService;

public class PhoneValidator {
    private final AccountService accountService;

    public PhoneValidator(AccountService accountService) {
        this.accountService = accountService;
    }

    public String getValidPhone(String phone) throws ValidationException {
        if (phone.length() != 11) {
            throw new ValidationException("Phone number must be exactly 11 digits");
        }
        String prefix = phone.substring(0, 3);
        if (!(prefix.equals("010") || prefix.equals("011") || prefix.equals("012") || prefix.equals("015"))) {
            throw new ValidationException("Invalid Egyptian phone prefix (010,011,012,015)");
        }
        if (!accountService.isPhoneNumberUnique(phone)) {
            throw new ValidationException("Phone number already used");
        }
        return phone;
    }
}