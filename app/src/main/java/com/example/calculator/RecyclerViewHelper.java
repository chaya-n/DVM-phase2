package com.example.calculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewHelper extends RecyclerView.Adapter<RecyclerViewHelper.MyViewHolder> {

    Context context;
    List<String> l;

    public RecyclerViewHelper(Context context, List<String> l){
        this.context=context;
        this.l=l;
    }

    @NonNull
    @Override
    public RecyclerViewHelper.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row,parent,false);
        return new RecyclerViewHelper.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHelper.MyViewHolder holder, int position) {
        holder.textView.setText(l.get(position));
    }

    @Override
    public int getItemCount() {
        return l.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.textView);
        }
    }
}
