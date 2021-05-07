package com.example.clase65;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements Dialog.NoticeDialogListener {
    private RecyclerView r;
    public static AdapterComentario ad;
    ArrayList<Comentario> lista = new ArrayList<Comentario>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        this.rellenarLista();

        r = findViewById(R.id.rvComentario);

        LinearLayoutManager l = new LinearLayoutManager(this);

        r.setLayoutManager(l);

        ad = new AdapterComentario(this.lista,this);

        r.setAdapter(ad);

    }

    public void floatingActionButtonAdd(View v){
            DialogFragment dial = new Dialog(false,null);
            dial.show(getSupportFragmentManager(),"Datos");
        /*
        Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        //Toast.makeText(this,"asd",Toast.LENGTH_SHORT);
        lista.add(new Comentario("asdasd","asdasdasdasd","asdasdasdasdasdasd asd asd as dasdas d sad",0,GenerarID()));
        ad = new AdapterComentario(this.lista,this);
        r.setAdapter(ad);
        */
    }

    private void rellenarLista(){
        lista.add(new Comentario("Alex","Blabla","blablabka blablablabla blablaba",0,GenerarID()));
    }

    public void RemoveUpdate(ArrayList<Comentario> listita){
        lista = listita;
        ad = new AdapterComentario(lista,this);
        r.setAdapter(ad);
    }

    public int GenerarID(){
        int idGenerado;
        boolean repite = true;
        System.out.println( "Generando Random: " + lista.size());
        if(lista.size() > 0){
            System.out.println("randommmmmmmmmmmm");
            do{
                idGenerado = (int) (Math.random() * 1000 + 1);
                repite = false;
                for (Comentario c: lista){
                    if(c.ID != idGenerado) repite = true;
                }
            }while(repite == false);
        }else{
            idGenerado = (int) (Math.random() * 1000 + 1);
        }

        System.out.println("ID GENERADO: " + idGenerado);
        return idGenerado;
    }

    @Override
    public void aceptarClick(String titulo, String nombre, String texto, int id) {
        if(id == 0){
            lista.add(new Comentario(nombre,titulo,texto,0,GenerarID()));
            ad = new AdapterComentario(this.lista,this);
            r.setAdapter(ad);
        }else{
            for (Comentario c: lista){
                if(c.ID == id){
                    lista.set(lista.indexOf(c),new Comentario(nombre,titulo,texto,0,GenerarID()));
                    ad = new AdapterComentario(this.lista,this);
                    r.setAdapter(ad);
                }
            }
        }

    }
}