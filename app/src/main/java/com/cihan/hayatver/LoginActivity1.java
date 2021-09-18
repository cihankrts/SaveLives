package com.cihan.hayatver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import customfonts.Button_Poppins_Regular;
import customfonts.EditText_Poppins_Regular;
import customfonts.MyTextView_Poppins_SemiBold;

public class LoginActivity1 extends AppCompatActivity {

    private EditText_Poppins_Regular edtmName;
    private EditText_Poppins_Regular edtmTelefon;
    private EditText_Poppins_Regular edtmCity;
    private EditText_Poppins_Regular edtmDistrict;
    private MyTextView_Poppins_SemiBold btnmKayitOl;

    private Spinner bloods;
    private Spinner genders;
    private Spinner onay;

    FirebaseDatabase mrootNode;
    DatabaseReference mreference;

    public void init(){

        edtmName = (EditText_Poppins_Regular) findViewById(R.id.edtmName);
        edtmTelefon = (EditText_Poppins_Regular) findViewById(R.id.edtmPhone);
        edtmCity = (EditText_Poppins_Regular) findViewById(R.id.edtmCity);
        edtmDistrict = (EditText_Poppins_Regular) findViewById(R.id.edtmDistrict);
        btnmKayitOl = (MyTextView_Poppins_SemiBold) findViewById(R.id.btnmKayıtOl);

        bloods = (Spinner) findViewById(R.id.spinnerBloods);
        ArrayAdapter<CharSequence> adapterBloods = ArrayAdapter.createFromResource(this,
                R.array.Bloods,
                R.layout.spinner_bloods);
        adapterBloods.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloods.setAdapter(adapterBloods);

        genders = (Spinner) findViewById(R.id.spinnerGender);
        ArrayAdapter<CharSequence> adapterGenders = ArrayAdapter.createFromResource(this,
                R.array.Gender,
                R.layout.spinner_bloods);
        adapterGenders.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genders.setAdapter(adapterGenders);

        onay = (Spinner) findViewById(R.id.spinnerOnay);
        ArrayAdapter<CharSequence> adapterOnay = ArrayAdapter.createFromResource(this,
                R.array.Onay,
                R.layout.spinner_bloods);
        adapterGenders.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        onay.setAdapter(adapterOnay);



        mrootNode = FirebaseDatabase.getInstance();
        mreference = mrootNode.getReference();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        init();

        btnmKayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KayitOlustur();
                edtmName.setText("");
                edtmTelefon.setText("");
                edtmCity.setText("");
                edtmDistrict.setText("");

                Intent intent = new Intent(LoginActivity1.this, KanAriyanlar.class);
                startActivity(intent);
            }
        });





    }

    private void KayitOlustur() {

        String Name= edtmName.getText().toString();
        String Phone = edtmTelefon.getText().toString();
        String City = edtmCity.getText().toString();
        String District = edtmDistrict.getText().toString();
        String bloodType = bloods.getSelectedItem().toString();
        String gender = genders.getSelectedItem().toString();
        String Onay = onay.getSelectedItem().toString();

        KanVerenHelperClass kanVerenHelperClass = new KanVerenHelperClass(Name,Phone,City,
                District,bloodType,gender,Onay);
        mreference.child("Donor").child(Phone).setValue(kanVerenHelperClass);

        Toast.makeText(LoginActivity1.this,"Kaydınız oluşmuştur.",Toast.LENGTH_LONG).show();


    }
}