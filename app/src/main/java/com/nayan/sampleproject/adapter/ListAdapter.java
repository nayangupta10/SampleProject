package com.nayan.sampleproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nayan.sampleproject.R;
import com.nayan.sampleproject.model.Doc;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private Context context;
    List<Doc> docArrayList;

    public ListAdapter(Context context, ArrayList<Doc> docArrayList){
        this.context=context;
       this.docArrayList=docArrayList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Doc doc=docArrayList.get(position);
        holder.tvId.setText("Id: "+doc.getId());
        holder.tvPublicationDate.setText("Publication Date: "+doc.getPublicationDate());
        holder.tvArticleType.setText("Article Type: "+doc.getArticleType());

        holder.recyclerViewAbstract.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerViewAbstract.setHasFixedSize(true);
        AbstractListAdapter abstractListAdapter=new AbstractListAdapter(context,docArrayList.get(position).getAbstract());
        holder.recyclerViewAbstract.setAdapter(abstractListAdapter);

    }

    @Override
    public int getItemCount() {
        return docArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvId,tvPublicationDate,tvArticleType;
        RecyclerView recyclerViewAbstract;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId=itemView.findViewById(R.id.id_tv);
            tvPublicationDate= itemView.findViewById(R.id.publication_date_tv);
            tvArticleType=itemView.findViewById(R.id.article_type_tv);
            recyclerViewAbstract=itemView.findViewById(R.id.abstract_recycler_view);
        }
    }
}
