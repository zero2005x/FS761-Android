package tw.com.flag.ch08_multiactivity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoSecondActivity(View v) {
        Intent it = new Intent(this, SecondActivity.class); //建立 Intent 並設定目標 Activity
        startActivity(it); // 啟動 Intent 中的目標 Activity
    }
}
