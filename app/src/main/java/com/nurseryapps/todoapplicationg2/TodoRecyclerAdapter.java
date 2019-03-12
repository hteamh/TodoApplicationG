package com.nurseryapps.todoapplicationg2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nurseryapps.todoapplicationg2.BataBase.Model.Todo;

import java.util.List;

/**
 * Created by Mohamed Nabil Mohamed (Nobel) on 1/14/2019.
 * byte code SA
 * m.nabil.fci2015@gmail.com
 */
public class TodoRecyclerAdapter extends
        RecyclerView.Adapter<TodoRecyclerAdapter.ViewHolder> {

    List<Todo> items;//data source
    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public Todo getSwipedItem(int pos){
        return items.get(pos);
    }

    public interface OnItemClickListener{
        void onItemClick(int pos,Todo item);
    }

    public TodoRecyclerAdapter(List<Todo> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int itemType) {
       View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_card_item,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {

        final Todo todo = items.get(position);
        viewHolder.title.setText(todo.getTitle());
        viewHolder.date.setText(todo.getDateTime());
        if(onItemClickListener!=null){
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(position,todo);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(items==null)return 0;
        return items.size();
    }

    public void ChangeData(List<Todo> items){
        this.items = items;
        notifyDataSetChanged();
    }
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView date;

        public ViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.title);
            date = view.findViewById(R.id.date);

        }
    }


}
