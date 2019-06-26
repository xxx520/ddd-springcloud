package org.yugh.authclient.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.yugh.authclient.domain.auth.UserDetail;
import org.yugh.authclient.utils.JwtUtils;
import org.yugh.authclient.utils.RedisClient;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * //Token校验
 *
 * @author: 余根海
 * @creation: 2019-04-09 18:17
 * @Copyright © 2019 yugenhai. All rights reserved.
 */
@Component
@Slf4j
@Configuration
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.header}")
    private String jwtHeader;

    @Value(("${jwt.tokenHead}"))
    private String jwtTokenHeader;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RedisClient redisClient;


    /**
     * 不按规范,不允许通过验证
     *
     * @param request
     * @param response
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authToken = request.getHeader(jwtHeader);
        final String tokenFlag = jwtTokenHeader;
        if (!StringUtils.isEmpty(authToken) && authToken.startsWith(tokenFlag)) {
            authToken = authToken.substring(tokenFlag.length());
        } else {
            authToken = null;
        }
        String userName = jwtUtils.getUsernameFromToken(authToken);
        log.info(String.format("=====> Checking authentication for userDetail %s.", userName));
        if (!StringUtils.isEmpty(userName)) {
            boolean isKey = redisClient.exists(userName);
            if(!isKey){
                authToken = null;
            }
            String redisToken = (String) redisClient.get(userName);
            if(redisToken.equals(authToken)){
                log.error("=====> redis token 有效并且和当前传入 token 一致：{},", redisToken);
            }
        }
        if (jwtUtils.containToken(userName, authToken) && userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetail userDetail = jwtUtils.getUserFromToken(authToken);
            if (jwtUtils.validateToken(authToken, userDetail)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                log.info("=====> Authenticated Context：{}", userName);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
}
