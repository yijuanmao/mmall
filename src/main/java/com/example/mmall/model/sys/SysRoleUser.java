package com.example.mmall.model.sys;

public class SysRoleUser {
    private String roleUserId;

    private String userId;

    private String roleId;

    private String roleUserAuthTime;

    private String roleUserCreatorUser;

    private String roleUserCreateTime;

    private String roleUserModifyUser;

    private String roleUserModifyTime;

    private Integer dFlag;

    public String getRoleUserId() {
        return roleUserId;
    }

    public void setRoleUserId(String roleUserId) {
        this.roleUserId = roleUserId == null ? null : roleUserId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getRoleUserAuthTime() {
        return roleUserAuthTime;
    }

    public void setRoleUserAuthTime(String roleUserAuthTime) {
        this.roleUserAuthTime = roleUserAuthTime == null ? null : roleUserAuthTime.trim();
    }

    public String getRoleUserCreatorUser() {
        return roleUserCreatorUser;
    }

    public void setRoleUserCreatorUser(String roleUserCreatorUser) {
        this.roleUserCreatorUser = roleUserCreatorUser == null ? null : roleUserCreatorUser.trim();
    }

    public String getRoleUserCreateTime() {
        return roleUserCreateTime;
    }

    public void setRoleUserCreateTime(String roleUserCreateTime) {
        this.roleUserCreateTime = roleUserCreateTime == null ? null : roleUserCreateTime.trim();
    }

    public String getRoleUserModifyUser() {
        return roleUserModifyUser;
    }

    public void setRoleUserModifyUser(String roleUserModifyUser) {
        this.roleUserModifyUser = roleUserModifyUser == null ? null : roleUserModifyUser.trim();
    }

    public String getRoleUserModifyTime() {
        return roleUserModifyTime;
    }

    public void setRoleUserModifyTime(String roleUserModifyTime) {
        this.roleUserModifyTime = roleUserModifyTime == null ? null : roleUserModifyTime.trim();
    }

    public Integer getdFlag() {
        return dFlag;
    }

    public void setdFlag(Integer dFlag) {
        this.dFlag = dFlag;
    }
}