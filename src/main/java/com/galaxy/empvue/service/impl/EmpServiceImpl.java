package com.galaxy.empvue.service.impl;

import com.galaxy.empvue.entity.Emp;
import com.galaxy.empvue.mapper.EmpMapper;
import com.galaxy.empvue.service.IEmpService;
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
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements IEmpService {

}
