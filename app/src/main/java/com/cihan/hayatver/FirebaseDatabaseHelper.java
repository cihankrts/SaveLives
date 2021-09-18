package com.cihan.hayatver;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceDonors;
    private List<Donor> donors = new ArrayList<>();

    public interface DataStatus{
        void DataIsLoaded(List<Donor> donors, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }


    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceDonors = mDatabase.getReference("Donor");
    }

    public void readDonors(final DataStatus dataStatus){
        mReferenceDonors.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                donors.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode: snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Donor donor = keyNode.getValue(Donor.class);
                    donors.add(donor);
                }
                dataStatus.DataIsLoaded(donors,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }







}
