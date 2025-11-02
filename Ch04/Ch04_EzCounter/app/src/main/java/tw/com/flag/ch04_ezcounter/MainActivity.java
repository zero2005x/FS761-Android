package tw.com.flag.ch04_ezcounter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener{
    TextView txv;		// 用來參照 textView1 元件的變數
    Button btn;			// 用來參照 button1 元件的變數
    int counter = 0;	    // 用來儲存計數的值, 初值為 0

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txv = (TextView) findViewById(R.id.textView); // 找出要參照的物件
        btn = (Button) findViewById(R.id.button); // 找出要參照的物件

        btn.setOnClickListener(this); // 登錄監聽物件, this 表示活動物件本身
    }

    @Override
    public void onClick(View v) {
        txv.setText(String.valueOf(++counter)); // 將計數值加 1, 然後轉成字串顯示出來
    }
}
