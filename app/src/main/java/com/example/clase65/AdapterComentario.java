package com.example.clase65;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterComentario extends RecyclerView.Adapter<AdapterComentario.vhComentario> {
    static ArrayList<Comentario> listas;
    static MainActivity Ma;

    public AdapterComentario(ArrayList<Comentario> lista, MainActivity ma){
        this.listas = lista;
        this.Ma = ma;
    }

    @NonNull
    @Override
    public vhComentario onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.modelo,parent,false);
        return new AdapterComentario.vhComentario(v);
    }

    @Override
    public void onBindViewHolder(@NonNull vhComentario holder, int position) {
        Comentario c= this.listas.get(position);
        holder.cargarDatos(c);
        holder.setID(c.ID);
    }

    @Override
    public int getItemCount() {
        return this.listas.size();
    }

    public class vhComentario extends RecyclerView.ViewHolder{
        public ImageView Perfil;
        public TextView Nombre;
        public TextView Titulo;
        public TextView Texto;
        public ImageButton borrar;
        public ImageButton editar;
        int IDComentario = 0;

        public vhComentario(@NonNull View itemView) {
            super(itemView);

            Perfil = itemView.findViewById(R.id.imgPerfil);
            Nombre = itemView.findViewById(R.id.txtNombre);
            Titulo = itemView.findViewById(R.id.txtTitulo);
            Texto = itemView.findViewById(R.id.txtTexto);
            borrar = itemView.findViewById(R.id.btnBorrar);
            editar = itemView.findViewById(R.id.btnEditar);

            borrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (Comentario c: listas){
                        //if(c.Nombre == Nombre.getText()){
                        if(c.ID == IDComentario){
                            Toast.makeText(itemView.getContext(),"Borrado" + c.Nombre,Toast.LENGTH_SHORT).show();
                            System.out.println("ID de borrado:" + c.ID + ", Nombre:" + c.Nombre);
                            listas.remove(c);
                            //RecyclerView rv = itemView.findViewById(R.id.rvComentario);
                            Ma.RemoveUpdate(listas);
                            //MainActivity.ad = new AdapterComentario(listas);
                            //rv.setAdapter(MainActivity.ad);
                            break;
                        }
                    }
                }
            });

            editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (Comentario c: listas){
                        //if(c.Nombre == Nombre.getText()){
                        if(c.ID == IDComentario){
                            DialogFragment dial = new Dialog(true,c);
                            dial.show(((AppCompatActivity)Ma).getSupportFragmentManager(),"Datos");
                            /* antiguo editar de prueba
                            Toast.makeText(itemView.getContext(),"Editado" + c.Nombre,Toast.LENGTH_SHORT).show();
                            Comentario editc = new Comentario("bxbxbxbx","bxbxbx","bxbxbx",0,Ma.GenerarID());
                            listas.set(listas.indexOf(c),editc);
                            System.out.println("ID Editado:" + c.ID + ", Nombre:" + c.Nombre);
                            Ma.RemoveUpdate(listas);
                             */
                            break;
                        }
                    }
                }
            });

        }

        public void cargarDatos(Comentario c){
            Nombre.setText(c.Nombre);
            Titulo.setText(c.Titulo);
            Texto.setText(c.Texto);

            if(c.imgID != 0){
                Perfil.setImageResource(c.imgID);
            }else{
                Perfil.setImageResource(R.drawable.ic_launcher_foreground);
            }
        }

        public void setID(int id){
            IDComentario = id;
        }
    }
}
