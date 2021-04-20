package com.wsl.shoppingkill.component.jwt;

import com.wsl.shoppingkill.common.util.DateUtil;
import com.wsl.shoppingkill.obj.constant.JwtEnum;
import com.wsl.shoppingkill.obj.bo.UserBO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @author WangShilei
 * @date 2020/11/23-10:41
 **/
@Component
public class JwtComponent {

    @Value("${jwt.expire}")
    private Integer expire;

    @Value("${jwt.keys}")
    private String keys;

    private Key getKeyInstance() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] bytes = DatatypeConverter.parseBase64Binary(keys);
        return new SecretKeySpec(bytes, signatureAlgorithm.getJcaName());
    }

    /**
     * 生成token的方法
     *
     * @param userBO :
     * @return String
     */
    public String getToken(UserBO userBO) {
        return Jwts.builder()
                .claim(JwtEnum.KEY_USER_ID, userBO.getId())
                .claim(JwtEnum.KEY_USER_FLAG, userBO.getFlag())
                .setExpiration(DateTime.now().plusSeconds(expire).toDate())
                .signWith(SignatureAlgorithm.HS256, getKeyInstance()).compact();

    }

    /**
     * 根据token获取token中的信息
     *
     * @param token:
     * @return JwtInfo:
     */
    public UserBO getTokenInfo(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        UserBO userBO = new UserBO();
        userBO.setId(Long.parseLong(claims.get(JwtEnum.KEY_USER_ID).toString()))
                .setFlag(Integer.parseInt(claims.get(JwtEnum.KEY_USER_FLAG).toString()));
        return userBO;
    }

    public Long getTokenTime(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();

        return DateUtil.distanceSecond(new Date(), claims.getExpiration());
    }
}
