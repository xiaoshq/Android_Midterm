package com.xiaoshq.sqltest;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


/**
 * Created by ivan on 2017/11/23.
 */

public class info_edit extends AppCompatActivity{
    public ImageView icon;
    public Button clear;
    public Button cancel;
    public Button save;
    public EditText name_edit;
    public EditText region_edit;
    public RadioGroup sex_choose;
    public RadioButton male;
    public RadioButton female;
    public EditText birth_edit;
    public EditText death_edit;
    public EditText info_edit;
    public int id;
    public DataOperation dataOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_edit);

        dataOperation = new DataOperation(this);
        if (!dataOperation.isDataExist()) {
            dataOperation.initTable();
        }

        icon = (ImageView)findViewById(R.id.icon_edit);
        clear = (Button)findViewById(R.id.clear);
        cancel = (Button)findViewById(R.id.cancel);
        save = (Button)findViewById(R.id.save);
        name_edit = (EditText)findViewById(R.id.name_edit) ;
        region_edit = (EditText)findViewById(R.id.birthplace_edit);
        sex_choose = (RadioGroup)findViewById(R.id.sex_choose);
        male = (RadioButton)findViewById(R.id.male);
        female = (RadioButton)findViewById(R.id.female);
        birth_edit = (EditText)findViewById(R.id.birth_edit);
        death_edit = (EditText)findViewById(R.id.death_edit);
        info_edit = (EditText)findViewById(R.id.info_edit);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("id", 0);
                intent.setClass(info_edit.this, info_show.class);
                startActivity(intent);
            }
        });

        Data data = dataOperation.getDataById(2);
        //icon.setImageResource(data.img);
        name_edit.setText(data.name);
        region_edit.setText(data.region);
        birth_edit.setText(data.born);
        death_edit.setText(data.dead);
        info_edit.setText(data.master);
        if(data.sex==0){
            male.setChecked(true);
            female.setChecked(false);
        }
        else if(data.sex==1){
            male.setChecked(false);
            female.setChecked(true);
        }
        initDialog();
    }

    //本地图册
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            Uri uri = data.getData();
            Log.e("uri", uri.toString());
            ContentResolver contentResolver = this.getContentResolver();
            try{
                Bitmap bitmap = BitmapFactory.decodeStream(contentResolver.openInputStream(uri));
                icon.setImageBitmap(bitmap);

            }catch (Exception e){
                Log.e("Exception", e.getMessage(), e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //region dialog
    private AlertDialog.Builder dialog;
    private void initDialog(){
        String[] items = {"从相册中选择"};
        dialog = new AlertDialog.Builder(this);
        dialog.setTitle("上传人物头像")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(info_edit.this,"取消上传", Toast.LENGTH_SHORT).show();
                    }
                });
        dialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
            }
        });

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
            }
        });
    }
    //endregion
}
