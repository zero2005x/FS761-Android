package tw.com.flag.ch10_camera;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
        imv = (ImageView)findViewById(R.id.imageView);  //參照 Layout 中的 ImageView 元件
    }

    public void onGet(View v) throws IOException {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    200);
        }
        else {
            getPhoto();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 200){
            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    &&grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getPhoto();
            }else{
                Toast.makeText(this, "程式需要寫入權限才能運作", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void getPhoto () {
        imgUri =  getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new ContentValues());
        Intent it = new Intent("android.media.action.IMAGE_CAPTURE");
        it.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);    //將 uri 加到拍照 Intent 的額外資料中
        startActivityForResult(it, 100);
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK && requestCode==100) {
            showImg();
        }
        else {
            Toast.makeText(this, "沒有拍到照片", Toast.LENGTH_LONG).show();
        }
    }

    void showImg() {
        int iw, ih, vw, vh;

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

        int scaleFactor = Math.min(iw/vw, ih/vh); // 計算縮小比率

        option.inJustDecodeBounds = false;  //關閉只載入圖檔資訊的選項
        option.inSampleSize = scaleFactor;  //設定縮小比例, 例如 2 則長寬都將縮小為原來的 1/2

        //載入圖檔
        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(imgUri), null, option);
        } catch (IOException e) {
            Toast.makeText(this, "無法取得照片", Toast.LENGTH_LONG).show();
        }
        imv.setImageBitmap(bmp);
    }
}
