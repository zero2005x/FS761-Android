package tw.com.flag.ch05_foodmenu_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void takeOrder(View v) {
        CheckBox chk;
        String msg="";
        // 用陣列存放所有 CheckBox 元件的 ID
        int[] id={R.id.chk1, R.id.chk2, R.id.chk3, R.id.chk4};

        for(int i:id){    // 以迴圈逐一檢視各 CheckBox 是否被選取
            chk = (CheckBox) findViewById(i);
            if(chk.isChecked())            // 若有被選取
                msg+="\n"+chk.getText();   // 將換行字元及選項文字
        }                                  // 附加到 msg 字串後面

        if(msg.length()>0) // 有點餐
            msg ="你點購的餐點是："+msg;
        else
            msg ="請點餐!";

        // 在文字方塊中顯示點購的項目
        ((TextView) findViewById(R.id.showOrder)).setText(msg);
    }
}
