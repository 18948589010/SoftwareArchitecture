package edu.hzuapps.androidlabs.watchtv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import edu.hzuapps.androidlabs.R;
import edu.hzuapps.androidlabs.watchtv.room.Programs;

public class MyBaseAdapter extends BaseAdapter {

    private List<Programs> data;
    private List<String> datatag;
    private Context context;

    public MyBaseAdapter(List<Programs> data,List<String> datatag, Context context) {
        this.data = data;
        this.datatag = datatag;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            if(datatag.contains(data.get(position).getName())){
                convertView = LayoutInflater.from(context).inflate(R.layout.list_item_tag, parent, false);
            }else{
                convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            }

        }
        TextView textview = convertView.findViewById(R.id.tv_item);
        textview.setText(data.get(position).getName());
        return convertView;
    }
}
