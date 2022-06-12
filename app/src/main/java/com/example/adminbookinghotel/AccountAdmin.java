package com.example.adminbookinghotel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AccountAdmin extends SideBar {

    private RecyclerView rcvAccountAdmin;
    private AccountAdapter accountAdapter;
    private List<UserAdmin> listCustomer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.activity_accout_admin, null, false);
        mDraweLayout.addView(v, 0);


        getListAdminFromRealtimeDataBase();
        initUi();

    }

    private void getListAdminFromRealtimeDataBase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("admin");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                listCustomer.clear();
                for(DataSnapshot child : snapshot.getChildren()){
                    UserAdmin userAdmin = child.getValue(UserAdmin.class);
                    listCustomer.add(userAdmin);
                }
                accountAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                showToast("Get list Room Failed");
            }
        });
    }

    private void showToast(String mess) {
        Toast.makeText(this,mess,Toast.LENGTH_SHORT);
    }

    private void initUi() {
        listCustomer = new ArrayList<>();
        rcvAccountAdmin = findViewById(R.id.rcv_account);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvAccountAdmin.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvAccountAdmin.addItemDecoration(dividerItemDecoration);


        accountAdapter = new AccountAdapter(listCustomer,this);
        rcvAccountAdmin.setAdapter(accountAdapter);

    }
}