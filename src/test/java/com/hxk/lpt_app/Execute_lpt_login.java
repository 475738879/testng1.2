package com.hxk.lpt_app;

import org.testng.annotations.Test;

public class Execute_lpt_login {
    @Test(enabled = false)
    public void execute() throws Exception {
        Login_Smslogin login = new Login_Smslogin();
        String token = login.lpt_login();
        System.out.println("----------------"+token);
    }
}
