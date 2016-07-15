package com.nodesagency.espressotest.util;

import android.support.annotation.NonNull;

/**
 * Created by liamakakpo on 14/07/2016.
 */
public class PasswordValidator {

    public enum Error {
        EMPTY,
        TOO_SHORT,
        LOWERCASE_REQUIRED,
        UPPERCASE_REQUIRED,
        NUMBER_REQUIRED,
        SPECIAL_CHARACTER_REQUIRED
    }

    private int minimumLength = -1;
    private boolean requireUpperCase = false;
    private boolean requireLowerCase = false;
    private boolean requireSpecialCharacter = false;
    private boolean requireNumber = false;

    public Error error = null;

    private PasswordValidator() {

    }

    public boolean validate(@NonNull final String text) {
        if (minimumLength != -1) {
            int length = text.length();
            if (length < minimumLength) {
                error = length == 0 ? Error.EMPTY : Error.TOO_SHORT;
                return false;
            }
        }
        boolean hasLowerCase = false;
        boolean hasUpperCase = false;
        boolean hasNumber = false;
        boolean hasSpecialCharacter = false;
        for (char c : text.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpperCase = true;
            if (Character.isLowerCase(c)) hasLowerCase = true;
            if (Character.isDigit(c)) hasNumber = true;
            if (Character.toString(c).matches("[^A-Za-z0-9 ]")) hasSpecialCharacter = true;
        }
        if (requireLowerCase && !hasLowerCase) {
            this.error = Error.LOWERCASE_REQUIRED;
            return false;
        }
        if (requireUpperCase && !hasUpperCase) {
            this.error = Error.UPPERCASE_REQUIRED;
            return false;
        }
        if (requireNumber && !hasNumber) {
            this.error = Error.NUMBER_REQUIRED;
            return false;
        }
        if (requireSpecialCharacter && !hasSpecialCharacter) {
            this.error = Error.SPECIAL_CHARACTER_REQUIRED;
            return false;
        }
        return true;
    }

    public Error getError() {
        return error;
    }

    public void clearError() {
        this.error = null;
    }

    public static class Builder {

        private PasswordValidator passwordValidator;

        public Builder() {
            this.passwordValidator = new PasswordValidator();
        }

        public Builder setMinimumLength(final int minimumLength) {
            this.passwordValidator.minimumLength = minimumLength;
            return this;
        }

        public Builder requireUpperCase(final boolean required) {
            passwordValidator.requireUpperCase = required;
            return this;
        }

        public Builder requireLowerCase(final boolean required) {
            passwordValidator.requireLowerCase = required;
            return this;
        }

        public Builder requireSpecialCharacter(final boolean required) {
            passwordValidator.requireSpecialCharacter = required;
            return this;
        }

        public Builder requireNumber(final boolean required) {
            passwordValidator.requireNumber = required;
            return this;
        }

        public PasswordValidator build() {
            return passwordValidator;
        }

    }

}
