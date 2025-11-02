# FS761 Android 程式設計專案

## 專案簡介

本專案包含《Android App 程式設計教本之無痛起步 - 使用 Android Studio 2.X 開發環境》一書的範例程式碼。

**書籍資訊：**

- **書名：** Android App 程式設計教本之無痛起步 - 使用 Android Studio 2.X 開發環境
- **作者：** 施威銘 主編
- **書號：** FS761
- **ISBN：** 9789863123989
- **類別：** 程式設計／資料庫程式設計
- **出版社：** 旗標科技

## 專案結構

專案按照書籍章節組織，每個資料夾對應一個章節的範例：

```
FS761_Android/
├── Ch01/          # 第一章：Hello Android
├── Ch02/          # 第二章：基本 UI 元件（Button、EditText）
├── Ch03/          # 第三章：版面配置（Layout）
├── Ch04/          # 第四章：計數器與訊息傳遞
├── Ch05/          # 第五章：ImageView 與選單
├── Ch06/          # 第六章：ListView 與 Adapter
├── Ch07/          # 第七章：對話框與日期時間選擇器
├── Ch08/          # 第八章：多個 Activity
├── Ch09/          # 第九章：Intent 應用
├── Ch10/          # 第十章：相機應用
├── Ch11/          # 第十一章：多媒體播放器
├── Ch12/          # 第十二章：感測器應用
├── Ch13/          # 第十三章：WebView 網頁瀏覽
├── Ch14/          # 第十四章：位置與地圖
├── Ch15/          # 第十五章：SQLite 資料庫
├── Ch16/          # 第十六章：iTank 遊戲專案
└── FlagAPI/       # 旗標 API
```

## 開發環境需求

### 軟體需求

- **Android Studio:** 建議使用 Android Studio Hedgehog (2023.1.1) 或更新版本
- **JDK:** Java Development Kit 11 或更高版本
- **Gradle:** 8.7.3（專案已包含 Gradle Wrapper）
- **Android SDK:**
  - Compile SDK Version: 33
  - Min SDK Version: 21
  - Target SDK Version: 33

### 硬體需求

- **記憶體：** 建議 8GB RAM 以上
- **硬碟空間：** 至少 10GB 可用空間（包含 Android SDK 與模擬器）
- **處理器：** 建議使用支援虛擬化技術的處理器（用於 Android 模擬器）

## 專案特性與差異說明

### 與原書範例的主要差異

1. **Gradle 版本更新：** 已升級至 Gradle 8.7.3，以支援最新的 Android Studio
2. **相依套件更新：** 使用 AndroidX 函式庫取代舊版 Support Library
3. **SDK 版本：** 更新至 compileSdkVersion 33 和 targetSdkVersion 33
4. **缺少 local.properties：** 此檔案包含本機路徑設定，已被排除不納入版本控制

## 安裝與設定

### 1. Clone 專案

```bash
git clone <repository-url>
cd FS761_Android
```

### 2. 用 Android Studio 開啟專案

1. 開啟 Android Studio
2. 選擇 "Open an Existing Project"
3. 導航到任一章節的子專案資料夾（例如：`Ch01/Ch01_hello`）
4. 點選 "OK" 開啟專案

### 3. 設定 SDK 路徑

第一次開啟專案時，Android Studio 會自動建立 `local.properties` 檔案。如果沒有，請手動建立並加入以下內容：

```properties
sdk.dir=C\:\\Users\\<你的使用者名稱>\\AppData\\Local\\Android\\Sdk
```

（將路徑替換為你的 Android SDK 實際安裝路徑）

### 4. 同步 Gradle

開啟專案後，Android Studio 會自動提示同步 Gradle。點選 "Sync Now" 等待同步完成。

### 5. 執行應用程式

1. 連接 Android 裝置或啟動模擬器
2. 在 Android Studio 中點選 "Run" 按鈕（綠色三角形）
3. 選擇目標裝置
4. 等待應用程式安裝並執行

## 常見問題

### Q1: 開啟專案時出現 "SDK location not found" 錯誤

**解決方法：** 在專案根目錄建立 `local.properties` 檔案，並指定 SDK 路徑（參考上方設定步驟）。

### Q2: Gradle 同步失敗

**解決方法：**

1. 確認網路連線正常
2. 檢查 Gradle 版本相容性
3. 嘗試 File → Invalidate Caches / Restart
4. 手動執行 `./gradlew clean build`

### Q3: 編譯錯誤或相依套件問題

**解決方法：**

1. 確認 `build.gradle` 中的相依套件版本
2. 更新 Android SDK 與 Build Tools
3. 清理並重建專案：Build → Clean Project → Rebuild Project

### Q4: 模擬器無法啟動

**解決方法：**

1. 確認 BIOS 已啟用虛擬化技術（Intel VT-x 或 AMD-V）
2. 在 Windows 上，確認 Hyper-V 已正確設定
3. 使用 AVD Manager 重新建立模擬器

## 學習建議

1. **循序漸進：** 建議按照章節順序學習，每個章節都建立在前面的基礎上
2. **動手實作：** 每個範例都親自執行並修改，了解程式碼的運作方式
3. **參考書籍：** 搭配原書閱讀，可獲得更完整的說明與理論基礎
4. **實驗修改：** 嘗試修改範例程式碼，觀察結果變化，加深理解

## 授權聲明

本專案的範例程式碼來自旗標科技出版的《Android App 程式設計教本之無痛起步》一書。

- **教育用途：** 本專案僅供學習與教育用途使用
- **商業使用：** 如需商業使用，請聯繫原書出版社取得授權
- **版權聲明：** 所有範例程式碼的智慧財產權歸原作者與出版社所有

## 貢獻指南

歡迎提出問題回報（Issue）或改進建議。如果發現程式碼錯誤或有更好的實作方式，請：

1. Fork 本專案
2. 建立您的特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交您的修改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 開啟 Pull Request

## 聯絡資訊

如有任何問題或建議，歡迎透過以下方式聯繫：

- **GitHub Issues：** 在本專案的 Issues 頁面提出問題
- **原書出版社：** [旗標科技](https://www.flag.com.tw/)

## 相關資源

- [Android 開發者官方網站](https://developer.android.com/)
- [Android Studio 下載](https://developer.android.com/studio)
- [Android Developers 正體中文](https://developer.android.com/intl/zh-tw)
- [旗標科技官網](https://www.flag.com.tw/)

---

**最後更新日期：** 2025-11-02  
**專案維護者：** 請填入您的名稱或組織
