// usercenter.js

// 頁面載入時自動執行，取得用戶資料並填入表單
async function initializeUserCenter() {
    const userId = localStorage.getItem('userId');
    const token = localStorage.getItem('authToken');
    if (!userId || !token) return;
  
    const res = await fetch(`${window.API_BASE_URL}/users/${userId}`, {
      headers: { 'Authorization': `Bearer ${token}` }
    });
    if (res.ok) {
      const data = await res.json();
      document.getElementById('name').value = data.name || '';
      document.getElementById('email').value = data.email || '';
      document.getElementById('phone').value = data.phone || '';
      document.getElementById('address').value = data.address || '';
    }
  }

  
// 按下更新資料按鈕時，收集表單資料並送出 API 請求
async function updateProfile() {
    const userId = localStorage.getItem('userId');
    const token = localStorage.getItem('authToken');
    const name = document.getElementById('name').value;
    const phone = document.getElementById('phone').value;
    const address = document.getElementById('address').value;
  
    const res = await fetch(`${window.API_BASE_URL}/users/${userId}`, {
      method: 'PUT',
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ name, phone, address })
    });
    if (res.ok) {
      showToast('更新成功');
    } else {
      showToast('更新失敗');
    }
  }