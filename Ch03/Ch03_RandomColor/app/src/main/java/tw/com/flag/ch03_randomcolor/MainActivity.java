package tw.com.flag.ch03_randomcolor;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView txvR,txvG,txvB;
    View colorBlock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 取得3個TextView的物件, 畫面最下方的 LinearLayout
        txvR = (TextView) findViewById(R.id.txvR);
        txvG= (TextView) findViewById(R.id.txvG);
        txvB= (TextView) findViewById(R.id.txvB);
        colorBlock = findViewById(R.id.colorBlock);
    }

    public void changeColor(View v){
        // 取得亂數物件, 產生3個亂數值(rgb值)
        Random x=new Random();
        int red=x.nextInt(256);		// 取0～255之間的亂數
        txvR.setText("紅："+ red);	// 顯示亂數值
        txvR.setTextColor(Color.rgb(red, 0, 0));	// 將文字設為亂數顏(紅)色值

        int green=x.nextInt(256);
        txvG.setText("綠："+ green);
        txvG.setTextColor(Color.rgb(0,green,0));// 將文字設為亂數顏(綠)色值

        int blue=x.nextInt(256);
        txvB.setText("藍："+ blue);
        txvB.setTextColor(Color.rgb(0,0,blue));	// 將文字設為亂數顏(藍)色值

        // 設定畫面最下方的空白 LinearLayout 之背景顏色
        colorBlock.setBackgroundColor(Color.rgb(red, green, blue));
    }
}
