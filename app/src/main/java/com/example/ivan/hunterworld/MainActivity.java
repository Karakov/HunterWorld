package com.example.ivan.hunterworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // лучше давать классам осознанные имена. AuthorizationActivity например

    private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<AuthUI.IdpConfig> providers = Collections.singletonList(new AuthUI.IdpConfig.EmailBuilder().build());
        // Если нужно поместить лишь один элемент в лист, то лучше использовать singletonList
        startActivityForResult(AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build()
                , RC_SIGN_IN);
        // желательно форматировать код в соответствии и дефолтным code style (ctrl+alt+L hotkey)
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            // баг - проверялся не тот код, потому код в блоке не выполнялся
            FirebaseUser response = FirebaseAuth.getInstance().getCurrentUser();
// а это действительно нужно, если результат никак не используется?
            if (resultCode == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Toast.makeText(this, user.toString(), Toast.LENGTH_SHORT).show();
                    // показ тоста лучше вынести в отдельную функцию, т.к. еще возможно пригодится
                } else {
// тут лучше либо определить какое-то действие, либо исключить else блок
                }
            }
        }
    }
}
