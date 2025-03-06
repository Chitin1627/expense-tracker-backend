package com.backend.expensetracker.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PasswordChangeRequest {
    String oldPassword;
    String newPassword;
}
