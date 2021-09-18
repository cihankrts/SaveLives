package com.cihan.hayatver;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private DonorsAdapter mDonorAdapter;
    private String getPhone;

    public void setConfig(RecyclerView recyclerView, Context context, List<Donor> donors,
                          List<String> keys){
        mContext = context;
        mDonorAdapter = new DonorsAdapter(donors,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mDonorAdapter);


    }


    class DonorItemView extends RecyclerView.ViewHolder{

        private TextView bloodType;
        private TextView city;
        private TextView district;
        private TextView gender;
        private TextView name;
        private TextView onay;
        private Button btnMainSendMessage;
        private TextView phone;



        private String telefon;

        private String key;

        public DonorItemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.kan_ariyan_arayuz,parent,false));

            bloodType = (TextView) itemView.findViewById(R.id.txtBloodType);
            gender = (TextView) itemView.findViewById(R.id.txtGender);
            onay = (TextView) itemView.findViewById(R.id.txtOnayDurumu);
            city = (TextView) itemView.findViewById(R.id.txtSehir);
            district = (TextView) itemView.findViewById(R.id.txtIlce);
            name = (TextView) itemView.findViewById(R.id.txtName);
            btnMainSendMessage = (Button) itemView.findViewById(R.id.btnMainSendMessage);
            phone = (TextView) itemView.findViewById(R.id.txtPhone);

           btnMainSendMessage.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   getPhone = phone.getText().toString();
                   Intent intent = new Intent(mContext,ChatActivity.class);
                   intent.putExtra("othertelefon",getPhone);

                   mContext.startActivity(intent);
               }
           });



        }


        public void bind(Donor donor, String key){
            name.setText(donor.getName());
            telefon = donor.getPhone();
            city.setText(donor.getCity());
            district.setText(donor.getDistrict());
            bloodType.setText(donor.getBloodType());
            gender.setText(donor.getGender());
            onay.setText(donor.getOnay());
            phone.setText(donor.getPhone());

            this.key  = key;
        }
    }

    class DonorsAdapter extends RecyclerView.Adapter<DonorItemView>{

        private List<Donor> mDonorList;
        private List<String> mKeys;

        public DonorsAdapter(List<Donor> mDonorList, List<String> mKeys) {
            this.mDonorList = mDonorList;
            this.mKeys = mKeys;
        }

        public DonorsAdapter() {
            super();
        }

        @NonNull
        @Override
        public DonorItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new DonorItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull DonorItemView holder, int position) {
            holder.bind(mDonorList.get(position),mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mDonorList.size();
        }
    }

}
