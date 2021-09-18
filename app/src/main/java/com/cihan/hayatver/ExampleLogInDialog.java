package com.cihan.hayatver;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import customfonts.EditText_Poppins_Regular;

public class ExampleLogInDialog extends AppCompatDialogFragment {

    public String stringSharedPreferences;

    private EditText_Poppins_Regular mTelefon;
    private ExampleLoginDialogListener listener;
    private String userTelefon;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_log_in_dialog,null);

        builder.setView(view).setTitle("HAYAT VER")
                .setNegativeButton("Geri", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Giriş Yap", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      userTelefon = mTelefon.getText().toString();
                      listener.applytext(userTelefon);

                    //  Intent intent2 = new Intent(view.getContext(),ChatActivity.class);
                    //  intent2.putExtra("usertelefon",telefon);

                        Intent intent3 = new Intent("User_Intent").putExtra("userTelefon",
                                userTelefon);
                        LocalBroadcastManager.getInstance(view.getContext()).sendBroadcast(intent3);
                        

                        Intent intent = new Intent(view.getContext(), KanAriyanlar.class);
                        intent.putExtra("userTelefon",userTelefon);
                        startActivity(intent);
                    }
                });
        mTelefon = view.findViewById(R.id.edtmTelefon);
        return builder.create();
        
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (ExampleLoginDialogListener) context;
        } catch (ClassCastException e) {
           throw new ClassCastException(context.toString() + "must implement " +
                   "ExampleLoginDialogListener");
        }
    }

    public interface  ExampleLoginDialogListener{

           void applytext(String phone);
    }

    
}
