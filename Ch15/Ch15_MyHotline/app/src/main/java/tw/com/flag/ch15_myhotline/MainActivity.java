package tw.com.flag.ch15_myhotline;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener {

    static final String DB_NAME="HotlineDB";
    static final String TB_NAME="hotlist";
    static final int MAX=8;
    static final String[] FROM=new String[] {"name","phone","email"};
    SQLiteDatabase db;
    Cursor cur;
    SimpleCursorAdapter adapter;
    EditText etName,etPhone,etEmail;
    Button btInsert, btUpdate, btDelete;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName=(EditText)findViewById(R.id.etName);
        etPhone=(EditText)findViewById(R.id.etPhone);
        etEmail=(EditText)findViewById(R.id.etEmail);
        btInsert =(Button)findViewById(R.id.btInsert);
        btUpdate =(Button)findViewById(R.id.btUpdate);
        btDelete =(Button)findViewById(R.id.btDelete);

        // 開啟或建立資料庫
        db = openOrCreateDatabase(DB_NAME,  Context.MODE_PRIVATE, null);

        // 建立資料表
        String createTable="CREATE TABLE IF NOT EXISTS " + TB_NAME +
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " + //索引欄位
                "name VARCHAR(32), " +
                "phone VARCHAR(16), " +
                "email VARCHAR(64))";
        db.execSQL(createTable);

        cur=db.rawQuery("SELECT * FROM "+ TB_NAME, null);  // 查詢資料

        // 若查詢結果是空的則寫入 2 筆測試資料
        if(cur.getCount()==0){
            addData("旗標公司","02-23963257","service@flag.com.tw");
            addData("旗訊公司","02-23214335","service@cio.com.tw");
        }

        adapter=new SimpleCursorAdapter(this,
                R.layout.item, cur,
                FROM,
                new int[] {R.id.name,R.id.phone,R.id.email}, 0);

        lv=(ListView)findViewById(R.id.lv);
        lv.setAdapter(adapter);			 // 設定 Adapter
        lv.setOnItemClickListener(this); // 設定按下事件的監聽器
        requery();	// 呼叫自訂方法, 重新查詢及設定按鈕狀態
    }

    private void addData(String name, String phone, String email) {
        ContentValues cv=new ContentValues(3);	// 建立含 3 個欄位的 ContentValues物件
        cv.put(FROM[0], name);
        cv.put(FROM[1], phone);
        cv.put(FROM[2], email);

        db.insert(TB_NAME, null, cv);	// 新增1筆記錄
    }

    private void update(String name, String phone, String email, int id) {
        ContentValues cv=new ContentValues(3);
        cv.put(FROM[0], name);
        cv.put(FROM[1], phone);
        cv.put(FROM[2], email);

        db.update(TB_NAME, cv, "_id="+id, null);	// 更新 id 所指的欄位
    }

    private void requery() {	// 重新查詢的自訂方法
        cur=db.rawQuery("SELECT * FROM "+TB_NAME, null);
        adapter.changeCursor(cur);	//更改 Adapter的Cursor
        if(cur.getCount()==MAX) // 已達上限, 停用新增鈕
            btInsert.setEnabled(false);
        else
            btInsert.setEnabled(true);
        btUpdate.setEnabled(false);	// 停用更新鈕
        btDelete.setEnabled(false);	// 停用刪除鈕
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        cur.moveToPosition(position); //	移動 Cursor 至使用者選取的項目
        // 讀出姓名,電話,Email資料並顯示
        etName.setText(cur.getString(
                cur.getColumnIndex(FROM[0])));
        etPhone.setText(cur.getString(
                cur.getColumnIndex(FROM[1])));
        etEmail.setText(cur.getString(
                cur.getColumnIndex(FROM[2])));

        btUpdate.setEnabled(true);	// 啟用更新鈕
        btDelete.setEnabled(true);	// 啟用刪除鈕
    }

    public void onInsertUpdate(View v){
        String nameStr=etName.getText().toString().trim();
        String phoneStr=etPhone.getText().toString().trim();
        String emailStr=etEmail.getText().toString().trim();
        if(nameStr.length()==0 ||	// 任一欄位的內容為空即返回
                phoneStr.length()==0 ||
                emailStr.length()==0) return;

        if(v.getId()==R.id.btUpdate)	// 按更新鈕
            update(nameStr, phoneStr, emailStr, cur.getInt(0));
        else 							// 按新增鈕
            addData(nameStr, phoneStr, emailStr);

        requery();	// 更新 Cursor 內容
    }

    public void onDelete(View v){	// 刪除鈕的On Click事件方法
        db.delete(TB_NAME, "_id="+cur.getInt(0),null);
        requery();
    }

    public void call(View v){	// 打電話
        String uri="tel:" + cur.getString(
                cur.getColumnIndex(FROM[1]));
        Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(it);
    }

    public void mail(View v){	// 寄送電子郵件
        String uri="mailto:"+cur.getString(
                cur.getColumnIndex(FROM[2]));
        Intent it = new Intent(Intent.ACTION_SENDTO, Uri.parse(uri));
        startActivity(it);
    }

}
