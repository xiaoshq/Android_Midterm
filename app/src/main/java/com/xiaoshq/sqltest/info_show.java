package com.xiaoshq.sqltest;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.Permission;

/**
 * Created by ivan on 2017/11/23.
 */

public class info_show extends AppCompatActivity{
    public ImageView icon;
    public Button edit;
    public Button like;
    public Button delete;
    public TextView name;
    public TextView sex;
    public TextView region;
    public TextView birth;
    public TextView death;
    public TextView master;
    public int id;
    public DataOperation dataOperation;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_show);

        dataOperation = new DataOperation(this);
        if (!dataOperation.isDataExist()) {
            dataOperation.initTable();
        }

        icon = (ImageView)findViewById(R.id.icon_show);
        like = (Button)findViewById(R.id.like);
        edit = (Button)findViewById(R.id.edit);
        delete = (Button)findViewById(R.id.delete);
        name = (TextView)findViewById(R.id.name) ;
        sex = (TextView)findViewById(R.id.sex);
        region = (TextView)findViewById(R.id.birthplace);
        birth = (TextView)findViewById(R.id.birth);
        death = (TextView)findViewById(R.id.death);
        master = (TextView)findViewById(R.id.master);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(info_show.this, "edit", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("id", 1);
                intent.setClass(info_show.this, info_edit.class);
                startActivity(intent);
            }
        });
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO:添加到收藏
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO：从数据库删除Delete 传入id
            }
        });

        Data data = dataOperation.getDataById(1);  // for test

        name.setText(data.name);

        verifyStoragePermissions();

        data.img = Environment.getExternalStorageDirectory() + data.img;
        Bitmap bitmap = getLocalBitmap(data.img);
        icon.setImageBitmap(bitmap);

        if(data.sex==0) sex.setText("男");
        else sex.setText("女");

        region.setText(data.region);
        birth.setText(data.born);
        death.setText(data.dead);
        master.setText(data.master);
    }

    //加载本地图片，根据url获得bitmap
    public static Bitmap getLocalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    //权限确认
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void verifyStoragePermissions()
    {
        try
        {
            int permissions = checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE");
            if(permissions != PackageManager.PERMISSION_GRANTED)
            {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {

        }
        else
        {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("权限不足");
            dialog.setMessage("按确定退出程序。");
            dialog.setPositiveButton("退出", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        }
    }

}
