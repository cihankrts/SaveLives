package com.cihan.hayatver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class KanVerenKayit extends AppCompatActivity {


    private EditText name;
    private EditText phone;
    private EditText city;
    private EditText district;
    private Spinner bloods;
    private Spinner genders;
    private Spinner onay;
    private Button btnKayit;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    public void init(){

        name = (EditText) findViewById(R.id.edtName);
        phone = (EditText) findViewById(R.id.edtPhone);
        city = (EditText) findViewById(R.id.edtCity);
        district = (EditText) findViewById(R.id.edtDistrict);
        btnKayit = (Button) findViewById(R.id.btnKayitOlustur);

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



        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference();

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kan_veren_kayit);
        init();

        btnKayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 KayitOlustur();
                 name.setText("");
                 phone.setText("");
                 city.setText("");
                 district.setText("");

                 Intent intent = new Intent(KanVerenKayit.this, KanAriyanlar.class);
                 startActivity(intent);
            }
        });


    }

    private void KayitOlustur() {

        String Name= name.getText().toString();
        String Phone = phone.getText().toString();
        String City = city.getText().toString();
        String District = district.getText().toString();
        String bloodType = bloods.getSelectedItem().toString();
        String gender = genders.getSelectedItem().toString();
        String Onay = onay.getSelectedItem().toString();

        KanVerenHelperClass kanVerenHelperClass = new KanVerenHelperClass(Name,Phone,City,
                District,bloodType,gender,Onay);
        reference.child("Donor").child(Phone).setValue(kanVerenHelperClass);

        Toast.makeText(KanVerenKayit.this,"Kaydınız oluşmuştur.",Toast.LENGTH_LONG).show();


    }
}