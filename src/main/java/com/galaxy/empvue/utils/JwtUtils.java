package com.galaxy.empvue.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JwtUtils {
    public static String encode(String uname){
        ConcurrentHashMap<String, Object> header = new ConcurrentHashMap<>();
        header.put("alg","SH256");
        header.put("typ","JWT");
        //签发日期
        Date issueTime = new Date();
        //过期日期
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,7200);
        return JWT.create()
                .withHeader(header)
                .withClaim("username",uname)
                .withSubject("test")
                .withIssuedAt(issueTime)
                .withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256("galaxy-jwt"));
    }
    /**
     *  解析 token信息 并且返回 负载信息
     * @param token
     * @return
     */
    public  static Map<String, Claim> decodeToken(String  token){
        //创建 token的校验器
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("galaxy-jwt")).build();
        // 将token进行解析
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        //载荷信息都在map集合中
        return decodedJWT.getClaims();
    }
}
