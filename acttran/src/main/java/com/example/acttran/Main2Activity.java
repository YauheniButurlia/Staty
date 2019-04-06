package com.example.acttran;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {


  private String[] texts;
  private Integer[] imageIDs;

  private int id;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main2);
    Window window = getWindow();
    window.setEnterTransition(new Fade());
    window.setExitTransition(new Slide());

    id = getIntent().getIntExtra(MainActivity.KEY_IMAGE,0);
    setData();

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    //toolbar.setLogo(imageIDs[id]);
    toolbar.setTitle("");
    setSupportActionBar(toolbar);

    ImageView imageView = findViewById(R.id.image);
    imageView.setImageResource(imageIDs[id]);

    ImageView imageIcon = findViewById(R.id.imageIcon);
    imageIcon.setImageResource(R.mipmap.ic_launcher_round);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_scrolling, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
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
}

