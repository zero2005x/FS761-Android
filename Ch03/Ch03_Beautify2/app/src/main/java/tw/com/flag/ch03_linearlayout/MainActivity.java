package tw.com.flag.ch03_linearlayout;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 宣告代表 UI 元件的變數
    EditText sname,fname,phone;
    TextView txv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化變數
        sname = (EditText) findViewById(R.id.surName);
        fname = (EditText) findViewById(R.id.firstName);
        phone = (EditText) findViewById(R.id.phone);
        txv = (TextView) findViewById(R.id.txv);
    }

    public void onclick(View v){
        txv.setText(sname.getText().toString()+	// 取得姓
                fname.getText()+				// 取得名
                "的電話是 "+ phone.getText());	// 取得電話
    }
}
