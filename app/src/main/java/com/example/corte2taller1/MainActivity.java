package com.example.corte2taller1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText usuario, contraseña, recontraseña;
    Button registrar, iniciar;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario=findViewById(R.id.usuario1);
        contraseña=findViewById(R.id.contraseña1);
        registrar=findViewById(R.id.iniciar1);
        iniciar=findViewById(R.id.iniciar);
        DB = new DBHelper(this);


        registrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String user = usuario.getText().toString();
                String contra = contraseña.getText().toString();
                String recontra = recontraseña.getText().toString();

                if(TextUtils.isEmpty(user) || TextUtils.isEmpty(contra) || TextUtils.isEmpty(recontra))
                    Toast.makeText(MainActivity.this, "Rellene los campos", Toast.LENGTH_SHORT).show();
                else{
                    if(contra.equals(recontra)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user, contra);
                            if(insert==true) {
                                Toast.makeText(MainActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            }else{
                                    Toast.makeText(MainActivity.this, "El registro falló", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(MainActivity.this, "Usuario existente", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                        }
                    }
                }



        });

        iniciar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}