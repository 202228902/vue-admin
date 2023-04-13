package com.galaxy.empvue.entity;

import com.baomidou.mybatisplus.annotation.Version;

import java.io.Serializable;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author duGalaxy
 * @since 2023-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer deptno;

    private String dname;

    private String loc;

    private Integer deptnum;


}
