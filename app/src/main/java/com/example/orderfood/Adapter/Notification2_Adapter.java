package com.example.orderfood.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.orderfood.Model.Notification_rcv;
import com.example.orderfood.R;

import java.util.ArrayList;

public class Notification2_Adapter extends RecyclerView.Adapter<Notification2_Adapter.NotificationRecyclerView2Holder> {

    private ArrayList<Notification_rcv> mListNotification;

    public Notification2_Adapter(ArrayList<Notification_rcv> mListNotification) {
        this.mListNotification = mListNotification;
    }

    @NonNull
    @Override
    public NotificationRecyclerView2Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_rcv2,parent, false);

        return new Notification2_Adapter.NotificationRecyclerView2Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationRecyclerView2Holder holder, int position) {

        Notification_rcv notification_rcv2 = mListNotification.get(position);
        if (notification_rcv2 == null){
            return;
        }

        holder.imageStore2.setImageResource(notification_rcv2.getImage());
        holder.nameStore2.setText(notification_rcv2.getNameStore());
        holder.content2.setText(notification_rcv2.getContent());


    }

    @Override
    public int getItemCount() {
        if(mListNotification != null){
            return mListNotification.size();
        }
        return 0;
    }

    public class NotificationRecyclerView2Holder extends RecyclerView.ViewHolder{

        private TextView nameStore2;
        private TextView content2;
        private ImageView imageStore2;

        public NotificationRecyclerView2Holder(@NonNull View itemView) {
            super(itemView);

            nameStore2 = itemView.findViewById(R.id.notification_nameStore2);
            content2 = itemView.findViewById(R.id.notification_content2);
            imageStore2 = itemView.findViewById(R.id.notification_store2);


        }
    }
}
