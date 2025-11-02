package tw.com.flag.ch05_foodmenu_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
implements CompoundButton.OnCheckedChangeListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] chk_id={R.id.chk1,R.id.chk2,R.id.chk3,R.id.chk4};
        for (int id:chk_id)
            ((CheckBox)findViewById(id)).setOnCheckedChangeListener(this);
    }

    int items=0;   // 記錄目前選取餐點的數量
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int visible;
        if(isChecked){  // 被選取時
            items++;    // 數量加 1
            visible=View.VISIBLE; // 將圖片設為可見
        }
        else{                     // 被取消時
            items--;              // 數量減 1
            visible=View.GONE;    // 將圖片設為不可見
        }

        switch (buttonView.getId()){  // 依項目 ID, 決定要更改的 ImageView ID
            case R.id.chk1:
                findViewById(R.id.output1).setVisibility(visible);
                break;
            case R.id.chk2:
                findViewById(R.id.output2).setVisibility(visible);
                break;
            case R.id.chk3:
                findViewById(R.id.output3).setVisibility(visible);
                break;
            case R.id.chk4:
                findViewById(R.id.output4).setVisibility(visible);
                break;
        }

        String msg;
        if(items>0) // 選取項目大於 0, 顯示有點餐的訊息
            msg= "你點的餐點如下";
        else        // 否則顯示請點餐的訊息
            msg= "請點餐!";
        ((TextView)findViewById(R.id.showOrder)).setText(msg);
    }
}
