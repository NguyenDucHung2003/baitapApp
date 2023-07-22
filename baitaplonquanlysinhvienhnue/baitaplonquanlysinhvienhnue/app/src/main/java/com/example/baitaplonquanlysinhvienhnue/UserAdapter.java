package com.example.baitaplonquanlysinhvienhnue;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{
    private List<User> mListUsers;

    public UserAdapter(List<User> mListUsers) {
        this.mListUsers = mListUsers;
    }
    public void setFilteredList(List<User> filterdList){
        this.mListUsers=filterdList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user=mListUsers.get(position);
        if(user==null){
            return;
        }
        holder.imgUser.setImageResource((user.getImage()));
        holder.StudentID.setText(user.getStudentID());
        holder.fullName.setText(user.getFullName());
        holder.className.setText(user.getClassName());
        holder.date.setText(user.getDate());
    }

    @Override
    public int getItemCount() {
        if(mListUsers!=null)
        {
            return mListUsers.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView imgUser;
        private TextView StudentID;
        private TextView fullName;
        private TextView className;
        private TextView date;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUser=itemView.findViewById(R.id.imng_user);
            StudentID=itemView.findViewById(R.id.StudentID);
            fullName=itemView.findViewById(R.id.fullName);
            className=itemView.findViewById(R.id.className);
            date=itemView.findViewById(R.id.date);
        }
    }
}
