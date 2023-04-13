package com.galaxy.empvue;

import com.galaxy.empvue.utils.JwtUtils;
import com.auth0.jwt.interfaces.Claim;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    public static void main(String[] args) throws IOException {
        // 测试加密
        Map<String, String> payload = new HashMap<>();
        payload.put("name", "John Doe");
        payload.put("age", "30");
        String token = JwtUtils.encode("payload");
        System.out.println("Token: " + token);

        // 测试解密
        Map<String, Claim> claims = JwtUtils.decodeToken(token);
        System.out.println("Name: " + claims.get("name").asString());
        System.out.println("Age: " + claims.get("age").asInt());
    }
}
