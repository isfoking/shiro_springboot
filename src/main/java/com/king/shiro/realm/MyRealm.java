package com.king.shiro.realm;

import com.king.shiro.pojo.User;
import com.king.shiro.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *  认证加授权的自定义AuthorizingRealm
 */
@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    /**
     * 授权
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("role1");
        info.addRole("role2");
        info.addStringPermission("user:add");
        info.addStringPermission("user:delete");
        info.addStringPermission("user:list");
        return info;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        Object credentials = token.getCredentials();
        String password = new String((char[]) credentials);
        //1、根据用户名查找用户对象
        User user = userService.findUserByUserName(username);
        //2、根据输入的密码和查找出来的密码创建 authenticationInfo
        if (user!=null) {
//            SimpleAuthenticationInfo myReal = new SimpleAuthenticationInfo(password, user.getPassword(), "myReal");
            //加盐
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(password, user.getPassword(), ByteSource.Util.bytes("admin"),"myReal");
            return info;
        }
        return null;
    }
}
