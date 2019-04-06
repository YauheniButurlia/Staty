package com.example.acttran;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.GridLayoutManager;
import android.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  private String[] texts;
  private Integer[] imageIDs;
  private Bitmap[] bitmaps;

  private RecyclerView recyclerView;
  private AdapterView adapterView;
  private RecyclerView.LayoutManager manager;

  public static String KEY_IMAGE = "image";
  public static String KEY_TEXT = "text";

  //private int COUNT = 10;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    setData();
    bitmaps = new Bitmap[imageIDs.length];
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inSampleSize = 4;
    for(int i = 0; i < imageIDs.length;i++) {
      Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageIDs[i], options);
      bitmaps[i] = bitmap;
    }

    adapterView = new AdapterView(bitmaps,texts, this);
    manager = new LinearLayoutManager(this);//new GridLayoutManager(this,2);

    recyclerView = findViewById(R.id.rv);
    recyclerView.setAdapter(adapterView);
    recyclerView.setLayoutManager(manager);
    //recyclerView.setBackgroundResource(R.drawable.thanosback);
  }

  private void setData() {
    imageIDs = new Integer[]{
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g,
            R.drawable.h,
            R.drawable.i,
            R.drawable.j};

    texts = new String[]{
            "Margene",
            "Thelma",
            "Latoya",
            "Ela",
            "Indira",
            "Rutha",
            "Garth",
            "Julius",
            "Shena",
            "Julieann"
    };
  }

  @Override
  public void onClick(View v) {
    Intent intent = new Intent(MainActivity.this, Main2Activity.class);

    AdapterView.ViewHolder viewHolder = (AdapterView.ViewHolder) recyclerView.getChildViewHolder(v);
    Pair<View, String> pair1 =  new Pair<View, String>(viewHolder.imageView,"imageView");
    Pair<View, String> pair2 =  new Pair<View, String>(viewHolder.textView,"textView");

    Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this, pair1, pair2).toBundle();
    intent.putExtra(KEY_IMAGE, manager.getPosition(v));
    intent.putExtra(KEY_TEXT, manager.getPosition(v));

    startActivity(intent,bundle);
  }
}
