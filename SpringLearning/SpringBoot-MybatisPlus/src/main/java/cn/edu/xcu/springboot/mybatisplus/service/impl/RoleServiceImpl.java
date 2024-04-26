package cn.edu.xcu.springboot.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.edu.xcu.springboot.mybatisplus.entity.Role;
import cn.edu.xcu.springboot.mybatisplus.service.RoleService;
import cn.edu.xcu.springboot.mybatisplus.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author 时空
* @description 针对表【t_role】的数据库操作Service实现
* @createDate 2024-04-26 15:29:25
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




