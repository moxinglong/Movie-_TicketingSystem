package com.moxl.movie;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.nutz.dao.Dao;
import org.nutz.dao.util.Daos;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import com.moxl.movie.pojo.User;

public class MainSetup implements Setup { 
    public void init(NutConfig nc) {
        Ioc ioc = nc.getIoc();
        Dao dao = ioc.get(Dao.class);
        Daos.createTablesInPackage(dao, "com.moxl.movie", false);
        // 初始化默认根用户
        if (dao.count(User.class) == 0) {
            User user = new User();
            user.setEmail("1239848157@qq.com");
            user.setXm("男");
            String salt="abcdefghijklmnopqrstuvwx";
            user.setPwd(new Sha256Hash("123", salt, 1024).toBase64());
            user.setSex("男");
            user.setRole("1");
            dao.insert(user);
        }
    } 
    public void destroy(NutConfig nc) {// webapp销毁之前执行的逻辑
    } 
}
