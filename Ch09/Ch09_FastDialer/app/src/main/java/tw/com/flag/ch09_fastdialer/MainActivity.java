package tw.com.flag.ch09_fastdialer;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        Intent it = new Intent();         //新建 Intent 物件
        it.setAction(Intent.ACTION_VIEW); //設定動作：顯示資料
        it.setData(Uri.parse("tel:800")); //設定資料：用URI指定電話號碼
        startActivity(it);                //啟動適合 Intent 的 Activity
    }
}
