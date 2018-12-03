package com.example.jiaguoqiang2018123;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edit;
    private Button button;
    private SearchActivity flowlayout;
    private Dao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取控件
        edit = findViewById(R.id.edit);
        button = findViewById(R.id.mbuton);
        flowlayout = findViewById(R.id.flowlayout);

        //点击事件
        button.setOnClickListener(this);

        //创建dao方法
        dao = new Dao(this);

        //查询历史记录

        Cursor query = dao.query("zhouyi", null, null, null, null, null, null);
        //创建Arrayli集合
        ArrayList<String> list = new ArrayList<>();
        if (query.moveToFirst()){
            do {
                String name = query.getString(query.getColumnIndex("name"));
                list.add(name);
            }while (query.moveToNext());
            //循环数据库长度
            for (int i=0;i<list.size();i++){
                final TextView textView = new TextView(this);
                textView.setText(list.get(i));

                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                textView.setLayoutParams(params);
                textView.setPadding(10,5,10,5);
                flowlayout.addView(textView);
                textView.setTextSize(20);

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,"点击了"+textView,Toast.LENGTH_LONG).show();
                    }
                });
            }
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.mbuton){
            String name = edit.getText().toString();
            //添加到数据库
            ContentValues values = new ContentValues();
            values.put("name",name);
            long insert = dao.insert("zhouyi", null, values);
            Toast.makeText(MainActivity.this,"tj="+insert,Toast.LENGTH_LONG).show();
            //添加历史
            flowlayout.addView(name);
        }else{
            flowlayout.removeAllViews();
            //删除
            long delete = dao.delete("zhouyi", null, null);
            Toast.makeText(MainActivity.this,"tj="+delete,Toast.LENGTH_LONG).show();
        }
    }
}
