package tw.com.flag.ch12_level;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements SensorEventListener {  //實作感測器監聽介面
    SensorManager sm;  //用來參照【感測器管理員】
    Sensor sr;         //用來參照【加速感測器物件】
    TextView txv;      //用來參照畫面中的文字元件
    ImageView igv;     //用來參照畫面中要移動的小圖
    RelativeLayout layout;  //用來參照畫面的 Layout 元件
    double mx = 0, my = 0;  //用來儲存 x,y 方向每一等分的大小

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);//設定螢幕不隨手機旋轉

        sm = (SensorManager) getSystemService(SENSOR_SERVICE); //由系統服務取得感測器管理員
        sr = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //取得加速感測器
        txv = (TextView) findViewById(R.id.txvIno);       //取得TextView元件
        igv = (ImageView) findViewById(R.id.igvMove);   //取得要移動的ImageView元件
        layout = (RelativeLayout) findViewById(R.id.layout);  //取得layout元件
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(mx == 0) { //如果還沒計算過
            mx = (layout.getWidth()-igv.getWidth()) /20.0;   //計算 x 方向每一等分的大小
            my = (layout.getHeight()-igv.getHeight()) /20.0; //計算 y 方向每一等分的大小
        }

        RelativeLayout.LayoutParams parms =     //取得小圖的 LayoutParm 物件
                (RelativeLayout.LayoutParams) igv.getLayoutParams();
        parms.leftMargin = (int)((10-event.values[0]) * mx);  //設定左邊界
        parms.topMargin = (int)((10+event.values[1]) * my);  //設定上邊界
        igv.setLayoutParams(parms);    //將小圖套用 LayoutParm, 使邊界設定生效

        txv.setText(String.format("X軸: %1.2f, Y軸: %1.2f, Z軸: %1.2f",   // 顯示感測器的資料
                event.values[0], event.values[1], event.values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {    }

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
