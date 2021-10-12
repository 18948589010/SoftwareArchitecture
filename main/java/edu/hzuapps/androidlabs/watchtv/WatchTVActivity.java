package edu.hzuapps.androidlabs.watchtv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.dommy.qrcode.util.Constant;
import com.geogle.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import edu.hzuapps.androidlabs.R;
import edu.hzuapps.androidlabs.watchtv.room.Programs;
import edu.hzuapps.androidlabs.watchtv.room.manager.DBEngine;

public class WatchTVActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "zhu";
    final WatchTVActivity thisActivity = this;
    private List<Programs> data = null;
    private ArrayList<String> datatag = null;
    private Context thiscontext = this;

    protected ViewPager2 viewPager;
    protected LinearLayout lhome,lstar,lacc;
    protected ImageView ivhome,ivstar,ivacc,ivcurr;
    protected Button btnSearch,btnScanner,btnMore;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_t_v);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                100);

        DBEngine db = new DBEngine(thiscontext);

       // dataInit();
       // data = dataQuery();
        dataInit();
        initPage();
        initMoudle();


    }

    private List<Programs> dataQuery() {
        List<Programs> list = new ArrayList<Programs>();
//        Resources resources = getResources();
//        String[] name = resources.getStringArray(R.array.name);
//        String[] url = resources.getStringArray(R.array.addr);
//        for (int i = 0; i < name.length; i++) {
//            Programs programs = new Programs();
//            programs.setName(name[i]);
//            programs.setAddr(url[i]);
//            programs.setId(i);
//            list.add(programs);
//        }
//        DBEngine db = new DBEngine(this);
//        List<Programs> list = db.queryAllPrograms();


        return list;
    }

    private void dataInit() {

        data = new ArrayList<>();
        datatag = new ArrayList<String>();
        Resources resources = getResources();
        String[] url = resources.getStringArray(R.array.addr);
        datatag.add("新闻");
        Programs p = new Programs();
        p.setName("新闻");
        data.add(p);
        int index = 0;
        for (int i = 0; i < 5; i++) {
            Programs programs = new Programs();
            programs.setName("新闻"+(i+1));
            programs.setAddr(url[index]);
            programs.setId(index++);
            data.add(programs);
        }

        datatag.add("娱乐");
        p = new Programs();
        p.setName("娱乐");
        data.add(p);
        for (int i = 0; i < 5; i++) {
            Programs programs = new Programs();
            programs.setName("娱乐"+(i+1));
            programs.setAddr(url[index]);
            programs.setId(index++);
            data.add(programs);
        }
        datatag.add("自然");
        p = new Programs();
        p.setName("自然");
        data.add(p);
        for (int i = 0; i < 5; i++) {
            Programs programs = new Programs();
            programs.setName("自然"+(i+1));
            programs.setAddr(url[index]);
            programs.setId(index++);
            data.add(programs);
        }
    }

    private void initPage(){
        viewPager = findViewById(R.id.vp_mid);
        ArrayList<Fragment> fragments= new ArrayList<>();
        fragments.add(HomeFragment.newInstance());
        fragments.add(CollectFragment.newInstances(data,datatag));
        fragments.add(UserFragment.newInstance());
        viewPager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(),getLifecycle(),fragments));
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                changTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }



    private void initMoudle(){
        lhome = findViewById(R.id.part_home);
        lstar = findViewById(R.id.part_collect);
        lacc = findViewById(R.id.ll_friend);
        ivhome = findViewById(R.id.iv_home);
        ivstar = findViewById(R.id.iv_collect);
        ivacc = findViewById(R.id.iv_user);
        btnSearch = findViewById(R.id.btn_search);
        btnScanner = findViewById(R.id.btn_scanner);
        btnMore = findViewById(R.id.btn_more);

        lhome.setOnClickListener(this);
        lstar.setOnClickListener(this);
        lacc.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
        btnScanner.setOnClickListener(this);
        btnMore.setOnClickListener(this);

        ivcurr = ivhome;
        ivhome.setSelected(true);
    }

    private void changTab(int position) {
        ivcurr.setSelected(false);
        switch (position){
            case R.id.part_home:
                viewPager.setCurrentItem(0);
            case 0:
                ivhome.setSelected(true);
                ivcurr = ivhome;
                break;
            case R.id.part_collect:
                viewPager.setCurrentItem(1);
            case 1:
                ivstar.setSelected(true);
                ivcurr = ivstar;
                break;
            case R.id.ll_friend:
            case R.id.btn_more:
                viewPager.setCurrentItem(2);
            case 2:
                ivacc.setSelected(true);
                ivcurr = ivacc;
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_more:
            case R.id.part_home:
            case R.id.part_collect:
            case R.id.ll_friend:
                changTab(v.getId());
                break;
            case R.id.btn_search:
                Intent intent = new Intent(thisActivity, SelectActivity.class);
                thisActivity.startActivity(intent);
                break;
            case R.id.btn_scanner:
                startQrCode();
                break;
        }

    }

    // 开始扫码
    private void startQrCode() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                Toast.makeText(WatchTVActivity.this, "请至权限中心打开本应用的相机访问权限", Toast.LENGTH_LONG).show();
            }
            // 申请权限
            ActivityCompat.requestPermissions(WatchTVActivity.this, new String[]{Manifest.permission.CAMERA}, Constant.REQ_PERM_CAMERA);
            return;
        }
        // 二维码扫码
        Intent intent = new Intent(WatchTVActivity.this, CaptureActivity.class);
        startActivityForResult(intent, Constant.REQ_QR_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String result = "";
        //扫描结果回调
        if (requestCode == Constant.REQ_QR_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                result = bundle.getString(Constant.INTENT_EXTRA_KEY_QR_SCAN);
            }
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(result));    //splitflowurl为分流地址
        if (!hasPreferredApplication(this,intent)){
            intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
        }
        startActivity(intent);

    }

    public static boolean hasPreferredApplication(Context context, Intent intent) {
        PackageManager pm = context.getPackageManager();
        ResolveInfo info = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return !"android".equals(info.activityInfo.packageName);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Constant.REQ_PERM_CAMERA:
                // 摄像头权限申请
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获得授权
                    startQrCode();
                } else {
                    // 被禁止授权
                    Toast.makeText(WatchTVActivity.this, "请至权限中心打开本应用的相机访问权限", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


}