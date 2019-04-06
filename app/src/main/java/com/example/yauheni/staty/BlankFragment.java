package com.example.yauheni.staty;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yauheni.staty.Adapters.MyListAdapter;


public class BlankFragment extends Fragment {

  static final String ARGUMENT_PAGE_NUMBER = "Page_number";
  static final String ARGUMENT_PAGE_TITLE = "Page_title";

  int pageNumber;
  int backColor;

  private int amountOfItems = 15;

  private RecyclerView recyclerView;
  private LinearLayoutManager mLayoutManager;
  private MyListAdapter adapter;

  private int[] textColors = new int[]{Color.rgb(141,100,198),
          Color.rgb(237,81,178),
          Color.rgb(255,206,108),
          Color.rgb(52,203,159) ,
          Color.rgb(78,215,250) };

  static BlankFragment newInstance(int page, String title) {
    BlankFragment pageFragment = new BlankFragment();
    Bundle arguments = new Bundle();
    arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
    arguments.putString(ARGUMENT_PAGE_TITLE, title);
    pageFragment.setArguments(arguments);
    return pageFragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);

    backColor = textColors[pageNumber];
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_blank, null);

    recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
    //recyclerView.setHasFixedSize(true);
    mLayoutManager = new LinearLayoutManager(getContext());
    recyclerView.setLayoutManager(mLayoutManager);

    adapter = new MyListAdapter(amountOfItems);
    recyclerView.setAdapter(adapter);

    //recyclerView.setScrollingTouchSlop(RecyclerView.TOUCH_SLOP_PAGING);

    return view;
  }
}

