package com.mehebbet_spring_project_1.mehebbet_spring_project_1.util;

import com.mehebbet_spring_project_1.mehebbet_spring_project_1.models.UserDtls;
import com.mehebbet_spring_project_1.mehebbet_spring_project_1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class CommonUtil {
    @Autowired
    private UserService userService;

    public UserDtls getLoggedInUserDetails(Principal p) {
        String email = p.getName();
        UserDtls userDtls = userService.getUserByEmail(email);
        return userDtls;
    }
}
