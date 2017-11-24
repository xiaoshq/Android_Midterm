package com.xiaoshq.sqltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public DataOperation dataOperation;
    public List<Data> dataList;
    public DataListAdapter adapter;
    public ListView showDateListView;
    public info_show show;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.info_show);
        show = new info_show();
        show.onCreate(savedInstanceState);


        dataOperation = new DataOperation(this);
        if (!dataOperation.isDataExist()) {
            dataOperation.initTable();
        }

        initComponent();

//        dataList = dataOperation.getAllData();
//        if (dataList != null) {
//            adapter = new DataListAdapter(this, dataList);
//            showDateListView.setAdapter(adapter);
//        }
    }

    private void initComponent() {
        // TODO 绑定各种控件，设监听器
//        Button buttonInsert = (Button)findViewById(R.id.buttonInsert);
//        Button buttonDelete = (Button)findViewById(R.id.buttonDelete);
//        Button buttonShow = (Button)findViewById(R.id.buttonShow);
//        Button buttonModify = (Button)findViewById(R.id.buttonModify);
//
//        SQLBtnOnClickListener onClickListener = new SQLBtnOnClickListener();
//        buttonInsert.setOnClickListener(onClickListener);
//        buttonDelete.setOnClickListener(onClickListener);
//        buttonShow.setOnClickListener(onClickListener);
////        buttonModify.setOnClickListener(onClickListener);
//    }
//
//    private void refreshDataList(){
//        // 注意：千万不要直接赋值，如：orderList = ordersDao.getAllDate() 此时相当于重新分配了一个内存 原先的内存没改变 所以界面不会有变化
//        // Java中的类是地址传递 基本数据才是值传递
//        dataList.clear();
//        dataList.addAll(dataOperation.getAllData());
//        adapter.notifyDataSetChanged();
//    }

//
//
//    public class SQLBtnOnClickListener implements View.OnClickListener {
//        @Override
//        public void onClick(View view) {
//            int id = 1;
//            Data newData = new Data();
//
//            // TODO 获取int类型的id 或者Data类型的newData
//            switch (view.getId()) {
//                case R.id.buttonInsert:
//                    dataOperation.insertData(newData);
//                    refreshDataList();
//                    break;
//                case R.id.buttonDelete:
//                    dataOperation.deleteData(id);
//                    refreshDataList();
//                    break;
//                case R.id.buttonShow:
//                    dataList = dataOperation.getData(id);
//                    refreshDataList();
//                    test();
//                    break;
//                case R.id.buttonModify:
//                    dataOperation.updateData(id, newData);
//                    refreshDataList();
//                    break;
//                default:
//                    break;
//            }
//
//
//
//        }
//    }
//
//    private TextView nameTV;
//    private TextView regionTV;
//
//    public void test(){
//        // 测试show
//        Data data = dataList.get(0);
//        nameTV = (TextView) findViewById(R.id.dataNameTV);
//        regionTV = (TextView) findViewById(R.id.dataRegionTV);
//        nameTV.setText(data.name);
//        regionTV.setText(data.region);
//    }
//
////    public void setView()
////    {
////        int position = 0;
////        Data data = dataList.get(position);
////
////        nameTV.setText(data.name);
////        regionTV.setText(data.region);
      }

}
