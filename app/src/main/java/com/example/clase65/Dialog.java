package com.example.clase65;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class Dialog extends DialogFragment {
    NoticeDialogListener listener;
    Boolean estaEditando;
    Comentario datitosCalentitos;
    int idTemp = 0;

    public Dialog(boolean edita,Comentario c){
        estaEditando = edita;
        if(c != null){
            datitosCalentitos = c;
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (NoticeDialogListener) context;
        }catch(ClassCastException e){
            throw new ClassCastException(getActivity().toString());
        }

    }

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder b = new AlertDialog.Builder(getContext());
        LayoutInflater f = getActivity().getLayoutInflater();
        View v = f.inflate(R.layout.dialog,null);
        b.setView(v);

        EditText eTitulo = v.findViewById(R.id.editTitulo);
        EditText eNombre = v.findViewById(R.id.editNombre);
        EditText eTexto = v.findViewById(R.id.editTexto);
        String nameBotonAceptar;
        idTemp = 0;

        if(datitosCalentitos != null){
            eTitulo.setText(datitosCalentitos.Titulo);
            eTexto.setText(datitosCalentitos.Texto);
            eNombre.setText(datitosCalentitos.Nombre);
            idTemp = datitosCalentitos.ID;
        }

        if(estaEditando){
            nameBotonAceptar = "Editar";
        }else{
            nameBotonAceptar = "Comentar";
        }

        b.setPositiveButton(nameBotonAceptar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.aceptarClick(eTitulo.getText().toString(),eNombre.getText().toString(),eTexto.getText().toString(),idTemp);
            }
        });

        b.setTitle("Titulo xd");

        return b.create();
    }

    public interface NoticeDialogListener{
        public void aceptarClick(String titulo, String nombre, String texto, int id);
    }
}
