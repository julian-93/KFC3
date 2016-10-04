package com.kfc.julianalmanza.kfc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Miperfil extends AppCompatActivity {
    TextView datos;
    String nombre,contraseña,correo;


    private String[] opciones = new String[] {"Menu Principal","Mi Perfil","Comidas","Cerrar_sesion"};
    private DrawerLayout drawerLayout;
    ListView lista;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miperfil);
        Bundle extra =getIntent().getExtras();
        //nombre=extra.getString("nombre");
        //contraseña=extra.getString("contraseña");
        //correo=extra.getString("correo");
        SharedPreferences prefs = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        nombre = prefs.getString("nombre", "");
        contraseña = prefs.getString("contraseña", "");
        correo = prefs.getString("correo", "");
        datos=(TextView) findViewById(R.id.datos);
        String texto=getString(R.string.user)+"\n"+nombre+"\n"+getString(R.string.contraseña)+"\n"+contraseña+"\n"+getString(R.string.correo)+"\n"+correo;
        datos.setText(texto);


        drawerLayout=(DrawerLayout) findViewById(R.id.cp);
        lista=(ListView) findViewById(R.id.menuiz);
        lista.setAdapter(new ArrayAdapter<String>(getSupportActionBar().getThemedContext(),
                android.R.layout.simple_list_item_1,opciones));

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0: Intent intent=new Intent(Miperfil.this,MainActivity.class);
                        startActivity(intent);
                        break;
                    case 1: //Intent intent2=new Intent(Miperfil.this,Miperfil.class);
                        //startActivity(intent2);
                        break;
                    case 2: Intent intent3=new Intent(Miperfil.this,Productos.class);
                        startActivity(intent3);
                        break;
                    case 3:
                        SharedPreferences prefs = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("registrar","si");
                        editor.commit();

                        Intent intent4=new Intent(Miperfil.this,Loggin.class);
                        startActivity(intent4);
                        break;
                }

                lista.setItemChecked(position,true);
                drawerLayout.closeDrawer(lista);

            }
        });
        drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.abierto,R.string.cerrado);
        drawerLayout.setDrawerListener(drawerToggle);
        final ActionBar ab = getSupportActionBar();
        if (ab!=null){

            ab.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            ab.setDisplayHomeAsUpEnabled(true);
        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.LEFT);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){

            case(R.id.MainActivity):
                finish();
                break;
            case(R.id.Productos):
                Intent intent= new Intent(this,Productos.class);
                intent.putExtra("nombre",nombre);
                intent.putExtra("contraseña",contraseña);
                intent.putExtra("correo",correo);
                finish();
                startActivity(intent);
            break;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
