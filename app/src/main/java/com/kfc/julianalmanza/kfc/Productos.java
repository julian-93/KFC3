package com.kfc.julianalmanza.kfc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
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

public class Productos extends AppCompatActivity {
    private ViewPager mViewPager;

    String nombre,contraseña,correo;

    private String[] opciones = new String[] {"Menu Principal","Mi Perfil","Comidas","Cerrar_sesion"};
    private DrawerLayout drawerLayout;
    ListView lista;

    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        /*Bundle extra =getIntent().getExtras();
        nombre=extra.getString("nombre");
        contraseña=extra.getString("contraseña");
        correo=extra.getString("correo");*/

        PagerAdapter adapter=new PagerAdapter(getSupportFragmentManager());
        mViewPager=(ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(adapter);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

            }
        };

        ActionBar.Tab tab = actionBar.newTab().setText("Pollo").setTabListener(tabListener);
        actionBar.addTab (tab);
        tab = actionBar.newTab().setText("Sandwiches").setTabListener(tabListener);
        actionBar.addTab (tab);
        tab = actionBar.newTab().setText("Refrescos").setTabListener(tabListener);
        actionBar.addTab (tab);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                getSupportActionBar().setSelectedNavigationItem(position);

            }
        });

        drawerLayout=(DrawerLayout) findViewById(R.id.cp);
        lista=(ListView) findViewById(R.id.menuiz);
        lista.setAdapter(new ArrayAdapter<String>(getSupportActionBar().getThemedContext(),
                android.R.layout.simple_list_item_1,opciones));

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0: Intent intent=new Intent(Productos.this,MainActivity.class);
                        startActivity(intent);
                        break;
                    case 1: Intent intent2=new Intent(Productos.this,Miperfil.class);
                        startActivity(intent2);
                        break;
                    case 2: //Intent intent3=new Intent(Productos.this,Productos.class);
                        //startActivity(intent3);
                        break;
                    case 3:
                        SharedPreferences prefs = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("registrar","si");
                        editor.commit();

                        Intent intent4=new Intent(Productos.this,Loggin.class);
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

public class PagerAdapter extends FragmentPagerAdapter {
    public PagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Pollo_fragment();
            case 1:
                return new sandwiches_fragment();
            case 2:
                return new Refrescos_fragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case (R.id.Miperfil):
                Intent intent= new Intent(this,Miperfil.class);
                intent.putExtra("nombre",nombre);
                intent.putExtra("contraseña",contraseña);
                intent.putExtra("correo",correo);
                finish();
                startActivity(intent);
            break;
            case(R.id.MainActivity):
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.LEFT);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }
}
