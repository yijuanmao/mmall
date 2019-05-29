package com.example.mmall.model.sys;

public class SysRole {
    private String roleId;

    private String roleName;

    private String roleDesc;

    private String roleCreatorUser;

    private String roleCreatorTime;

    private String roleModifyUser;

    private String roleModifyTime;

    private String roleIndex;

    private String roleCode;

    private Integer dFlag;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

    public String getRoleCreatorUser() {
        return roleCreatorUser;
    }

    public void setRoleCreatorUser(String roleCreatorUser) {
        this.roleCreatorUser = roleCreatorUser == null ? null : roleCreatorUser.trim();
    }

    public String getRoleCreatorTime() {
        return roleCreatorTime;
    }

    public void setRoleCreatorTime(String roleCreatorTime) {
        this.roleCreatorTime = roleCreatorTime == null ? null : roleCreatorTime.trim();
    }

    public String getRoleModifyUser() {
        return roleModifyUser;
    }

    public void setRoleModifyUser(String roleModifyUser) {
        this.roleModifyUser = roleModifyUser == null ? null : roleModifyUser.trim();
    }

    public String getRoleModifyTime() {
        return roleModifyTime;
    }

    public void setRoleModifyTime(String roleModifyTime) {
        this.roleModifyTime = roleModifyTime == null ? null : roleModifyTime.trim();
    }

    public String getRoleIndex() {
        return roleIndex;
    }

    public void setRoleIndex(String roleIndex) {
        this.roleIndex = roleIndex == null ? null : roleIndex.trim();
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public Integer getdFlag() {
        return dFlag;
    }

    public void setdFlag(Integer dFlag) {
        this.dFlag = dFlag;
    }
}