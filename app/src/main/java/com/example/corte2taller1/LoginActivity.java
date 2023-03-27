package com.example.corte2taller1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText usuario, contraseña;
    Button iniciar;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = findViewById(R.id.usuario1);
        contraseña = findViewById(R.id.contraseña1);
        iniciar = findViewById(R.id.iniciar1);
        DB = new DBHelper(this);

        iniciar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String user = usuario.getText().toString();
                String contra = contraseña.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(contra))
                Toast.makeText(LoginActivity.this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, contra);
                    if(checkuserpass==true){
                        Toast.makeText(LoginActivity.this, "Inicio exitoso",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Inicio inválido", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}