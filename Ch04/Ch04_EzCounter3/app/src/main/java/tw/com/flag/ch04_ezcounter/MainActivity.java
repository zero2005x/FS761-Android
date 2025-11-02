package tw.com.flag.ch04_ezcounter;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, View.OnLongClickListener { // 實作 2 個介面
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
        btn.setOnLongClickListener(this);   //  將活動物件登錄為按鈕的長按監聽器
        txv.setOnLongClickListener(this);   // 將活動物件登錄為文字標籤的長按監聽器
    }

    @Override
    public void onClick(View v) {
        txv.setText(String.valueOf(++counter)); // 將計數值加 1, 然後轉成字串顯示出來
    }

    @Override
    public boolean onLongClick(View v) {
        if(v.getId() == R.id.textView) { // 判斷來源物件是否為顯示計數值的 txv, 若是就將計數歸零
            counter = 0;
            txv.setText("0");
        }
        else { // 來源物件為按鈕, 將計數值加 2
            counter += 2;
            txv.setText(String.valueOf(counter));
        }
        return true;
    }
}
