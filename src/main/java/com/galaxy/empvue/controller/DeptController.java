package com.galaxy.empvue.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.galaxy.empvue.common.R;
import com.galaxy.empvue.entity.Dept;
import com.galaxy.empvue.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author duGalaxy
 * @since 2023-04-11
 */
@RestController
@RequestMapping("/dept")
@CrossOrigin
public class DeptController {
    @Autowired
    private IDeptService deptService;

    @PostMapping("/list/{page}/{limit}")
    public R list(@PathVariable Integer page,
                  @PathVariable Integer limit,
                  @RequestBody(required = false) Dept dept){
        if (page == null || limit ==null)
            return R.failure("提交参数为空");
        Page<Dept> deptPage = new Page<>(page,limit);
        QueryWrapper<Dept> wrapper = new QueryWrapper<>();
        if (dept!=null && !ObjectUtils.isEmpty(dept.getDname()))
            wrapper.like("dname",dept.getDname());
        if (dept!=null && !ObjectUtils.isEmpty(dept.getLoc()))
            wrapper.like("loc",dept.getLoc());
        deptService.page(deptPage,wrapper);
        return R.success(deptPage,"获取数据成功");
    }
}

