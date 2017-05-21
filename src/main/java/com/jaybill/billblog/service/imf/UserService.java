package com.jaybill.billblog.service.imf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jaybill.billblog.pojo.User;
import com.jaybill.billblog.service.LoginService;
@Service
public class UserService implements UserDetailsService{
	@Autowired
	LoginService ls;
	@Override
    public UserDetails loadUserByUsername(String userAccount) throws UsernameNotFoundException {
		User user = ls.selectByAccount(userAccount);
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        if( user == null ){
            throw new UsernameNotFoundException(String.format("User with username=%s was not found", userAccount));
        }
        authorities.add(new SimpleGrantedAuthority(user.getUserRole()));
        return new org.springframework.security.core.userdetails.User(user.getUserId()+"",
                user.getUserPassword(), authorities);
    }
}
