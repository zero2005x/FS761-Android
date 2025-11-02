package tw.com.flag.ch10_camera;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Uri imgUri;    //用來參照拍照存檔的 Uri 物件
    ImageView imv; //用來參照 ImageView 物件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);//設定螢幕不隨手機旋轉
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//設定螢幕直向顯示
        imv = (ImageView)findViewById(R.id.imageView);  //參照 Layout 中的 ImageView 元件
    }

    public void onGet(View v) {
        if (ActivityCompat.checkSelfPermission(this,   //檢查是否已取得寫入權限
                Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            //尚未取得權限
            ActivityCompat.requestPermissions(this,  //向使用者要求允許寫入權限
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    200);
        }
        else {
            //已經取得權限
            savePhoto();  //拍照並存檔
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 200){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){ //使用者允許權限
                savePhoto();  //拍照並存檔
            }
            else { //使用者拒絕權限
                Toast.makeText(this, "程式需要寫入權限才能運作", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void savePhoto () {
        imgUri =  getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new ContentValues());
        Intent it = new Intent("android.media.action.IMAGE_CAPTURE");
        it.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);    //將 uri 加到拍照 Intent 的額外資料中
        startActivityForResult(it, 100);
    }

    public void onPick(View v) {
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);    //動作設為 "選取內容"
        it.setType("image/*");            //設定要選取的媒體類型為：所有類型的圖片
        startActivityForResult(it, 101);  //啟動意圖, 並要求傳回選取的圖檔
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK) {   //要求的意圖成功了
            switch(requestCode) {
                case 100: //拍照
                    Intent it = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, imgUri);//設為系統共享媒體檔
                    sendBroadcast(it);
                    break;
                case 101: //選取相片
                    imgUri = data.getData();  //取得選取相片的 Uri
                    break;
            }
            showImg();  //顯示 imgUri 所指明的相片
        }
        else {
            Toast.makeText(this, requestCode==100? "沒有拍到照片":
                    "沒有選取相片", Toast.LENGTH_LONG)
                    .show();
        }
    }

    void showImg() {
        int iw, ih, vw, vh;
        boolean needRotate;  //用來儲存是否需要旋轉

        BitmapFactory.Options option = new BitmapFactory.Options(); //建立選項物件
        option.inJustDecodeBounds = true;      //設定選項：只讀取圖檔資訊而不載入圖檔

        //讀取圖檔資訊存入 Option 中
        try {
            BitmapFactory.decodeStream(getContentResolver().openInputStream(imgUri), null, option);
        }
        catch (IOException e) {
            Toast.makeText(this, "讀取照片資訊時發生錯誤", Toast.LENGTH_LONG).show();
            return;
        }

        iw = option.outWidth;   //由 option 中讀出圖檔寬度
        ih = option.outHeight;  //由 option 中讀出圖檔高度
        vw = imv.getWidth();    //取得 ImageView 的寬度
        vh = imv.getHeight();   //取得 ImageView 的高度

        int scaleFactor;
        if(iw<ih) {    //如果圖片的寬度小於高度
            needRotate = false;       				//不需要旋轉
            scaleFactor = Math.min(iw/vw, ih/vh);   // 計算縮小比率
        }
        else {
            needRotate = true;       				//需要旋轉
            scaleFactor = Math.min(iw/vh, ih/vw);   // 將 ImageView 的寬、高互換來計算縮小比率
        }

        option.inJustDecodeBounds = false;  //關閉只載入圖檔資訊的選項
        option.inSampleSize = scaleFactor;  //設定縮小比例, 例如 2 則長寬都將縮小為原來的 1/2

        //載入圖檔
        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(imgUri), null, option);
        } catch (IOException e) {
            Toast.makeText(this, "無法取得照片", Toast.LENGTH_LONG).show();
        }

        if(needRotate) { //如果需要旋轉
            Matrix matrix = new Matrix();  //建立 Matrix 物件
            matrix.postRotate(90);         //設定旋轉角度
            bmp = Bitmap.createBitmap(bmp , //用原來的 Bitmap 產生一個新的 Bitmap
                    0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true);
        }

        imv.setImageBitmap(bmp); //顯示圖片
    }
}
