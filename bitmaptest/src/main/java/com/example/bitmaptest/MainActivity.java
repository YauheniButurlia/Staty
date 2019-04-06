package com.example.bitmaptest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

  private ImageView imageView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    imageView = findViewById(R.id.image);

    findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Bitmap bitmap = Bitmap.createBitmap(200,200, Bitmap.Config.RGB_565);
        bitmap.eraseColor(Color.BLUE);
        bitmap.setPixel(100,100,Color.YELLOW);
        imageView.setImageBitmap(bitmap);

      }
    });
  }
}
