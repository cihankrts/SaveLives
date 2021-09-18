package com.cihan.hayatver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    private String userPhone,otherPhone;

    private TextView mchatUserName;

    private ImageView backImage, sendImage;
    private EditText chatEditText;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String message;
    private RecyclerView chatRecyView;
    private MessageAdapter messageAdapter;
    private  List<MessageModel> list;


    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            userPhone = intent.getStringExtra("userTelefon");
        }
    };

    public void init(){

        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, new IntentFilter(
                "User_Intent"));
        
        Intent intent1= getIntent();
        Intent intent3= getIntent();
        Bundle bundleOther = intent3.getExtras();
         Bundle bundleUser = intent1.getExtras();
         if(bundleUser!=null) {
             userPhone = intent1.getStringExtra("userTELEFON");
         }
       //  else if (bundleOther!=null){
        //   otherPhone = intent3.getStringExtra("othertelefon");
      // }
           otherPhone = "05388537522";

       // otherPhone="1";


        mchatUserName = (TextView) findViewById(R.id.chatUserName);
        backImage = (ImageView) findViewById(R.id.backImage);
        sendImage = (ImageView) findViewById(R.id.sendImage);
        chatEditText = (EditText) findViewById(R.id.chatEditText);

        chatRecyView = (RecyclerView) findViewById(R.id.chatRecyView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ChatActivity.this,1);
        chatRecyView.setLayoutManager(layoutManager);

        list = new ArrayList<>();

        messageAdapter = new MessageAdapter(ChatActivity.this,list,ChatActivity.this,userPhone);
        chatRecyView.setAdapter(messageAdapter);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        mchatUserName.setText(otherPhone);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatActivity.this, KanAriyanlar.class);
                intent.putExtra("telefon",userPhone);
                startActivity(intent);
            }
        });

        sendImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = chatEditText.getText().toString();
                chatEditText.setText("");
                mesajGonder(message);
            }
        });

    }


    public void mesajGonder(String text){

        String strUserPhone = String.valueOf(userPhone);
        String strOtherPhone = String.valueOf(otherPhone);


        final String key =
                databaseReference.child("Mesajlar").child(strUserPhone).child(strOtherPhone).push().getKey();
        final Map messageMap = new HashMap();
        messageMap.put("text",text);
        messageMap.put("from", userPhone);
        databaseReference.child("Mesajlar").child(strUserPhone).child(strOtherPhone).child(key).setValue(messageMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    databaseReference.child("Mesajlar").child(strOtherPhone).child(strUserPhone).child(key).setValue(messageMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                }
            }
        });

    }

    public void loadMessage(){

        String strUserPhone = String.valueOf(userPhone);
        String strOtherPhone = String.valueOf(otherPhone);


        databaseReference.child("Mesajlar").child(strUserPhone).child(strOtherPhone).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                MessageModel messageModel = dataSnapshot.getValue(MessageModel.class);
                list.add(messageModel);
                messageAdapter.notifyDataSetChanged();
                chatRecyView.scrollToPosition(list.size()-1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        init();
        loadMessage();
    }
}