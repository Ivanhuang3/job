# job
for job resume
<img width="1877" height="828" alt="image" src="https://github.com/user-attachments/assets/c20c3a12-5948-4689-b0e9-38abeaafacf9" />


#我的會員中心前後端設計是這樣：> - 前端 userCenter.html 會在網頁載入時自動呼叫 initializeUserCenter()函式，頁面更新後自動get並顯示用戶資料。更新資料按鈕則綁定 updateProfile()，點擊後會發送 PUT 請求更新資料。> - 這些功能都寫在 usercenter.js，用 fetch 串接後端 API，並且在 header 夾帶 JWT token 做身分驗證。> - 後端 UserController.java 提供 /api/users/{id} 的 GET 和 PUT API，分別負責查詢與更新個人資料，並且會驗證 JWT token，確保安全性並回傳資料渲染到前端
