package tw.com.flag.ch07_dialogshow;

import android.support.v7.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlertDialog.Builder bdr = new AlertDialog.Builder(this);
        bdr.setMessage("交談窗示範教學！\n" // 加入文字訊息
                + "請按返回鍵關閉交談窗");
        bdr.setTitle("歡迎");        // 加入標題
        bdr.setIcon(android.R.drawable.btn_star_big_on); // 加入圖示
        bdr.setCancelable(true);   // 允許按返回鍵關閉交談窗
        bdr.show();
    }
}
