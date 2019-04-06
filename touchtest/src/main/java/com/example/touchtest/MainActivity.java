package com.example.touchtest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    ImageView imageView = ((ImageView)findViewById(R.id.image));

    Picasso.get()
            .load(R.drawable.back)
            .resize(1080, 1920)
            .centerCrop()
            .into(imageView);


    /*
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.back);
    bitmap.setConfig(Bitmap.Config.RGB_565);
    String info = String.format("%s %s %s",bitmap.getWidth(), bitmap.getHeight(), bitmap.getByteCount());
    Log.d("myLog", info);
    imageView.setImageBitmap(bitmap);
    Log.d("myLog",String.valueOf(((ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass()));
*/

  }
}
