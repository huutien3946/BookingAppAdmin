package com.example.adminbookinghotel.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apachat.swipereveallayout.core.SwipeLayout;
import com.example.adminbookinghotel.Model.Category;
import com.example.adminbookinghotel.Model.TypeRoom;
import com.example.adminbookinghotel.Model.UserAdmin;
import com.example.adminbookinghotel.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ListTypeRoomAdapter extends RecyclerView.Adapter<ListTypeRoomAdapter.ListTypeRoomViewHolder>{

    private List<TypeRoom> list;
    private Context context;

    public ListTypeRoomAdapter(List<TypeRoom> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ListTypeRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type_room, parent, false);
        return new ListTypeRoomAdapter.ListTypeRoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListTypeRoomViewHolder holder, int position) {

        TypeRoom typeRoom = list.get(position);
        if (typeRoom == null) {
            return;
        }

        holder.type.setText(typeRoom.getType());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("typeroom");
                reference.addListenerForSingleValueEvent(new ValueEventListener(){
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot child : snapshot.getChildren()) {
                            TypeRoom typeRoom1 = child.getValue(TypeRoom.class);
                            if (typeRoom1.getType().equals(typeRoom.getType())) {
                                reference.child(child.getKey()).removeValue();
                                Toast.makeText(v.getContext(), "Delete is successful", Toast.LENGTH_SHORT).show();
                                break;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(v.getContext(), "Warning!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        if(list.size() != 0)
            return list.size();
        return 0;
    }

    public class ListTypeRoomViewHolder extends RecyclerView.ViewHolder {

        private TextView type;
        private TextView delete, edit;
        private SwipeLayout swipeLayout;


        public ListTypeRoomViewHolder(@NonNull View itemView) {
            super(itemView);
            swipeLayout = itemView.findViewById(R.id.swipeTypeRoom);
            type = itemView.findViewById(R.id.tv_item_type_room);
            delete = itemView.findViewById(R.id.delete_type_room);
            edit = itemView.findViewById(R.id.update_typeroom);

        }
    }
}
