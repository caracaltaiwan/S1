package com.example.s1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BattleActivity extends AppCompatActivity {
    // -----類別變數-----
    private Button btn_battle,btn_exit,btn_item;
    private TextView tv1,tv2,tv3;
    private ImageView pic;
    private ListView listView;
    private ArrayAdapter<String> listAdapter ,itemAdapter;
    private Toast toast;

    // -----部分內容-----
    private int hp;
    private String number;//怪獸的編號
    private String[]
                stats,//snippet切割後的資料 ,snippet(monstor number:i number:monster hp:monster Attack Power)
                list = DataBase.skill,
                listk = {"，這種又硬又尖的物品","，這種尖尖又脆脆的物品","，這種硬硬痛痛的物品","，沒什麼軟用","，你的塗鴉造成了怪物心理陰影"},
                item = {"恢復藥劑","恢復藥水"},
                itemk= {"，這種中藥","，這種西藥"};
    private int[] dmg = {20,20,40,0,5};

    // -----生命週期-----
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findview();
        createsetting();
        setlistener();

    }//onCreate end

    // -----監聽事件-----
    Button.OnClickListener
            btn_battle_event = new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listView.setAdapter(listAdapter);
                }
            },
            btn_exit_event =new Button.OnClickListener() {//強制
                @Override
                public void onClick(View v) {
                    BattleActivity.this.finish();
                }
            },
            btn_item_event = new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listView.setAdapter(itemAdapter);
                }
            };
    ListView.OnItemClickListener
            listview_event = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(listView.getAdapter().equals(listAdapter))
            {
                try {
                    hp = hp - dmg[position];
                    if (hp > 0) {
                        tv1.setText("" + hp);
                        makeTextAndShow(getApplicationContext(), "你選擇的是" + list[position] + listk[position] + "\n造成了" + dmg[position] + "點傷害", Toast.LENGTH_SHORT);
                    } else {
                        Intent intent2 = new Intent();
                        intent2.setClass(BattleActivity.this, GamingMaps.class);
                        Bundle bundle2 = new Bundle();
                        bundle2.putBoolean("battle", true);
                        intent2.putExtras(bundle2);
                        setResult(1, intent2);
                        BattleActivity.this.finish();
                        makeTextAndShow(getApplicationContext(), "太牛了，你用" + list[position] + "宰了" + tv2.getText(), Toast.LENGTH_SHORT);
                    }
                }catch (Exception e) {
                    makeTextAndShow(getApplicationContext(), "你選擇的是" + list[position], Toast.LENGTH_SHORT);
                }
            }
            else{
                try {
                    hp = hp + dmg[position];
                    if (hp > 0) {
                        tv3.setText("" + hp);
                        makeTextAndShow(getApplicationContext(), "你選擇的是" + item[position] + listk[position] + "\n恢復了" + dmg[position] + "點生命", Toast.LENGTH_SHORT);
                    } else {
                        Intent intent2 = new Intent();
                        intent2.setClass(BattleActivity.this, GamingMaps.class);
                        Bundle bundle2 = new Bundle();
                        bundle2.putBoolean("battle", true);
                        intent2.putExtras(bundle2);
                        setResult(1, intent2);
                        BattleActivity.this.finish();
                    }
                }catch (Exception e) {
                    makeTextAndShow(getApplicationContext(), "你選擇的是" + item[position], Toast.LENGTH_SHORT);
                }
            }
        }
    };

    // -----類別方法-----
    private void findview(){
        //listview define
        listView = (ListView)findViewById(R.id.listView);

        //listAdapter
        listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, DataBase.skill);
        itemAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, item);

        //button define
        btn_battle = (Button) findViewById(R.id.button6);
        btn_exit = (Button) findViewById(R.id.button7);//強制
        btn_item = (Button) findViewById(R.id.button8);

        //textView define
        tv1 = (TextView) findViewById(R.id.textView5);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);

        //picture define
        pic = (ImageView) findViewById(R.id.imageView);
    }
    private void createsetting(){
        //listAdapter setting
        listView.setAdapter(listAdapter);

        //getBundle
        try
        {
            Bundle bundle = getIntent().getExtras();
            number = bundle.getString("number");
        }catch (Exception e) {
            number = DataBase.monster[0];//snippet(monstor number:i number:monster hp:monster Attack Power) "30:5:160:70"
        }
        //hp = Integer.valueOf(number.substring(0,2));
        stats = number.split(":");//切割字串
        hp = Integer.valueOf(stats[2]);
        tv1.setText("" + hp);

        //imageView define
        imageView_define();
    }
    private void imageView_define(){
        //imageView define
        switch (Integer.valueOf(stats[0])) {
            case 11:
                pic.setImageResource(R.drawable.slime);tv2.setText("史萊姆");
                break;
            case 13:
                pic.setImageResource(R.drawable.succubus);tv2.setText("媚魔");
                break;
            default:
                pic.setImageResource(DataBase.picture[Integer.parseInt(stats[0])-1]);tv2.setText("記得生成名稱");
        }
    }
    private void setlistener(){
        //button listener
        btn_battle.setOnClickListener(btn_battle_event);
        btn_exit.setOnClickListener(btn_exit_event);
        btn_item.setOnClickListener(btn_item_event);

        //listview listener
        listView.setOnItemClickListener(listview_event);
    }

    private void makeTextAndShow (final Context context, final String text, final int duration){
        if (toast == null) {
            toast = android.widget.Toast.makeText(context, text, duration);
        } else {
            toast.setText(text);
            toast.setDuration(duration);
        }
        toast.show();//Create by Meng-Ting Tsai
    }
}//Mainactivity end
