package tw.com.flag.ch16_itankled;

import android.content.pm.ActivityInfo;
import android.os.Message;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import tw.com.flag.api.FlagBt;
import tw.com.flag.api.OnFlagMsgListener;

public class MainActivity extends AppCompatActivity
        implements OnFlagMsgListener {

    FlagBt bt; //宣告藍牙物件
    TextView txv;
    byte[] ledCmd ={ //點亮 LED0 的指令
            (byte)0xFF,(byte)0xFF,
            (byte)0x03,(byte)0x00,(byte)0x02,
            (byte)0xFF,(byte)0xFF,(byte)0x00
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setRequestedOrientation( // 讓手機螢幕保持直立模式
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        txv = (TextView)findViewById(R.id.txv);
        bt = new FlagBt(this); // 建立藍牙物件
    }

    public void onDestroy() {
        bt.stop(); // 確保程式結束前會停止藍牙連線
        super.onDestroy();
    }

    public void connect(View v) {
        if(!bt.connect()) // 選取已配對裝置進行連線
            txv.setText("找不到任何已配對裝置");
    }

    public void quit(View v) {
        bt.stop();
        finish();
    }

    @Override
    public void onFlagMsg(Message msg) {
        switch(msg.what) {
            case FlagBt.CONNECTING: // 嘗試與已配對裝置連線
                txv.setText("正在連線到：" + bt.getDeviceName());
                break;
            case FlagBt.CONNECTED:  // 與已配對裝置連線成功
                txv.setText("已連線到：" + bt.getDeviceName());
                bt.write(ledCmd);   // 送出點亮 LED0 的指令
                break;
            case FlagBt.CONNECT_FAIL: // 連線失敗
                txv.setText("連線失敗！請重連");
                break;
            case FlagBt.CONNECT_LOST: // 目前連線意外中斷
                txv.setText("連線中斷!請重連");
                break;
        }
    }
}