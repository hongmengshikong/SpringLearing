package cn.edu.xcu.springboot.security.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName sys_menu
 */
@TableName(value ="sys_menu")
@Data
public class Menu implements Serializable {
    @TableId
    private Long menuId;

    private String menuName;

    private Long parentId;

    private Integer orderNum;

    private String path;

    private String component;

    private String query;

    private Integer isFrame;

    private Integer isCache;

    private String menuType;

    private String visible;

    private String status;

    private String perms;

    private String icon;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String remark;

    private static final long serialVersionUID = 1L;
}