package com.example.fragtran;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class FragmentOne extends Fragment {

  private FragmentTwo fragmentTwo = new FragmentTwo();

  public FragmentOne(){
    Slide slide = new Slide();
    slide.setSlideEdge(Gravity.BOTTOM);
    slide.addTarget(R.id.slideDownView);
    setExitTransition(slide);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment1,container,false);

    final ImageView imageView = view.findViewById(R.id.imageView);
    imageView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
         getFragmentManager().beginTransaction()
                 .addSharedElement(imageView, ViewCompat.getTransitionName(imageView))
                 .replace(R.id.root, fragmentTwo)
                 .addToBackStack(null)
                 .commit();
      }
    });
    return view;
  }
}
