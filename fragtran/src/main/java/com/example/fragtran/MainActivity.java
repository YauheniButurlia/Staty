package com.example.fragtran;

import android.graphics.drawable.Animatable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

  private Animatable icon;
  private boolean clicked = false;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    final FragmentOne fragmentOne = new FragmentOne();
    final FragmentTwo fragmentTwo = new FragmentTwo(); //why should I use *final* here???

    //FrameLayout root = findViewById(R.id.root);
    final Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    toolbar.setNavigationIcon(android.R.drawable.arrow_down_float);
    toolbar.setNavigationIcon(R.drawable.avd_pathmorph_drawer_hamburger_to_arrow);
    icon = (Animatable) toolbar.getNavigationIcon();

    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.root, fragmentTwo)
                .addToBackStack(null)
                .commit();
      }
    });


    getSupportFragmentManager().beginTransaction()
            .add(R.id.root, fragmentOne)
            .commit();
  }
}
