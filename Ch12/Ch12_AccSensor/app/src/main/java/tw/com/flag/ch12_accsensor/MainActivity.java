package tw.com.flag.ch12_accsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity             implements SensorEventListener {
    SensorManager sm;  //用來參照【感測器管理員】
    Sensor sr;          //用來參照【加速感測器物件】
    TextView txv;       //用來參照畫面中的文字元件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE); //由系統服務取得感測器管理員
        sr = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //取得加速感測器
        txv = (TextView) findViewById(R.id.textView);     // 取得TextView元件
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        txv.setText(String.format("X軸: %1.2f, Y軸: %1.2f, Z軸: %1.2f",
                event.values[0], event.values[1], event.values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this, sr, SensorManager.SENSOR_DELAY_NORMAL); //向加速感測器 (sr) 註冊監聽物件(this)
    }
    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);  //取消監聽物件(this) 的註冊
    }
}
