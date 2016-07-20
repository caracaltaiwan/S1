package com.example.s1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AlretDialog_StageStory extends Activity {

    // -----類別變數-----
    Button
            btn_confirm,
            btn_cancel;
    TextView
            text_content,
            text_title,
            text_close;

    // -----部分內容-----
    int i;

    // -----生命週期-----
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_signin);

        findview();
        setlistence();

    }
    // -----監聽事件-----
    Button.OnClickListener
            btn_confirm_event = new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    try{
                        text_content.setText(Script.first[i]);
                        i--;
                    }catch (Exception e){
                        AlretDialog_StageStory.this.finish();
                    };
                }
            },
            btn_cancel_event = new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    try{
                        text_content.setText(Script.first[i]);
                        i++;
                    }catch (Exception e){
                        AlretDialog_StageStory.this.finish();
                    };
                }
            },
            btn_text_close_event = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(AlretDialog_StageStory.this, String.format("你跳過了\n%s", text_title.getText()), Toast.LENGTH_SHORT).show();
                    finish();
                }
            };

    // -----類別方法-----
    private void findview(){
        btn_confirm = (Button)findViewById(R.id.btn_confirm);
        btn_cancel = (Button)findViewById(R.id.btn_cancel);
        text_close = (TextView)findViewById(R.id.text_close);
        text_title  = (TextView)findViewById(R.id.text_title);
        text_content = (TextView)findViewById(R.id.text_content);
    }
    private void setlistence(){
        btn_confirm.setOnClickListener(btn_confirm_event);
        btn_cancel.setOnClickListener(btn_cancel_event);
        text_close.setOnClickListener(btn_text_close_event);
    }
}
