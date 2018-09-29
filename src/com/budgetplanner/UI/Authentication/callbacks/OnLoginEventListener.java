package com.budgetplanner.UI.Authentication.callbacks;

import com.budgetplanner.datamodel.User;

public interface OnLoginEventListener {
    void onLoginSuccessful(User user);

    void onRegisterPressed();
}
