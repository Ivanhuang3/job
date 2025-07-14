package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.*;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService; // 用來驗證 token

    // 取得個人資料
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(
            @PathVariable Long id,
            @RequestHeader("Authorization") String authHeader) {
        // 驗證 token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(Map.of("error", "缺少授權"));
        }
        String token = authHeader.substring(7);
        String userEmail = jwtService.extractEmail(token);

        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = userOptional.get();

        // 驗證 token 是否屬於該用戶
        if (!user.getEmail().equals(userEmail)) {
            return ResponseEntity.status(403).body(Map.of("error", "無權限"));
        }

        // 回傳用戶資料
        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("email", user.getEmail());
        response.put("fullName", user.getFullName());
        response.put("phoneNumber", user.getPhoneNumber());
        response.put("address", user.getAddress());
        // ...其他欄位...

        return ResponseEntity.ok(response);
    }

    // 更新個人資料
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @RequestBody Map<String, String> updateData,
            @RequestHeader("Authorization") String authHeader) {
        // 驗證 token
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(Map.of("error", "缺少授權"));
        }
        String token = authHeader.substring(7);
        String userEmail = jwtService.extractEmail(token);

        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        User user = userOptional.get();

        // 驗證 token 是否屬於該用戶
        if (!user.getEmail().equals(userEmail)) {
            return ResponseEntity.status(403).body(Map.of("error", "無權限"));
        }

        // 更新資料
        if (updateData.containsKey("fullName")) {
            user.setFullName(updateData.get("fullName"));
        }
        if (updateData.containsKey("phoneNumber")) {
            user.setPhoneNumber(updateData.get("phoneNumber"));
        }
        if (updateData.containsKey("address")) {
            user.setAddress(updateData.get("address"));
        }
        userRepository.save(user);

        // 回傳更新後的資料
        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("email", user.getEmail());
        response.put("fullName", user.getFullName());
        response.put("phoneNumber", user.getPhoneNumber());
        response.put("address", user.getAddress());
        // ...其他欄位...

        return ResponseEntity.ok(response);
    }
}