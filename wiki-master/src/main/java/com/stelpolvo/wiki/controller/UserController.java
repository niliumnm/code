package com.stelpolvo.wiki.controller;

import com.alibaba.fastjson.JSONObject;
import com.stelpolvo.wiki.annotation.Log;
import com.stelpolvo.wiki.domain.User;
import com.stelpolvo.wiki.domain.Resp;
import com.stelpolvo.wiki.domain.vo.UserReqVo;
import com.stelpolvo.wiki.domain.vo.UserVo;
import com.stelpolvo.wiki.service.UserService;
import com.stelpolvo.wiki.utils.SnowFlake;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private SnowFlake snowFlake;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/list")
    @Log("条件查询用户列表")
    public Resp list(UserReqVo user) {
        return Resp.ok(userService.list(user));
    }

    @PostMapping("/save")
    @Log("修改或新增用户信息")
    public Resp save(@RequestBody User user) {
        if (!user.isNotNull()) {
            return Resp.error("请填写全部信息");
        }
        if (user.isValided()){
            return userService.save(user) > 0 ? Resp.ok("操作成功") : Resp.error("操作失败");
        }
        else {
            return Resp.error("密码需包含数字与字母，最少八位最高16位");
        }
    }

    @DeleteMapping("/delete/{id}")
    @Log("删除用户")
    public Resp delete(@PathVariable Long id) {
        if (ObjectUtils.isEmpty(id)){
            return Resp.error("请填写用户id");
        }
        return userService.delete(id) > 0 ? Resp.ok("删除成功") : Resp.error("删除失败");
    }

    @PostMapping("/reset-password")
    @Log("重置用户密码")
    public Resp resetPassword(@RequestBody User user) {
        if (ObjectUtils.isEmpty(user.getId()) || ObjectUtils.isEmpty(user.getPassword())) {
            return Resp.error("请填写全部信息");
        }
        if (user.isValided()){
            String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
            return userService.resetPassword(user.getId(), password) > 0 ? Resp.ok("修改成功") : Resp.error("修改失败");
        }
        else {
            return Resp.error("密码需包含数字与字母，最少八位最高16位");
        }
    }

    @PostMapping("/login")
    @Log("用户登录")
    public Resp login(@RequestBody User user) {
        if (ObjectUtils.isEmpty(user.getLoginName()) || ObjectUtils.isEmpty(user.getPassword())) {
            return Resp.error("请填写全部信息");
        }
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        User login = userService.login(user);

        Long token = snowFlake.nextId();
        UserVo userVo = new UserVo(login.getId(), login.getLoginName(), login.getName(), token.toString());
        redisTemplate.opsForValue().set(token.toString(), JSONObject.toJSONString(userVo), 3600 * 24, TimeUnit.SECONDS);
        return Resp.ok("登录成功", userVo);
    }

    @GetMapping("/logout/{token}")
    @Log("用户登出")
    public Resp logout(@PathVariable String token) {
        redisTemplate.delete(token);
        return Resp.ok("登出成功");
    }
}
