package com.nayan.sampleproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nayan.sampleproject.R;
import com.nayan.sampleproject.model.Doc;

import java.util.List;

public class AbstractListAdapter extends RecyclerView.Adapter<AbstractListAdapter.ViewHolder> {
    List<String> list;
    Context context;

    public AbstractListAdapter(Context context, List<String> list){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.abstract_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvAbstract.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvAbstract;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAbstract=itemView.findViewById(R.id.abstract_tv);
        }
    }
}
