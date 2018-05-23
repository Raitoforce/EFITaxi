package com.efitaxi.technous.efitaxi.Activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.efitaxi.technous.efitaxi.Activity.Fragment.LoginFragment;
import com.efitaxi.technous.efitaxi.Activity.Fragment.RegisterFragment;
import com.efitaxi.technous.efitaxi.R;

public class LoginActivity extends AppCompatActivity{
    private Fragment currentFragment=null;
    public static int fragment_login =1;
    public static int fragment_register =0;
    private Button registerButton;
    private int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layaout);
        if (currentFragment==null)
            setCurrentFragment(fragment_login);
        registerButton= (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (state==fragment_login) {
                    registerButton.setText("Ya tengo Cuenta");
                    setCurrentFragment(fragment_register);
                }
                else {
                    registerButton.setText("Registrarse");
                    setCurrentFragment(fragment_login);
                }
            }
        });
    }


    public void setCurrentFragment(int opcion) {
            if (opcion==1){
                currentFragment=new LoginFragment();
                state=1;
            }else{
                currentFragment=new RegisterFragment();
                state=0;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_contenido,currentFragment).commit();
    }
}
