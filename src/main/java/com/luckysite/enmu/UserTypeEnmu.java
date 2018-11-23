package com.luckysite.enmu;

public enum  UserTypeEnmu{
    USER("user", 1),   //用户角色
    VIP("vip", 2);

    private String role;
    private int roleId;

    private UserTypeEnmu(String role, int roleId) {
        this.role = role;
        this.roleId = roleId;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getRoleId() {
        return this.roleId;
    }

    public String getRole() {
        return this.role;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
