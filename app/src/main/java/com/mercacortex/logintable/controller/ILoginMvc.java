package com.mercacortex.logintable.controller;

/**
 * Interfaz con las reglas de negocio
 * @Version 1.0
 * @Author Churrasquito
 */

public interface ILoginMvc {

    public int validateCredentials(String user, String pwd);
}
