package tw.com.flag.ch08_memo;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    String[] aMemo = { // 預設的備忘內容
            "1. 按一下可以編輯備忘",
            "2. 長按可以清除備忘","3.","4.","5.","6." };
    ListView lv; // 顯示備忘錄的 ListView
    ArrayAdapter<String> aa; // ListView 與備忘資料的橋樑

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView)findViewById(R.id.listView);
        aa = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, aMemo);

        lv.setAdapter(aa);    //設定 listView1 的內容

        //設定 listView1 被按一下的監聽器
        lv.setOnItemClickListener(this);
        //設定 listView1 被長按的監聽器
        lv.setOnItemLongClickListener(this);
    }

    public void onItemClick(AdapterView<?> a, View v, int pos, long id) {
        Intent it = new Intent(this, Edit.class);
        it.putExtra("編號", pos+1);      //附加編號
        it.putExtra("備忘", aMemo[pos]); //附加備忘項目的內容
        startActivity(it);             	 //啟動 Edit 活動
    }

    public boolean onItemLongClick(AdapterView<?> a, View v, int pos, long id) {
        aMemo[pos] = (pos+1) + "."; //將內容清除 (只剩編號)
        aa.notifyDataSetChanged();  //通知 Adapter 要更新陣列內容
        return true;     			//傳回 true 表示此事件已處理
    }
}
