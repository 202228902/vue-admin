package com.galaxy.empvue.service.impl;

import com.galaxy.empvue.entity.User;
import com.galaxy.empvue.mapper.UserMapper;
import com.galaxy.empvue.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author duGalaxy
 * @since 2023-04-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
