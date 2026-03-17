package org.self.service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;


@Service
public class JWTService {
//   private String secretKey = "";
	private static final String SECRET = "mysecretkeymysecretkeymysecretkey12345";

	private SecretKey getSignKey() {
	    return Keys.hmacShaKeyFor(SECRET.getBytes());
	}
//   public JWTService() {
//	   try {
//		KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
//		SecretKey sk = keyGen.generateKey();
//		secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
//   }
   
   public String generateToken(String name, String role) {
	   Map<String, Object> claims = new HashMap<>();
       claims.put("role", role);
       
       return Jwts.builder()
    		   .claims()
    		   .add(claims)
    		   .subject(name)
    		   .issuedAt(new Date(System.currentTimeMillis()))
    		   .expiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60))
    		   .and()
    		   .signWith(getSignKey())
               .compact();	   
   }
//   private SecretKey getKey() {
//       byte[] keyBytes = Base64.getDecoder().decode(secretKey);
//       return Keys.hmacShaKeyFor(keyBytes);
//   }
   
   public String extractEmail(String token) {
	   return extractAllClaims(token).getSubject();
   }

   public String extractRole(String token) {
       return extractAllClaims(token).get("role", String.class);  
   }
   
   private Claims extractAllClaims(String token) {
	   return Jwts.parser()
			   .verifyWith(getSignKey())
			   .build()
			   .parseSignedClaims(token)
			   .getPayload();
   }
   
   public boolean validateToken(String token, UserDetails userDetails) {
       final String userName = extractEmail(token);
       return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
   }
   
   private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
       final Claims claims = extractAllClaims(token);
       return claimResolver.apply(claims);
   }

   private Date extractExpiration(String token) {
       return extractClaim(token, Claims::getExpiration);
   }
   
   private boolean isTokenExpired(String token) {
       return extractExpiration(token).before(new Date());
   }

}
