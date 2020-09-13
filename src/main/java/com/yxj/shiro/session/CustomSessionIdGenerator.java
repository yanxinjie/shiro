package com.yxj.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;
import java.util.UUID;

/**
 * 自定义SessionId生成
 * @author 严新捷
 * @Type CustomSessionIdGenerator.java
 * @Desc
 * @date 2020/9/13 23:38
 */
public class CustomSessionIdGenerator  implements SessionIdGenerator {
    @Override
    public Serializable generateId(Session session) {
        return "eta_bdt-" + UUID.randomUUID().toString();
    }
}
