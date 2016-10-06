package com.mercacortex.logintable.controller;

/**
 * Clase que implementa las reglas de negocio del Login
 * @Version 1.0
 * @Author Churrasquito
 */

public class LoginTable_Controller implements ILoginMvc{

    public static final int PASSWORD_DIGIT = 1;
    public static final int PASSWORD_CASE = 2;
    public static final int PASSWORD_LENGTH = 3;
    public static final int OK = 0;

    public int validateCredentials(String user, String pwd) {

        if(!pwd.matches(".*[0-9].*"))
            return PASSWORD_DIGIT ;
        if(!pwd.matches(".*[a-z].*") || !pwd.matches(".*[A-Z].*"))
            return PASSWORD_CASE;
        if(!pwd.matches(".{8,}"))
            return PASSWORD_LENGTH;

        return OK;
    }
}
