package com.example.yauheni.staty;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.yauheni.staty.Views.CircleView;
import com.example.yauheni.staty.Views.RingView;
import com.example.yauheni.staty.Views.TextAnimView;

public class ScrollingActivity extends AppCompatActivity{

  private ViewPager viewPager;
  private PagerAdapter pagerAdapter;
  private CircleView circleView;
  private TextAnimView textView;
  private RingView ringView;
  private String[] titles;

  private ColorAnimator colorAnimator;
  private int[] colors;

  private static final int PAGE_COUNT = 5;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    super.onCreate(savedInstanceState);
    //RingView ringView = new RingView(this);
    //setContentView(ringView);

    setContentView(R.layout.activity_scrolling);

    titles = new String[PAGE_COUNT];

    for (int i = 0; i < PAGE_COUNT; i++) {
      titles[i] = "Page" + i;
    }

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    toolbar.setContentInsetsAbsolute(0,0);
    setSupportActionBar(toolbar);

    viewPager = (ViewPager) findViewById(R.id.viewPager);
    pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
    viewPager.setAdapter(pagerAdapter);


    circleView = (CircleView) findViewById(R.id.circle_view);
    circleView.setViewPager(viewPager);

    textView = (TextAnimView) findViewById(R.id.textView);
    textView.setViewPager(viewPager);

    ringView = (RingView) findViewById(R.id.ringView);
    ringView.setViewPager(viewPager);

    colors = new int[]{Color.rgb(141,100,198),
            Color.rgb(237,81,178),
            Color.rgb(255,206,108),
            Color.rgb(52,203,159) ,
            Color.rgb(78,215,250) };

    colorAnimator = new ColorAnimator(colors);
    colorAnimator.setCurrentItem(viewPager.getCurrentItem());

    viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //Log.d("myLog","onPageScrolled" + " position: " + position + " positionOffset:" + positionOffset);
        textView.onScroll(position,positionOffset);
        ringView.onScroll(position,positionOffset);
        circleView.onScroll(position,positionOffset);
        viewPager.setBackgroundColor(colorAnimator.getCurrentColor(position,positionOffset));
      }

      @Override
      public void onPageSelected(int position) {
        circleView.setCurrentItem(position);
        textView.setCurrentItem(position);
        ringView.setCurrentItem(position);
        colorAnimator.setCurrentItem(position);
      }

      @Override
      public void onPageScrollStateChanged(int state) {
        switch (state){
          case ViewPager.SCROLL_STATE_DRAGGING:
            textView.setAnimated(true);
            ringView.setAnimated(true);
            circleView.setAnimated(true);
            colorAnimator.setAnimated(true);
            Log.d("myLog","SCROLL_STATE_DRAGGING");
            break;
          case ViewPager.SCROLL_STATE_IDLE:
            Log.d("myLog","SCROLL_STATE_IDLE");
            break;
          case ViewPager.SCROLL_STATE_SETTLING:
            Log.d("myLog","SCROLL_STATE_SETTLING");
            break;
        }
      }
    });

    AppBarLayout barLayout = findViewById(R.id.appBar);
    barLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
      @Override
      public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        //int circleViewVisibility = circleView.getVisibility();
        textView.setStrokeWidth(calculateStrokeWidth(verticalOffset, 400));
        ringView.setRadius(calculateRadius(verticalOffset, 400));
      }
    });


  }
  private float calculateRadius(int offset, int height){
    float percent = (float) Math.abs(offset)/ (float) height;
    Log.d("AppBar calculation",String.valueOf(percent));
    float minWidth = 50f;
    float maxWidth = 200f;
    return maxWidth - percent*(maxWidth-minWidth);
  }
  private float calculateStrokeWidth(int offset, int upperViewHeight){
    float percent = (float) Math.abs(offset)/ (float) upperViewHeight;
    float minWidth = 20f;
    float maxWidth = 40f;
    return maxWidth - percent*(maxWidth-minWidth);
  }

  private class MyFragmentPagerAdapter extends FragmentPagerAdapter{

    public MyFragmentPagerAdapter(FragmentManager fm) {
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      return BlankFragment.newInstance(position, titles[position]);
    }

    @Override
    public int getCount() {
      return PAGE_COUNT;
    }


  }



/*
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

*/
}
