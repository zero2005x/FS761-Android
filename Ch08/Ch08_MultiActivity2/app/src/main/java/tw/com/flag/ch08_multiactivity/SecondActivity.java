package tw.com.flag.ch08_multiactivity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void goBack(View v) {
        finish(); // 結束 Activity, 即可回到前一個 Activity
    }
}
