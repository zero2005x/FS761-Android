package tw.com.flag.ch07_datetimepicker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener,
        DatePickerDialog.OnDateSetListener,     // 實作監聽日期交談窗事件的介面
        TimePickerDialog.OnTimeSetListener {

    Calendar c = Calendar.getInstance();  //建立日曆物件
    TextView txDate;                 // 記錄日期文字的元件
    TextView txTime;                 // 記錄時間文字的元件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txDate = (TextView)findViewById(R.id.textView1);
        txTime = (TextView)findViewById(R.id.textView2);

        txDate.setOnClickListener(this); //設定按下日期文字時的監聽物件
        txTime.setOnClickListener(this); //設定按下時間文字時的監聽物件
    }

    @Override
    public void onClick(View v) {
        if(v == txDate) { // 按的是日期文字
            //建立日期選擇交談窗, 並傳入設定完成時的監聽物件
            new DatePickerDialog(this, this,    // 由活動物件監聽事件
                    c.get(Calendar.YEAR),   //由Calendar物件取得目前的西元年
                    c.get(Calendar.MONTH),  //取得目前月 (由 0 算起)
                    c.get(Calendar.DAY_OF_MONTH)) //取得目前日
                    .show();  //顯示出來
        }
        else if(v == txTime) { // 按的是時間文字
            //建立時間選擇交談窗, 並傳入設定完成時的監聽物件
            new TimePickerDialog(this, this, // 由活動物件監聽事件
                    c.get(Calendar.HOUR_OF_DAY), //取得目前的時 (24小時制)
                    c.get(Calendar.MINUTE),      //取得目前的分
                    true)                        //使用24小時制
                    .show();   //顯示出來
        }
    }
    @Override
    public void onDateSet(DatePicker v, int y, int m, int d) {
        txDate.setText("日期：" + y + "/" + (m+1) + "/" + d);  // 將選定日期顯示在螢幕上
    }

    @Override
    public void onTimeSet(TimePicker v, int h, int m) {
        txTime.setText("時間：" + h + ":" + m);    // 將選定的時間顯示在螢幕上
    }
}
