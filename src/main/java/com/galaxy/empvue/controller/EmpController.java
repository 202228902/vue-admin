package com.galaxy.empvue.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galaxy.empvue.common.R;
import com.galaxy.empvue.entity.Emp;
import com.galaxy.empvue.service.IEmpService;
import com.galaxy.empvue.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author duGalaxy
 * @since 2023-04-12
 */
@RestController
@CrossOrigin
@RequestMapping("/emp")
public class EmpController {
    @Autowired
    private IEmpService empService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RedisUtils redisUtils;

    @GetMapping
    public R list(@RequestParam(defaultValue = "1") Integer page,
                  @RequestParam(defaultValue = "10") Integer limit,
                  @RequestParam(defaultValue = "") String keyword){
        // 先从 Redis 中查询是否已有缓存
        String cacheKey = "emp_list_" + page + "_" + limit + "_" + keyword;
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        Object empListCache = valueOperations.get(cacheKey);
        if (empListCache != null) {
            System.out.println("不走数据库");
            // 如果存在缓存，则直接返回缓存结果
            return R.success(empListCache);
        }
        System.out.println("走数据库");
        // 如果不存在缓存，则进行数据库查询
        Page<Emp> empPage = new Page<>(page,limit);
        QueryWrapper<Emp> wrapper = new QueryWrapper<>();
        wrapper.like("ename", keyword);
        empService.page(empPage,wrapper);
        List<Emp> empList = empPage.getRecords();
        HashMap<String, Object> resultMap = new HashMap<>();
        // 返回查询结果
        resultMap.put("total", empPage.getTotal());
        resultMap.put("size", empPage.getSize());
        resultMap.put("pages", empPage.getPages());
        resultMap.put("list", empList);
        // 将查询结果缓存到 Redis 中，设置过期时间为 60 秒
        valueOperations.set(cacheKey, resultMap, Duration.ofSeconds(60));
        return R.success(resultMap);
    }
    @PostMapping
    public R addEmp(Emp emp){
        if (emp != null){
            boolean save = empService.save(emp);
            if (save){
                redisUtils.deleteByPrefix("emp_list");
                return R.success();
            }
        }
        return R.failure();
    }
    @PutMapping
    public R editEmp(Emp emp){
        System.out.println(emp);
        if (emp != null){
            boolean save = empService.updateById(emp);
            if (save){
                redisUtils.deleteByPrefix("emp_list");
                return R.success();
            }
        }
        return R.failure();
    }
    @DeleteMapping
    public R deleteEmp(Integer empno){
        if (empno != null){
            boolean save = empService.removeById(empno);
            if (save){
                redisUtils.deleteByPrefix("emp_list");
                return R.success();
            }
        }
        return R.failure();
    }
}

