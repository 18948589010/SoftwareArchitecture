package edu.hzuapps.androidlabs.watchtv;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import edu.hzuapps.androidlabs.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {

    View rootview;


    public UserFragment() {
        // Required empty public constructor
    }


    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(rootview == null){
            rootview = inflater.inflate(R.layout.fragment_user, container, false);
        }
        ImageView imageView = rootview.findViewById(R.id.imageView);
        Glide.with(this)
                .load(R.drawable.myphoto)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(180)))
                .into(imageView);
        // Inflate the layout for this fragment
        return rootview;
    }
}