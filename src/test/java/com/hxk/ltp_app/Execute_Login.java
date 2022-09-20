package com.hxk.ltp_app;

import org.testng.annotations.Test;

public class Execute_Login {
    @Test(enabled = false)
    public void Execute_Login() throws Exception{
        Login_Smslogin login_smslogin = new Login_Smslogin();
        String token = login_smslogin.login();
//        Lpt_ReadToken readToken = new Lpt_ReadToken();
//        String token = readToken.readTxt();
        System.out.println("================="+token);
    }
}
