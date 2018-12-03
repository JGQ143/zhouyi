package com.example.jiaguoqiang2018123;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends FrameLayout {
    public SearchActivity( Context context) {
        super(context);
    }

    public SearchActivity( Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchActivity( Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    //添加方法
    public void addView(final String name) {
        //布局
        final TextView textView = (TextView) View.inflate(getContext(),R.layout.activity_item,null);
        //输出
        textView.setText(name);


        //点击事件
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"点击了"+textView,Toast.LENGTH_LONG).show();
            }
        });
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        textView.setLayoutParams(params);



        //添加
        addView(textView);

    }

    //创建onLayout
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        //获取屏幕宽度
        int width = getWidth();

        //行数
        int row=0;

        //边距
        int disWidth=20;

        for (int i=0;i<getChildCount();i++){
            //获取子元素
            View view = getChildAt(i);

            //获取子宽高

            int viewWidth = view.getWidth();
            int viewHeight = view.getHeight();
            //判断
            if (disWidth+viewWidth>width){
                row++;
                disWidth=20;
            }

            view.layout(disWidth,row*viewHeight,disWidth+viewWidth,viewWidth*(row+1));

            disWidth+=viewWidth;
        }
    }


}
