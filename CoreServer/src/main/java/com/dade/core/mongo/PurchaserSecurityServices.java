package com.dade.core.mongo;

import com.dade.common.utils.LogUtil;
import com.dade.core.test.HunterUser;
import com.dade.core.user.purchaser.Purchaser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dade on 2017/3/12.
 */
public class PurchaserSecurityServices implements UserDetailsService {
    private final PurchaserRepository purchaserRepository;

    public PurchaserSecurityServices(PurchaserRepository purchaserRepository){
        this.purchaserRepository = purchaserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {

//        LogUtil.info(phoneNumber);

        Purchaser purchaser = purchaserRepository.findByPhoneNumber(phoneNumber);

//        LogUtil.info(hunterUser.toString());

        if (purchaser != null){
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + purchaser.getRole()));

            LogUtil.info("ROLE_" + purchaser.getRole());

            return new org.springframework.security.core.userdetails.User(purchaser.getPhoneNumber(), purchaser.getPassword(), authorities);
        }

        throw new UsernameNotFoundException("User " + phoneNumber + "Not Found!");

    }

}

