package com.example.oderfood;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        ActionBar ac = getSupportActionBar();
        ac.hide();
        edtUsername = findViewById( R.id.edtUsrename );
        edtPassword = findViewById( R.id.edtPassword );
        btnLogin = findViewById( R.id.btnLogin );
        btnLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                boolean check = checkPassword( username, password );
                if (check) {
                    Intent intent = new Intent( getBaseContext(), MenuFood.class );
                    intent.putExtra( "username", edtUsername.getText().toString() );
                    startActivityForResult( intent, 100 );
                }
            }
        } );
    }

    public boolean checkPassword(String Username, String password) {
        if (Username.length() == 0 || password.length() ==0) {
            Toast.makeText( getBaseContext(), "Bạn phải nhập tài khoản và mật khẩu", Toast.LENGTH_SHORT ).show();
            return false;
        }
        if (password.length() < 8) {
            Toast.makeText( getBaseContext(), "Mật khẩu phải từ 8 kí tự trở lên", Toast.LENGTH_SHORT ).show();
            return false;
        } else if (!isDigit( password )) {
            Toast.makeText( getBaseContext(), "Mật khẩu phải chứa ít nhất 1 số!", Toast.LENGTH_SHORT ).show();
            return false;
        } else if (!isLower( password )) {
            Toast.makeText( getBaseContext(), "Mật khẩu phải chứa ít nhất kí tự in thường!", Toast.LENGTH_SHORT ).show();
            return false;
        } else if (!isUpper( password )) {
            Toast.makeText( getBaseContext(), "Mật khẩu phải chứa ít nhất kí tự in hoa!", Toast.LENGTH_SHORT ).show();
            return false;
        } else if (!isSpecial( password )) {
            Toast.makeText( getBaseContext(), "Mật khẩu phải chứa ít nhất kí tự đặc biệt", Toast.LENGTH_SHORT ).show();
            return false;
        }
        return true;
    }

    public static boolean isLower(String let) {
        for (int i = 'a'; i <= 'z'; i++) {
            if (let.indexOf( (i) ) >= 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isUpper(String let) {
        for (int i = 'A'; i <= 'Z'; i++) {
            if (let.indexOf( (i) ) >= 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDigit(String let) {
        for (int i = '0'; i <= '9'; i++) {
            if (let.indexOf( (i) ) >= 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSpecial(String let) {
        String s = "!@#$%^&*()_ ";
        for (int i = 0; i < let.length(); i++) {
            if (s.indexOf( let.charAt( i ) ) >= 0)
                return true;
        }
        return false;
    }
}
