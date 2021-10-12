package edu.hzuapps.androidlabs.watchtv;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import edu.hzuapps.androidlabs.R;
import edu.hzuapps.androidlabs.watchtv.until.ToolUtils;

import static android.content.Context.WINDOW_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    View rootView;
    protected Button play;
    protected VideoView videoView;
    protected EditText edittext;
    protected Button newplayer;



    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(rootView == null){
            rootView = inflater.inflate(R.layout.fragment_home, container, false);
        }
        edittext = rootView.findViewById(R.id.et_home);
        play = rootView.findViewById(R.id.bt_play);
        videoView = rootView.findViewById(R.id.vv_home);
        videoView.setMediaController(new MediaController(rootView.getContext()));
        videoView.setVisibility(View.INVISIBLE);
        play.setOnClickListener(this);
        newplayer = rootView.findViewById(R.id.newplayer);
        newplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MovieActivity.class);
                intent.setData(Uri.parse(edittext.getText().toString()));
                startActivity(intent);
            }
        });




        return rootView;
    }

    @Override
    public void onClick(View v) {
        if(videoView!=null && videoView.isPlaying()){
            videoView.stopPlayback();
        }
        videoView.setVideoURI(Uri.parse(edittext.getText().toString()));
        videoView.setVisibility(View.VISIBLE);
        videoView.start();
    }





}