package com.teamdevroute.devroute.global.error.exception;

import com.teamdevroute.devroute.global.error.ErrorCodes;

public class UserDuplicateException extends DuplicateException {

    public UserDuplicateException(final String email) {
        super(email, ErrorCodes.DUPLICATE_USER_EMAIL);
    }
}
