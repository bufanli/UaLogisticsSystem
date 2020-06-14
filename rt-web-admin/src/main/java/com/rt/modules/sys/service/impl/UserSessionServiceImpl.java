package com.rt.modules.sys.service.impl;

import com.rt.common.shiro.ShiroUser;
import com.rt.modules.sys.entity.User;
import com.rt.modules.sys.entity.UserSessionEntity;
import com.rt.modules.sys.service.UserSessionService;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @author wjggwm
 * @ClassName UserSessionServiceImpl
 * @Description 手动操作Session，管理Session
 * @data 2016年12月13日 下午12:33:10
 */
@Service("userSessionService")
public class UserSessionServiceImpl implements UserSessionService {

    private static final Logger logger = LoggerFactory.getLogger(UserSessionServiceImpl.class);

    /**
     * session status
     */
    public static final String SESSION_STATUS = "webside_session_status";

    @Autowired
    SessionDAO sessionDAO;

    /**
     * 获取所有的有效Session用户
     *
     * @return
     */
    public List<UserSessionEntity> getAllUser() {
        // 获取所有session
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        List<UserSessionEntity> list = new ArrayList<UserSessionEntity>();
        for (Session session : sessions) {
            UserSessionEntity bo = getSessionUser(session);
            if (null != bo) {
                list.add(bo);
            }
        }
        return list;
    }

    /**
     * 根据ID查询 SimplePrincipalCollection
     *
     * @param userIds 用户ID
     * @return
     */
    public List<SimplePrincipalCollection> getSimplePrincipalCollectionByUserId(
            Long... userIds) {
        // 把userIds 转成Set，好判断
        Set<Long> idset = new TreeSet<Long>(Arrays.asList(userIds));
        // 获取所有session
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        // 定义返回
        List<SimplePrincipalCollection> list = new ArrayList<SimplePrincipalCollection>();
        for (Session session : sessions) {
            // 获取SimplePrincipalCollection
            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (null != obj && obj instanceof SimplePrincipalCollection) {
                // 强转
                SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
                // 判断用户，匹配用户ID。
                obj = spc.getPrimaryPrincipal();
                if (null != obj && obj instanceof ShiroUser) {
                    ShiroUser user = (ShiroUser) obj;
                    // 比较用户ID，符合即加入集合
                    if (idset.contains(user.getId())) {
                        list.add(spc);
                    }
                }
            }
        }
        return list;
    }


    public UserSessionEntity getSession(String sessionId) {
        Session session = sessionDAO.readSession(sessionId);
        UserSessionEntity bo = getSessionUser(session);
        return bo;
    }


    public UserSessionEntity getSessionUser(Session session) {
        // 获取session登录信息
        Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        if (null == obj) {
            return null;
        }
        // 确保是 SimplePrincipalCollection对象。
        if (obj instanceof SimplePrincipalCollection) {
            SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
            /**
             * 获取用户登录的，@link SampleRealm.doGetAuthenticationInfo(...)方法中 return
             * new SimpleAuthenticationInfo(user,user.getPswd(),
             * getName());的user 对象。
             */
            obj = spc.getPrimaryPrincipal();
            if (null != obj && obj instanceof User) {
                // 存储session + user 综合信息
                UserSessionEntity userBo = new UserSessionEntity((User) obj);
                // 最后一次和系统交互的时间
                userBo.setLastAccess(session.getLastAccessTime());
                // 主机的ip地址
                userBo.setHost(session.getHost());
                // session ID
                userBo.setSessionId(session.getId().toString());
                // session最后一次与系统交互的时间
                // userBo.setLastLoginTime(session.getLastAccessTime());
                // 回话到期 ttl(ms)
                userBo.setTimeout(session.getTimeout());
                // session创建时间
                userBo.setStartTime(session.getStartTimestamp());
                return userBo;
            }
        }
        return null;
    }


}
