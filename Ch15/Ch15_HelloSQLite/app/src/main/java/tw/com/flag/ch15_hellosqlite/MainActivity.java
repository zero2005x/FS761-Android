package tw.com.flag.ch15_hellosqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static final String db_name="testDB";	// 資料庫名稱
    static final String tb_name="test";		// 資料表名稱
    SQLiteDatabase db;	//資料庫

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 開啟或建立資料庫
        db = openOrCreateDatabase(db_name,  Context.MODE_PRIVATE, null);

        String createTable="CREATE TABLE IF NOT EXISTS " +
                tb_name +			// 資料表名稱
                "(name VARCHAR(32), " +	//姓名欄位
                "phone VARCHAR(16), " +	//電話欄位
                "email VARCHAR(64))";	//Email欄位
        db.execSQL(createTable);	// 建立資料表

        // 插入 2筆資料
        addData("Flag Publishing Co.","02-23963257","service@flag.com.tw");
        addData("PCDIY Magazine","02-23214335","service@pcdiy.com.tw");

        TextView txv=(TextView)findViewById(R.id.txv);
        txv.setText("資料庫檔路徑: " + db.getPath() + "\n\n" +   // 取得及顯示資料庫資訊
                "資料庫分頁大小: " + db.getPageSize() + " Bytes\n\n" +
                "資料庫大小上限: " + db.getMaximumSize() + " Bytes");

        db.close();		// 關閉資料庫
    }

    private void addData(String name, String phone, String email) {
        ContentValues cv=new ContentValues(3);	// 建立含3個資料項目的物件
        cv.put("name", name);
        cv.put("phone", phone);
        cv.put("email", email);

        db.insert(tb_name, null, cv);	// 將資料加到資料表
    }
}
