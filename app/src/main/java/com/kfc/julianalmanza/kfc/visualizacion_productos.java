package com.kfc.julianalmanza.kfc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class visualizacion_productos extends AppCompatActivity {
    TextView titulo,descripcion;
    ImageView imagen;
    String imag;
    String nombre,descrip;
    Button volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizacion_productos);

        Bundle extra =getIntent().getExtras();
        nombre=extra.getString("nombre");
        descrip=extra.getString("descripcion");
        imag=extra.getString("imagen");
        titulo=(TextView) findViewById(R.id.titulo);
        descripcion=(TextView) findViewById(R.id.descripcion);
        imagen=(ImageView) findViewById(R.id.imagen);

        titulo.setText(nombre);
        descripcion.setText(descrip);
        int a=Integer.parseInt(imag);
        imagen.setImageDrawable(getResources().getDrawable(a));
        volver=(Button) findViewById(R.id.volver);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
