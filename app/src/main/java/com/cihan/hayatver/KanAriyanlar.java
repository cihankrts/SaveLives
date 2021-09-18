package com.cihan.hayatver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class KanAriyanlar extends AppCompatActivity {

    private Button btnMainSendMessage;


    private ActionBarDrawerToggle toggle;
    private RecyclerView mRecyclerView;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private String  mLoginPhone;
   // private String mDonorPhone;

    private TextView mDonorName;
    private TextView mDonorCity;
    private TextView mDonorDistrict;
    private TextView mDonorPhone;

    private String name;
    private String city;
    private String district;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openLogOutDialog(){
        LogOutDialog logOutDialog = new LogOutDialog();
        logOutDialog.show(getSupportFragmentManager(), "phone logout");


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kan_ariyanlar);


       

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navView = (NavigationView) findViewById(R.id.navView);

        View linearLayout=navView.inflateHeaderView(R.layout.nav_header);




        mDonorName = linearLayout.findViewById(R.id.txtDonorName);
        mDonorCity = linearLayout.findViewById(R.id.txtDonorCity);
        mDonorDistrict = linearLayout.findViewById(R.id.txtDonorDistrict);
        mDonorPhone = linearLayout.findViewById(R.id.txtDonorPhone);

       // mIntDonorPhone = Integer.parseInt(mDonorPhone.toString());

        Intent intent = getIntent();
        mLoginPhone = intent.getStringExtra("userTelefon");




        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Donor").child(mLoginPhone);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              name = snapshot.child("name").getValue().toString();
              city = snapshot.child("city").getValue().toString();
              district = snapshot.child("district").getValue().toString();
                mDonorName.setText(name);
                mDonorCity.setText(city);
                mDonorDistrict.setText(district);
                mDonorPhone.setText(mLoginPhone);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






        toggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        mRecyclerView = (RecyclerView) findViewById(R.id.kanVerenRV);

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.nav_home)
                    Toast.makeText(KanAriyanlar.this,"anasayfa",Toast.LENGTH_LONG).show();
                else    if (item.getItemId()==R.id.nav_message) {
                    Toast.makeText(KanAriyanlar.this, "message", Toast.LENGTH_LONG).show();
                }
                else    if (item.getItemId()==R.id.nav_logout){
                    openLogOutDialog();
                }
                return true;
            }
        });


        new FirebaseDatabaseHelper().readDonors(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Donor> donors, List<String> keys) {
                 findViewById(R.id.loading_donor_pb).setVisibility(View.GONE);
                new RecyclerView_Config().setConfig(mRecyclerView,KanAriyanlar.this,donors,keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });

        


    }






}