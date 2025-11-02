package tw.com.flag.ch05_buyticket;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void show(View v){
        TextView txv=(TextView)findViewById(R.id.txv);
        RadioGroup ticketType =
                (RadioGroup) findViewById(R.id.ticketType);

        // 依選取項目顯示不同訊息
        int checkedId = ticketType.getCheckedRadioButtonId();
        if (checkedId == R.id.adult) {	// 選全票
            txv.setText("買全票");
        } else if (checkedId == R.id.child) {	// 選半票
            txv.setText("買半票");
        } else if (checkedId == R.id.senior) {	// 選敬老票
            txv.setText("買敬老票");
        }
    }
}
