# 💼 求職作品集 Job Portfolio(簡易版、無CSS)

此專案為我應徵工作所準備的作品集，展示我在前後端開發與系統整合上的能力。

![會員中心示意圖](https://github.com/user-attachments/assets/c20c3a12-5948-4689-b0e9-38abeaafacf9)

---

## 🔐 會員中心系統 - 前後端整合設計

本模組為一個具備身分驗證機制的會員資料管理系統，實作安全的 API 串接與前端畫面互動。

### 🖥️ 前端：`userCenter.html` + `usercenter.js`

- 頁面載入時，自動呼叫 `initializeUserCenter()`，向後端取得並渲染用戶資料。
- 點擊「更新資料」按鈕會觸發 `updateProfile()`，以 `PUT` 方法將更新資訊送至後端。
- 使用 `fetch` 進行 API 串接，並於 HTTP Header 中夾帶 JWT Token 進行身分驗證。

### 🧩 後端：`UserController.java`

- 提供兩個 RESTful API 端點 `/api/users/{id}`：
  - `GET`：查詢使用者資料
  - `PUT`：更新使用者資料
- 每次請求皆驗證 JWT Token，確保資料存取安全。
- 回傳 JSON 格式的資料供前端渲染。

---

## 🚀 使用技術與架構

- **前端技術**：HTML、JavaScript (Vanilla)、Fetch API
- **後端技術**：Java（Spring Boot）、RESTful API
- **身分驗證**：JWT（JSON Web Token）
- **版本控制**：Git

---

## 📌 開發目的

本系統模組為求職作品之一，展示我在安全性設計、API 整合與系統架構上的能力，適合應用於會員管理、個人資料中心等需求情境。

