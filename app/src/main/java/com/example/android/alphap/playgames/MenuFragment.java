package com.example.android.alphap.playgames;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.alphap.R;
import com.example.android.alphap.eddie.MainActivityRVAdapter;

import java.util.ArrayList;

public class MenuFragment extends Fragment {
    private Listener listener;
    private ArrayList mainRvImages;
    private ArrayList mainRvTvAList;
    private TextView appName;
    private ImageView aboutUsIcon;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Listener) {
            listener = (Listener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement Listener");
        }
    }
    public interface Listener {
        void onCreateGameClicked();

        void onJoinGameClicked();

        void onSignOutClicked();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_screen2, container, false);

        appName = (TextView) view.findViewById(R.id.product_name);
        Typeface satisfy_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Satisfy-Regular.ttf");
        appName.setTypeface(satisfy_font);
        final ImageView animImageView = (ImageView) view.findViewById(R.id.header_view2);
        animImageView.setBackgroundResource(R.drawable.sun_header_anim);
        animImageView.setImageAlpha(5);
        animImageView.post(new Runnable() {
            @Override
            public void run() {
                AnimationDrawable frameAnimation =
                        (AnimationDrawable) animImageView.getBackground();
                frameAnimation.start();
            }
        });

        initViews(view);

        return view;
    }

    private void initViews(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.main_rv);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        mainRvImages = new ArrayList<>();
        mainRvImages.add(R.drawable.tractor_clip_art_470px);
        mainRvImages.add(R.drawable.barn_clipart_470px);

        mainRvTvAList = new ArrayList<>();
        mainRvTvAList.add("Create");
        mainRvTvAList.add("Join");
        aboutUsIcon = (ImageView) view.findViewById(R.id.fab);
        aboutUsIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "About Us Fragment", Toast.LENGTH_SHORT).show();

            }
        });


        RecyclerView.Adapter adapter = new MainActivityRVAdapter(mainRvImages, mainRvTvAList,listener);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

            });




            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                View child = rv.findChildViewUnder(e.getX(), e.getY());
                if (child != null && gestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);

                    if (position == 0) {
                        Toast.makeText(getContext(), "Create Activity", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getContext(), "Join Activity", Toast.LENGTH_SHORT).show();

                    }
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }
}




//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        appName = (TextView) view.findViewById(R.id.product_name);
//        Typeface satisfy_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Satisfy-Regular.ttf");
//        appName.setTypeface(satisfy_font);
//        final ImageView animImageView = (ImageView) view.findViewById(R.id.header_view2);
//        animImageView.setBackgroundResource(R.drawable.sun_header_anim);
//        animImageView.setImageAlpha(5);
//        animImageView.post(new Runnable() {
//            @Override
//            public void run() {
//                AnimationDrawable frameAnimation =
//                        (AnimationDrawable) animImageView.getBackground();
//                frameAnimation.start();
//            }
//        });
//
//        initViews(view);
//
//
//    }

//    interface Listener {
//        void onCreateGameClicked();
//
//        void onJoinGameClicked();
//
//        void onSignOutClicked();
//    }
//
//
//            @Override
//            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//
//                View child = rv.findChildViewUnder(e.getX(), e.getY());
//                if (child != null && gestureDetector.onTouchEvent(e)) {
//                    int position = rv.getChildAdapterPosition(child);
//
//                    if (position == 0) {
//                        Toast.makeText(getContext(), "Create Activity", Toast.LENGTH_SHORT).show();
//
//                    } else {
//                        Toast.makeText(getContext(), "Join Activity", Toast.LENGTH_SHORT).show();
//
//                    }
//                }
//
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });
//
//    }
//}
