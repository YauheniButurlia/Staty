package com.example.acttran;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterView extends RecyclerView.Adapter<AdapterView.ViewHolder> {

  private Integer[] imageIDs;
  private String[] text;
  private View.OnClickListener listener;
  private Bitmap[] bitmaps;

  public AdapterView(Bitmap[] bitmaps, String[] text, View.OnClickListener listener){
    this.bitmaps = bitmaps;
    this.text = text;
    this.listener = listener;
  }

  @NonNull
  @Override
  public AdapterView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemview, viewGroup, false);
    view.setOnClickListener(listener);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull AdapterView.ViewHolder viewHolder, int i) {
    viewHolder.textView.setText(text[i]);
    viewHolder.imageView.setImageBitmap(bitmaps[i]);//imageIDs[i]
  }

  @Override
  public int getItemCount() {
    return bitmaps.length;
  }

  public class ViewHolder extends RecyclerView.ViewHolder{

    TextView textView;
    ImageView imageView;
    int pos;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      textView = (TextView) itemView.findViewById(R.id.tv);
      imageView = (ImageView) itemView.findViewById(R.id.iv);
    }
  }
}
