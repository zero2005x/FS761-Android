package tw.com.flag.ch02_edittext;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sayHello(View v){
        // 取得代表佈局中 EditText 及 TextView 的物件
        EditText name = (EditText)findViewById(R.id.name);
        TextView txv = (TextView)findViewById(R.id.txv);

        // 將 EditText 文字串接自訂訊息再設定給 TextView
        txv.setText(name.getText().toString() + ", 您好！");
    }
}
