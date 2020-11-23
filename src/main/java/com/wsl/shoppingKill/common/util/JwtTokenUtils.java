package com.wsl.shoppingKill.common.util;

import com.wsl.shoppingKill.constant.JwtEnum;
import com.wsl.shoppingKill.obj.bo.UserBO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

/**
 * @author WangShilei
 * @date 2020/11/23-10:41
 **/
public class JwtTokenUtils {
    private static Key getKeyInstance(){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] bytes = DatatypeConverter.parseBase64Binary("mall-user");
        return new SecretKeySpec(bytes,signatureAlgorithm.getJcaName());
    }

    /**
     * 生成token的方法
     * @param userBO :
     * @param expire :
     * @return String
     */
    public static String generatorToken(UserBO userBO, int expire){
        return Jwts.builder()
                .claim(JwtEnum.KEY_USER_ID,userBO.getId())
                .claim(JwtEnum.KEY_USER_NAME,userBO.getName())
                .claim(JwtEnum.KEY_USER_URL,userBO.getUrl())
                .claim(JwtEnum.KEY_USER_FLAG,userBO.getFlag())
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                .signWith(SignatureAlgorithm.HS256,getKeyInstance()).compact();
    }

    /**
     * 根据token获取token中的信息
     * @param token:
     * @return JwtInfo:
     */
    public static UserBO getTokenInfo(String token){
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        UserBO userBO = new UserBO();
        userBO.setId(Long.parseLong(claims.get(JwtEnum.KEY_USER_ID).toString()))
                .setName(claims.get(JwtEnum.KEY_USER_NAME).toString())
                .setUrl(claims.get(JwtEnum.KEY_USER_URL).toString())
                .setFlag(Integer.parseInt(claims.get(JwtEnum.KEY_USER_FLAG).toString()));
        return userBO;
    }
}
