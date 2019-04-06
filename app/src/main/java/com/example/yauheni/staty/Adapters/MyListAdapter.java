package com.example.yauheni.staty.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.yauheni.staty.Views.ListItemView;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
  private int amount;

  public MyListAdapter(int itemAmount){
    this.amount = itemAmount;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    ListItemView item = new ListItemView(viewGroup.getContext());
    return new ViewHolder(item);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

  }

  @Override
  public int getItemCount() {
    return amount;
  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    private ListItemView view;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      view = (ListItemView) itemView;
    }
  }
}
