package tw.com.flag.ch02_button;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int size=30;

    public void bigger(View v) {
        TextView txv;
        txv = (TextView) findViewById(R.id.txv);
        txv.setTextSize(++size);
    }
}
