package ninja.backend.security;

import ninja.backend.config.CustomProperties;
import ninja.backend.model.enumeration.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Date;


@Service
public class JWTService {

    private static final String AUTHORITIES_KEY = "auth";
    private static final String REFRESH_AUTHORITY = "refresh";

    @Inject
    private CustomProperties customProperties;

    public String createAccessToken(long principalId, Role role) {
        return Jwts.builder().setSubject(String.valueOf(principalId)).claim(AUTHORITIES_KEY, role.toString()).signWith(SignatureAlgorithm.HS512, customProperties.getSecretKey())
                .setExpiration(getValidity(customProperties.getAccessTokenValidityInSeconds())).compact();
    }

    public String createRefreshToken(long principalId) {
        return Jwts.builder().setSubject(String.valueOf(principalId)).claim(AUTHORITIES_KEY, REFRESH_AUTHORITY).signWith(SignatureAlgorithm.HS512, customProperties.getSecretKey())
                .setExpiration(getValidity(customProperties.getRefreshTokenValidityInSeconds())).compact();
    }

    @SuppressWarnings("unchecked")
    Authentication getAuthentication(String token) {
        final Claims claims = getJwtClaims(token);
        final long principalId = Long.parseLong(claims.getSubject());

        final String role = claims.get(AUTHORITIES_KEY).toString();

        return new PreAuthenticatedAuthenticationToken(principalId, null, Collections.singletonList(new SimpleGrantedAuthority(role)));
    }

    public Claims getJwtClaims(String token) {
        return Jwts.parser().setSigningKey(customProperties.getSecretKey()).parseClaimsJws(token).getBody();
    }

    private Date getValidity(long tokenValidity) {
        return Date.from(ZonedDateTime.now(ZoneId.of("UTC")).plusSeconds(tokenValidity).toInstant());
    }

}
