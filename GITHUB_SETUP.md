# GitHub 發佈指南

本文件說明如何將 FS761_Android 專案發佈到 GitHub。

## 步驟一：檢查專案準備狀態

確認以下檔案已存在於專案根目錄：

- ✅ `.gitignore` - Git 版本控制忽略檔案
- ✅ `README.md` - 專案說明文件
- ✅ `LICENSE.md` - 授權聲明
- ✅ `CHANGELOG.md` - 變更日誌

## 步驟二：初始化本地 Git 儲存庫

在專案根目錄執行以下命令：

```powershell
# 切換到專案目錄
cd "c:\Users\liangtinglin\Documents\淡江課程\行動裝置程式設計\FS761_Android"

# 初始化 Git 儲存庫
git init

# 設定使用者資訊（如果尚未設定）
git config user.name "您的名字"
git config user.email "您的Email"

# 加入所有檔案到暫存區
git add .

# 檢查狀態（可選）
git status

# 提交初始版本
git commit -m "Initial commit: Android Studio 範例專案

- 包含 Ch01 到 Ch16 的所有範例程式
- 已升級至 Gradle 8.7.3 與 AndroidX
- 加入專案文件：README, LICENSE, CHANGELOG
- 來自《Android App 程式設計教本之無痛起步》(FS761)"
```

## 步驟三：在 GitHub 上建立儲存庫

1. 登入 [GitHub](https://github.com/)
2. 點擊右上角的 `+` 號，選擇 `New repository`
3. 填寫儲存庫資訊：
   - **Repository name:** `FS761-Android` 或 `Android-Programming-Examples`
   - **Description:** `Android App 程式設計教本範例程式（旗標 FS761）`
   - **Visibility:**
     - `Public` - 任何人都可以看到
     - `Private` - 僅您和指定的協作者可以看到
   - **不要**勾選 "Initialize this repository with a README"（我們已經有了）
4. 點擊 `Create repository`

## 步驟四：連結本地儲存庫到 GitHub

複製 GitHub 提供的遠端儲存庫 URL（應該類似 `https://github.com/您的使用者名稱/FS761-Android.git`），然後執行：

```powershell
# 加入遠端儲存庫（將 <URL> 替換為您的儲存庫 URL）
git remote add origin <URL>

# 例如：
# git remote add origin https://github.com/yourusername/FS761-Android.git

# 檢查遠端儲存庫設定
git remote -v

# 推送到 GitHub（首次推送使用 -u 參數）
git push -u origin master
# 或者如果預設分支是 main：
# git push -u origin main
```

## 步驟五：驗證上傳成功

1. 在瀏覽器中打開您的 GitHub 儲存庫頁面
2. 確認所有檔案都已正確上傳
3. 檢查 README.md 是否正確顯示在儲存庫首頁

## 步驟六：設定儲存庫（選用）

### 加入主題標籤（Topics）

在 GitHub 儲存庫頁面，點擊右側的 `Add topics`，加入相關標籤：

- `android`
- `android-studio`
- `android-development`
- `java`
- `mobile-development`
- `learning`
- `examples`
- `tutorial`
- `chinese`
- `taiwan`

### 啟用 GitHub Pages（如果需要）

如果您想建立專案網站：

1. 進入 Settings → Pages
2. 選擇 Source 為 `main` 分支
3. 點擊 Save

### 設定 About 區塊

在儲存庫首頁右側，點擊齒輪圖示編輯 About：

- **Description:** Android App 程式設計教本範例程式（旗標 FS761）
- **Website:** 可加入相關連結
- **Topics:** 如上述標籤

## 步驟七：後續維護

### 更新專案

當您對專案進行修改後：

```powershell
# 查看變更
git status

# 加入變更的檔案
git add <檔案名稱>
# 或加入所有變更
git add .

# 提交變更
git commit -m "簡短描述您的變更"

# 推送到 GitHub
git push
```

### 建立分支（進階功能）

```powershell
# 建立並切換到新分支
git checkout -b feature/新功能名稱

# 在新分支上工作...

# 推送新分支到 GitHub
git push -u origin feature/新功能名稱
```

### 處理大型檔案

如果有超過 100MB 的檔案，需要使用 Git LFS：

```powershell
# 安裝 Git LFS
git lfs install

# 追蹤大型檔案類型（例如 .apk 檔案）
git lfs track "*.apk"

# 提交 .gitattributes 檔案
git add .gitattributes
git commit -m "Add Git LFS tracking"
```

## 常見問題

### Q1: 推送時要求輸入帳號密碼

從 2021 年起，GitHub 不再支援密碼驗證，需要使用 Personal Access Token (PAT)：

1. 前往 GitHub Settings → Developer settings → Personal access tokens → Tokens (classic)
2. 點擊 "Generate new token (classic)"
3. 選擇權限（至少需要 `repo`）
4. 複製產生的 token
5. 在 Git 要求密碼時，貼上 token（而非 GitHub 密碼）

### Q2: 推送失敗：檔案太大

檢查 `.gitignore` 是否正確設定，確保不會推送：

- `build/` 資料夾
- `.gradle/` 資料夾
- `.idea/` 資料夾
- `*.apk` 檔案（編譯產物）

### Q3: 如何取消已加入的檔案

```powershell
# 從 Git 中移除但保留本地檔案
git rm --cached <檔案名稱>

# 提交變更
git commit -m "Remove file from Git"
```

### Q4: 想要修改最後一次提交訊息

```powershell
# 修改最後一次提交的訊息
git commit --amend -m "新的提交訊息"

# 強制推送（小心使用！）
git push --force
```

## 分享與協作

### 邀請協作者

1. 進入儲存庫 Settings → Collaborators
2. 點擊 "Add people"
3. 輸入對方的 GitHub 使用者名稱或 Email

### 接受貢獻

當有人提交 Pull Request：

1. 檢視變更內容
2. 在 GitHub 上討論
3. 如果滿意，點擊 "Merge pull request"

## 版權注意事項

⚠️ **重要提醒：**

由於本專案包含教科書的範例程式碼，請注意：

1. 已在 `LICENSE.md` 中明確說明授權條款
2. 在 `README.md` 中註明書籍來源資訊
3. 建議將儲存庫設為 **Public**，以便其他學習者參考
4. 如有商業用途需求，請聯繫原出版社取得授權

## 參考資源

- [GitHub 官方文件](https://docs.github.com/)
- [Git 基礎教學](https://git-scm.com/book/zh-tw/v2)
- [GitHub Flow 工作流程](https://guides.github.com/introduction/flow/)
- [Markdown 語法說明](https://guides.github.com/features/mastering-markdown/)

---

**完成此指南後，您的專案將可供全球開發者存取！** 🎉
