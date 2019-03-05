package cn.imeina.avm.util;

import cn.hutool.core.util.IdUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

/**
 * {@link "https://github.com/auth0/java-jwt"}
 *
 * @author gaopengfei
 */
public class JWTUtil {

    private static final Logger LOGGER = LogManager.getLogger(JWTUtil.class);

    /**
     * JWT 加密密钥
     */
    private static final String SECRET = "a@k#i$d%<q(w&)e_r=i+b*c>2/0?1,9";
    /**
     * JWT "iss"(Issuer) Claim
     */
    private static final String ISSUER = "danyuantech.com";
    /**
     * JWT "sub"(Subject) Claim
     */
    private static final String SUBJECT = "all";
    /**
     * JWT "exp"(Expires) Claim
     */
    private static final long EXPIRES_TIME = 60 * 60 * 1000 * 24;

    /**
     * Create token by JWT
     * {@link "https://tools.ietf.org/html/rfc7519#section-4.1"}
     *
     * @return token
     */
    public static String createJWT(String uid) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        long currentTimeMillis = System.currentTimeMillis();
        String token = JWT.create()
                .withIssuer(ISSUER)//发行者
                .withSubject(SUBJECT)//主题
                .withAudience()//受众者
                .withNotBefore(new Date(currentTimeMillis))//在此时间前JWT不可用
                .withIssuedAt(new Date(currentTimeMillis))//JWT签发时间
                .withExpiresAt(new Date(currentTimeMillis + EXPIRES_TIME))//过期时间
                .withJWTId(IdUtil.simpleUUID())//增加JTD作为一次性Token，防止重放攻击
                .withClaim("uid", uid)
                .sign(algorithm);
        LOGGER.debug("Generate Token=" + token);
        return token;
    }

    /**
     * Verify JWT
     *
     * @param token token
     * @return if token valid return true or false.
     */
    public static boolean verifyJWT(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            LOGGER.debug("Verify Token：\n[HEADER:" + decodedJWT.getHeader()
                    + "\nPAYLOAD:" + decodedJWT.getPayload()
                    + "\nSIGNATURE:" + decodedJWT.getSignature()
                    + "]");
            return true;
        } catch (JWTVerificationException exception) {
            LOGGER.debug("The token has an invalid signature or the Claim requirement is not met...",
                    exception);
            return false;
        }
    }

    /**
     * 解析Token
     * @param token token
     */
    public void parseClaimsFromToken(String token){
        DecodedJWT decodedJWT = JWT.decode(token);
        String header = decodedJWT.getHeader();
        String payload = decodedJWT.getPayload();
        String signature = decodedJWT.getSignature();
        LOGGER.debug("[JWT decoder decode token：\nheader：" + header + "\npayload：" + payload + "\nsignature：" + signature + "]");
    }
}
