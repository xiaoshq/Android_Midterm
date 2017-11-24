package com.xiaoshq.sqltest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        Data data = dataOperation.getDataById(2);
        //icon.setImageResource(data.img);
        name.setText(data.name);

        if(data.sex==0) sex.setText("男");
        else sex.setText("女");

        region.setText(data.region);
        birth.setText(data.born);
        death.setText(data.dead);
        master.setText(data.master);
    }

}
