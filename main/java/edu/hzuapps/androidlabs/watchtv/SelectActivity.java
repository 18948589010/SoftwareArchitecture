package edu.hzuapps.androidlabs.watchtv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import edu.hzuapps.androidlabs.R;

public class SelectActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        Button back = findViewById(R.id.btn_back);
        back.setOnClickListener(this);
        Button search = findViewById(R.id.btn_search);
        search.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_search:
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }
}