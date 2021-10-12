package edu.hzuapps.androidlabs.watchtv;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.hzuapps.androidlabs.R;
import edu.hzuapps.androidlabs.watchtv.room.Programs;


public class CollectFragment extends Fragment {

    ListView listView;
    View rootView;
    List<Programs> dataList;
    ArrayList<String> tagList;

    public CollectFragment() {
        // Required empty public constructor
    }

    public static CollectFragment newInstances(List<Programs> list, ArrayList<String> tag){
        CollectFragment collectFragment = new CollectFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) list);
        bundle.putStringArrayList("tag",tag);;
        collectFragment.setArguments(bundle);
        return collectFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            dataList = getArguments().getParcelableArrayList("data");
            tagList = getArguments().getStringArrayList("tag");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(rootView == null){
            rootView = inflater.inflate(R.layout.fragment_collect, container, false);
        }
        // Inflate the layout for this fragment
        initview();
        return rootView;
    }

    private void initview() {
        listView = rootView.findViewById(R.id.lv_home);
        listView.setAdapter(new MyBaseAdapter(dataList,tagList,this.getContext()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = dataList.get(position).getAddr();
                ClipboardManager cm;
                ClipData mClipData;
                //获取剪贴板管理器：
                cm = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                mClipData = ClipData.newPlainText("Label", str);
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                Toast.makeText(getContext(), "视频地址已复制到剪切板",
                        Toast.LENGTH_SHORT).show();

            }
        });
    }
}