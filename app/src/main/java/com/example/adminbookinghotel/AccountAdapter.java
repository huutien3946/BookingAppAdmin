package com.example.adminbookinghotel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {

    private List<UserAdmin> list;
    private Context context;

    public AccountAdapter(List<UserAdmin> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override

    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_account, parent, false);
        return new AccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position) {

        UserAdmin userAdmin = list.get(position);
        if (userAdmin == null) {
            return;
        }

        holder.email.setText(userAdmin.getEmail());
        holder.phone.setText(userAdmin.getPhone());
        holder.name.setText(userAdmin.getName());

    }

    @Override
    public int getItemCount() {
        if(list.size() != 0)
            return list.size();
        return 0;
    }

    public class AccountViewHolder extends RecyclerView.ViewHolder {

        private TextView email;
        private TextView phone;
        private TextView name;

        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.tv_email_account);
            phone = itemView.findViewById(R.id.tv_phone_account);
            name = itemView.findViewById(R.id.tv_name_account);
        }


    }
}

