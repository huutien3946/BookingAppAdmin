package com.example.adminbookinghotel;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeAdmin extends SideBar {

    private RecyclerView rcvRoom;
    private RoomAdapter mRoomAdapter;
    private List<Room> mListRoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.activity_home_admin, null, false);
        mDraweLayout.addView(v, 0);

        initUI();
        getListRoomFromRealtimeDataBase();
    }

    private void initUI() {


        rcvRoom = findViewById(R.id.rcv_room);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvRoom.setLayoutManager(linearLayoutManager);

        //phan cach giua cac item
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvRoom.addItemDecoration(dividerItemDecoration);

        mListRoom = new ArrayList<>();
        mRoomAdapter = new RoomAdapter(this,mListRoom);

        rcvRoom.setAdapter(mRoomAdapter);

    }

    private void getListRoomFromRealtimeDataBase(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("hotel");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                mListRoom.clear();
                for(DataSnapshot child : snapshot.getChildren()){
                    Room room = child.getValue(Room.class);
                    mListRoom.add(room);
                }
                mRoomAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                showToast("Get list Room Failed");
            }
        });

    }
    private void showToast(String mess){
        Toast.makeText(this,mess,Toast.LENGTH_LONG);
    }

}