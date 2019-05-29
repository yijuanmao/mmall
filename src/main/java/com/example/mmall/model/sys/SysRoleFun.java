package com.example.mmall.model.sys;

import lombok.Data;

@Data
public class SysRoleFun {
    private Long roleFunId;

    private String roleId;

    private String code;

    private String roleFunCreatorUser;

    private String roleFunCreatorTime;

    private String roleFunModifyTime;

    private String roleFunModifyUser;

    private Integer dFlag;

}