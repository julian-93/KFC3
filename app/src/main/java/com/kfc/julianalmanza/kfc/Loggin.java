package com.kfc.julianalmanza.kfc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;


public class Loggin extends AppCompatActivity {

    EditText usuario_ingresar,contraseña_ingresar;
    Button ingresar,registrar;
    String valor;
    ArrayList<usuario_datos> usuarios   =new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);

        usuario_ingresar=(EditText) findViewById(R.id.usuario_ingresar);
        contraseña_ingresar=(EditText) findViewById(R.id.contraseña_ingresar);
        ingresar=(Button) findViewById(R.id.ingresar);
        registrar=(Button) findViewById(R.id.registrar);

        SharedPreferences prefs = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        if(prefs.getString("registrar","").equals("si")){
            String nomb=prefs.getString("nombre","");
            String contr=prefs.getString("contraseña","");
            String corr=prefs.getString("correo","");
            usuario_datos us= new usuario_datos();
            us.ingresar_usuario(nomb,contr,corr);
            usuarios.add(us);
        }
        editor.putString("registrar","");
        editor.commit();



        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent().setClass(Loggin.this,Registrarse.class);
                startActivityForResult(i,1234);
            }
        });

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usu=usuario_ingresar.getText().toString();
                String contras=contraseña_ingresar.getText().toString();
                int comprobar=0;
                int puntero=0;
                for(int y=0;y<usuarios.size();y++){
                    if(usu.equals(usuarios.get(y).getnombre())){
                        if(contras.equals(usuarios.get(y).getcontraseña())){
                            puntero=y;
                            comprobar=1;
                        }
                    }
                }
                if(comprobar==1) {

                    Intent i = new Intent().setClass(Loggin.this, MainActivity.class);
                    i.putExtra("nombre", usuarios.get(puntero).getnombre());
                    i.putExtra("contraseña", usuarios.get(puntero).getcontraseña());
                    i.putExtra("correo", usuarios.get(puntero).getcorreo());
                    startActivity(i);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK) {
            String nomb=data.getExtras().getString("nombre");
            String contr=data.getExtras().getString("contraseña");
            String corr=data.getExtras().getString("correo");
            usuario_datos us= new usuario_datos();
            us.ingresar_usuario(nomb,contr,corr);
            usuarios.add(us);
            usuario_ingresar.setText(nomb);
            contraseña_ingresar.setText(contr);
        }else{

        }
    }

}

