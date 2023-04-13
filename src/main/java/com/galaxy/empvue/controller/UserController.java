package com.galaxy.empvue.controller;


import com.auth0.jwt.interfaces.Claim;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.galaxy.empvue.common.R;
import com.galaxy.empvue.entity.User;
import com.galaxy.empvue.service.IUserService;
import com.galaxy.empvue.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author duGalaxy
 * @since 2023-04-12
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public R login(@RequestBody User user){
        if (user != null && !ObjectUtils.isEmpty(user.getUsername()) && !ObjectUtils.isEmpty(user.getPassword())){
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("username",user.getUsername());
            User one = userService.getOne(wrapper);
            if (one != null){
                if (one.getPassword().equals(user.getPassword())) {
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("msg","登陆成功");
                    hashMap.put("token", JwtUtils.encode(user.getUsername()));
                    return R.success(hashMap);
                }
                return R.failure("密码错误");
            }
            return R.failure("账号不存在");
        }
        return R.failure("提交参数不完整");
    }
    @GetMapping("/info")
    public R info(String token){
        if (ObjectUtils.isEmpty(token))
            return R.failure("token不能为空");
        Map<String, Claim> decodeToken = JwtUtils.decodeToken(token);
        String username = decodeToken.get("username").asString();
        if (!username.equals("")){
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("username",username);
            User one = userService.getOne(wrapper);
            if (one != null){
                return R.success(one);
            }
            return R.failure("用户不存在");
        }
        return R.failure("token出错");
    }

}

