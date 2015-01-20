package com.example.izv.imagenes;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class Principal extends Activity {

    private GridView gv;
    private ArrayList<String> rutas;
    Adaptador ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);
        iniciarComponentes();
    }

    public void iniciarComponentes(){

        rutas=new ArrayList<String>();
        Uri ur= MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] proyeccion = null;
        String condicion = null;
        String[] parametros = null;
        String orden = null;
        Cursor cursor=getContentResolver().query(
                ur,
                proyeccion,
                condicion,
                parametros,
                orden);
        int nc=cursor.getColumnCount();
        for (int i=0;i<nc;i++){
            String nombre= cursor.getColumnName(i);

        }cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String nombre = cursor.getString(1);
            rutas.add(nombre);
            cursor.moveToNext();
        }
        ad = new Adaptador(this,R.layout.item,rutas);
        gv = (GridView)findViewById(R.id.gv);
        gv.setAdapter(ad);
        registerForContextMenu(gv);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String ruta=rutas.get(i);
                Intent in = new Intent(Principal.this, VerFoto.class);
                in.putExtra("ruta", ruta);
                startActivity(in);
            }
        });
    }
}
