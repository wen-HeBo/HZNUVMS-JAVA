package com.hznuvms.shiro;

import com.hznuvms.entity.Admin;
import com.hznuvms.service.AdminService;
import com.hznuvms.util.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AdminService adminService;

    @Override
    public boolean supports(AuthenticationToken token){
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        JwtToken jwtToken = (JwtToken)token;

        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        Admin admin = adminService.getById(Long.valueOf(userId));
        if(admin == null){
            throw new UnknownAccountException("用户不存在");
        }
//        if (volunteer.getStatus() == -1){
//            throw new LockedAccountException("用户已锁定");
//        }

        AccountProfile profile = new AccountProfile();
        BeanUtils.copyProperties(admin,profile);

        System.out.println("------------");

        return new SimpleAuthenticationInfo(profile,jwtToken.getCredentials(),getName());
    }
}