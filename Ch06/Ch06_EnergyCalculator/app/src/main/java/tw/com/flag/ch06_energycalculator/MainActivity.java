package tw.com.flag.ch06_energycalculator;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {
    // 字串陣列中各項運動的能量消耗率:『仟卡/公斤/小時』
    double[] energyRate={3.1, 4.4, 13.2, 9.7, 5.1, 3.7};
    EditText weight,time;	// 體重及運動時間欄位
    TextView total,txvRate;	// 顯示能量消耗率, 計算結果的 TextView
    Spinner sports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weight= (EditText)findViewById(R.id.weight);
        time= (EditText)findViewById(R.id.timeSpan);
        total= (TextView)findViewById(R.id.total);
        txvRate= (TextView)findViewById(R.id.txvRate);
        sports=(Spinner) findViewById(R.id.sports);
        sports.setOnItemSelectedListener(this); // 註冊監聽器
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // 顯示選取的運動項目, 其基本的能量消耗率
        txvRate.setText(String.valueOf(energyRate[position]));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // 不會用到, 但仍需保留
    }

    public void calc(View v){
        String w = weight.getText().toString();  // 取得使用者輸入的體重
        String t = time.getText().toString();  // 取得使用者輸入的運動時間長度
        if(w.isEmpty() || w.equals(".") || t.isEmpty() || t.equals(".")) { // 如果輸入空白或 "." 則不計算
            total.setText("請輸入體重及運動時間");  // 顯示提示訊息
            return;
        }

        int pos = sports.getSelectedItemPosition(); // 取得目前選取項目的索引

        // 計算消耗能量=能量消耗率*體重*運動時間長度
        long kcal = Math.round( energyRate[pos]
                        * Double.parseDouble(w) * Double.parseDouble(t));

        total.setText( String.format("消耗能量 %d 仟卡", kcal));		// 顯示計算結果
    }
}
