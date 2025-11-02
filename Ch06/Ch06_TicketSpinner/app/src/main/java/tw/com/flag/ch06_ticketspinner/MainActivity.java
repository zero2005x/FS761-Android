package tw.com.flag.ch06_ticketspinner;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txv;
    Spinner cinema;    // 戲院清單物件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txv = (TextView) findViewById(R.id.txv);
        cinema = (Spinner) findViewById(R.id.cinema);
    }

    public void order(View v) {
        String[] cinemas = getResources().             // 取得字串資源中
                getStringArray(R.array.cinemas);      // 的字串陣列

        int index = cinema.getSelectedItemPosition();    // 取被選取的項目
        txv.setText("訂" + cinemas[index] + "的票");    // 顯示選取的項目
    }
}
