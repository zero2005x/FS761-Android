# 變更日誌 (CHANGELOG)

## 專案說明

本專案基於旗標科技出版的《Android App 程式設計教本之無痛起步 - 使用 Android Studio 2.X 開發環境》書籍範例程式碼。為了適應現代 Android 開發環境，已進行必要的更新與調整。

## 版本資訊

### [1.0.0] - 2025-11-02

#### 📝 與原書範例的主要差異

##### ✨ 更新項目

1. **開發工具升級**

   - Android Studio: 從 2.X 升級至 Hedgehog (2023.1.1) 或更新版本
   - Gradle: 升級至 8.7.3
   - Gradle Plugin: 升級至 com.android.tools.build:gradle:8.7.3

2. **SDK 版本更新**

   - Compile SDK Version: 33 (原書使用較舊版本)
   - Target SDK Version: 33
   - Min SDK Version: 21 (保持對舊版本的相容性)

3. **相依套件現代化**

   - 從 Android Support Library 遷移至 AndroidX
   - AndroidX AppCompat: 1.6.1
   - AndroidX Espresso: 3.5.1 (測試框架)
   - JUnit: 4.13.2

4. **專案結構改善**
   - 加入根目錄 `.gitignore` 檔案
   - 建立完整的 `README.md` 專案說明文件
   - 加入 `LICENSE.md` 授權聲明
   - 建立本 `CHANGELOG.md` 記錄版本變更

##### 🔧 技術調整

1. **命名空間 (Namespace)**

   - 在 `build.gradle` 中加入 `namespace` 宣告
   - 例如：`namespace 'tw.com.flag.ch01_hello'`

2. **測試設定**

   - 在 `build.gradle` 中加入 `testOptions` 設定
   - 啟用單元測試預設值回傳

3. **排除檔案**
   - `local.properties` - 包含本機路徑設定，因人而異
   - `.gradle/` - Gradle 快取資料夾
   - `.idea/` - IntelliJ IDEA / Android Studio 設定檔

##### ⚠️ 已知差異

1. **編譯環境差異**

   - 原書使用 Android Studio 2.X，本專案建議使用最新穩定版
   - 可能需要根據您的開發環境調整 Gradle 版本

2. **API 變更**

   - 某些 Android API 在新版本中已被棄用或修改
   - 部分範例可能需要小幅調整以符合最新的 Android 開發規範

3. **第三方函式庫**
   - 原書中使用的某些第三方函式庫可能已不再維護
   - 建議使用官方推薦的替代方案

##### 📦 專案結構

```
FS761_Android/
├── .gitignore           # [新增] Git 版本控制忽略檔案
├── README.md            # [新增] 專案說明文件
├── LICENSE.md           # [新增] 授權聲明
├── CHANGELOG.md         # [新增] 本檔案
├── prompt.txt           # 原有檔案
├── Ch01/ ~ Ch16/        # 各章節範例程式
│   ├── [專案名稱]/
│   │   ├── .gitignore   # 各專案的 gitignore
│   │   ├── build.gradle # [已更新] Gradle 8.7.3
│   │   ├── app/
│   │   │   └── build.gradle  # [已更新] AndroidX
│   │   └── gradle/
└── FlagAPI/             # 旗標 API
```

##### 🚀 使用建議

1. **初次使用**

   - 請先閱讀 `README.md` 了解環境需求
   - 確保已安裝 Android Studio 與必要的 SDK
   - 開啟專案時，Android Studio 會自動建立 `local.properties`

2. **學習路徑**

   - 建議按照章節順序學習：Ch01 → Ch02 → ... → Ch16
   - 每個章節都是獨立的 Android Studio 專案
   - 可以同時開啟多個專案進行比較學習

3. **常見問題**
   - 如遇到編譯錯誤，請先檢查 Gradle 同步狀態
   - 確認 SDK 版本與專案需求匹配
   - 參考 `README.md` 的常見問題章節

##### 📚 參考資源

- [Android 官方開發者文件](https://developer.android.com/)
- [AndroidX 遷移指南](https://developer.android.com/jetpack/androidx/migrate)
- [Gradle 版本相容性](https://developer.android.com/studio/releases/gradle-plugin)

---

## 未來計畫

- [ ] 持續更新相依套件版本
- [ ] 適配最新 Android SDK 版本
- [ ] 加入更多註解說明
- [ ] 提供中文介面的範例
- [ ] 加入單元測試範例

## 貢獻指南

歡迎提出 Issue 或 Pull Request 來改進本專案。請參考 `README.md` 中的貢獻指南。

---

**最後更新：** 2025-11-02
