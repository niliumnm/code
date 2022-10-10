package com.stelpolvo.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stelpolvo.wiki.domain.User;
import com.stelpolvo.wiki.domain.UserExample;
import com.stelpolvo.wiki.domain.RespPage;
import com.stelpolvo.wiki.domain.vo.UserReqVo;
import com.stelpolvo.wiki.exception.UserException;
import com.stelpolvo.wiki.mapper.UserMapper;
import com.stelpolvo.wiki.utils.SnowFlake;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private SnowFlake snowFlake;

    public RespPage<User> list(UserReqVo user) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (!ObjectUtils.isEmpty(user.getUsername())) {
            criteria.andLoginNameEqualTo(user.getUsername());
        }
        PageHelper.startPage(user.getPage(), user.getSize());
        List<User> userList = userMapper.selectByExampleNP(userExample);
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        return new RespPage<>(userPageInfo.getTotal(), userPageInfo.getList());
    }

    public int save(User user) {
        if (ObjectUtils.isEmpty(user.getId())) {
            if (user.isNotNull()) {
                User selectByLoginName = selectByLoginName(user.getLoginName());
                if (ObjectUtils.isEmpty(selectByLoginName)) {
                    user.setId(snowFlake.nextId());
                    return userMapper.insert(user);
                } else {
                    throw new UserException("用户名已存在");
                }
            } else {
                throw new UserException("请填写全部用户信息");
            }
        } else {
            user.setLoginName(null);
            user.setPassword(null);
            return userMapper.updateByPrimaryKeySelective(user);
        }
    }

    public int delete(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    public User selectByLoginName(String loginName) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andLoginNameEqualTo(loginName);
        List<User> users = userMapper.selectByExample(userExample);
        return CollectionUtils.isEmpty(users) ? null : users.get(0);
    }

    public int resetPassword(Long id, String password) {
        User user = new User(id, password);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public User login(User user) {
        User userDb = selectByLoginName(user.getLoginName());
        if (ObjectUtils.isEmpty(userDb)) {
            // 用户名不存在
            throw new UserException("用户名或密码错误");
        } else {
            if (userDb.getPassword().equals(user.getPassword())) {
                // 登录成功
                userDb.setPassword(null);
                return userDb;
            } else {
                // 密码不对
                throw new UserException("用户名或密码错误");
            }
        }
    }
}
