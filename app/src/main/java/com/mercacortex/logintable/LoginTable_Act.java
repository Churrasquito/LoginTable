package com.mercacortex.logintable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mercacortex.logintable.controller.ILoginMvc;
import com.mercacortex.logintable.controller.LoginTable_Controller;

import static android.R.attr.onClick;
import static com.mercacortex.logintable.R.string.login;

/**
 * Actividad que aplica las reglas del negocio del controlador según el MVC
 * @Version 1.0
 * @Author Churrasquito
 */

public class LoginTable_Act extends AppCompatActivity{

    private ILoginMvc loginMvc;
    private EditText medtUser, medtPwd;
    private Button mbtnOk, mbtnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_table_);
        loginMvc = new LoginTable_Controller();
        medtUser = (EditText) findViewById(R.id.edtUser);
        medtPwd = (EditText) findViewById(R.id.edtPwd);
        mbtnOk = (Button) findViewById(R.id.btnOk);

        mbtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = medtUser.getText().toString();
                String pwd = medtPwd.getText().toString();
                // Aquí nos arriesgamos a un NullPointer, por lo que usamos TextUtils
                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pwd))
                    // El getResources forma parte del Resource Manager de Android
                    // Me permite encontrar una cadena dentro de la clase R para inflarla
                    Toast.makeText(LoginTable_Act.this,getResources().getString(R.string.data_empty),Toast.LENGTH_SHORT).show();
                else{
                    int result = loginMvc.validateCredentials(user, pwd);
                    switch (result){
                        case LoginTable_Controller.PASSWORD_DIGIT:
                            Toast.makeText(LoginTable_Act.this, getResources().getString(R.string.password_digit), Toast.LENGTH_LONG).show();
                            break;
                        case LoginTable_Controller.PASSWORD_CASE:
                            Toast.makeText(LoginTable_Act.this, getResources().getString(R.string.password_case), Toast.LENGTH_LONG).show();
                            break;
                        case LoginTable_Controller.PASSWORD_LENGTH:
                            Toast.makeText(LoginTable_Act.this, getResources().getString(R.string.password_length), Toast.LENGTH_LONG).show();
                            break;
                        case LoginTable_Controller.OK:
                            // Se lanzaría la actividad después del login
                            break;
                    }
                }
            }
        });

        mbtnCancel = (Button) findViewById(R.id.btnCancel);
        mbtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetValues();

            }
        });
    }

    private void resetValues() {
        medtPwd.setText("");
        medtUser.setText("");
    }
}
