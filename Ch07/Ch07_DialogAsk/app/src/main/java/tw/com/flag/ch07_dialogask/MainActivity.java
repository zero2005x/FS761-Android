package tw.com.flag.ch07_dialogask;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements DialogInterface.OnClickListener {

    TextView txv; // 記錄預設的 TextView 元件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txv = (TextView) findViewById(R.id.answer); // 找出預設的 TextView 元件
        new AlertDialog.Builder(this) // 建立 Builder 物件
                .setMessage("你喜歡 Android 手機嗎？") // 設定顯示訊息
                .setCancelable(false) // 禁用返回鍵關閉交談窗
                .setIcon(android.R.drawable.ic_menu_edit) // 採用內建的圖示
                .setTitle("Android 問卷調查") // 設定交談窗的標題
                .setPositiveButton("喜歡", this)  // 加入否定按鈕
                .setNegativeButton("討厭", this)    // 加入肯定按鈕
                .setNeutralButton("沒意見", null) // 不監聽按鈕事件
                .show(); // 顯示交談窗
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if(which == DialogInterface.BUTTON_POSITIVE) { // 如果按下肯定的『喜歡』
            txv.setText("你喜歡 Android 手機");
        }
        else if(which == DialogInterface.BUTTON_NEGATIVE) { // 如果按下否定的『討厭』
            txv.setText("你討厭 Android 手機");
        }
    }
}
