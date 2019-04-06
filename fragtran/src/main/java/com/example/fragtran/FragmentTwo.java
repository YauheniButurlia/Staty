package com.example.fragtran;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.ChangeBounds;
import android.transition.ChangeClipBounds;
import android.transition.ChangeImageTransform;
import android.transition.ChangeTransform;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentTwo extends Fragment {

  public FragmentTwo(){
    Slide slide = new Slide();
    slide.setSlideEdge(Gravity.BOTTOM);
    slide.addTarget(R.id.slideUpView);
    setEnterTransition(slide);
    setReturnTransition(slide);

    TransitionSet transition = new TransitionSet();
    transition.addTransition(new ChangeBounds());
    transition.addTransition(new ChangeClipBounds());
    transition.addTransition(new ChangeImageTransform());
    transition.addTransition(new ChangeTransform());
    transition.setOrdering(TransitionSet.ORDERING_TOGETHER);
    setSharedElementEnterTransition(transition);
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment2,container,false);
    return view;
  }
}
