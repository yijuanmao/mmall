package com.example.mmall.dto;

import com.example.mmall.model.sys.SysFunctionCell;
import com.example.mmall.model.user.MmallUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SpringSecurity需要的用户详情
 * Created by macro on 2018/4/26.
 */
public class MmallUserDetails implements UserDetails {
    private MmallUser mmallUser;
    private List<SysFunctionCell> sysFunctionCells;

    public MmallUserDetails(MmallUser mmallUser, List<SysFunctionCell> sysFunctionCells) {
        this.mmallUser = mmallUser;
        this.sysFunctionCells = sysFunctionCells;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return sysFunctionCells.stream()
                .filter(permission -> permission.getFuceCode()!=null)
                .map(permission ->new SimpleGrantedAuthority(permission.getFuceCode()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return mmallUser.getPassword();
    }

    @Override
    public String getUsername() {
        return mmallUser.getUsername();
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
