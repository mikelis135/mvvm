package com.example.mvvm.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm.R;
import com.example.mvvm.model.NicePlace;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private static final String TAG = "MainAdapter";
    private List<NicePlace> nicePlaces;
    private Context context;

    public MainAdapter(Context context, List<NicePlace> nicePlaces) {
        this.context = context;
        this.nicePlaces = nicePlaces;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "MainAdapter onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "MainAdapter: " + nicePlaces.get(position).getName() + " " + position);
        holder.name.setText(nicePlaces.get(position).getName());
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: " + nicePlaces.get(position).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return nicePlaces.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item);
        }
    }

}
