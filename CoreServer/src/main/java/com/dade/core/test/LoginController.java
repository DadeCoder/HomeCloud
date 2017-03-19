//package com.dade.core.test;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.security.Principal;
//
///**
// * Created by Dade on 2017/3/7.
// */
//@RestController
//@CrossOrigin
//public class LoginController {
//
//
//
////    @Autowired
////    RoleRepo roleRepo;
////
////    @Autowired
////    UserRepo userRepo;
//
////        @RequestMapping("/login")
////        String login(Principal principal){
////            return principal.getName();
////        }
//
//
////    @RequestMapping(value = "/user", method = RequestMethod.GET)
////    @PreAuthorize("hasAnyRole('admin', 'user')")
////    public String userPage(Principal principal) {
////        return "user-page: " + principal.getName();
////    }
////
////    @RequestMapping(value = "/admin", method = RequestMethod.GET)
////    @PreAuthorize("hasAnyRole('admin')")
////    public String adminPage(Principal principal) {
////        return "admin-page: " + principal.getName();
////    }
//
//        @RequestMapping("/test_1")
//        String test(Principal principal){
//            return principal.getName()+"test_1";
//        }
//
//        @RequestMapping("/test__2")
//        String test_2(Principal principal){
//        return principal.getName()+"test_2";
//    }
//
//
////        @RequestMapping("ins")
////        String ins(){
////            SysRole sysRole = new SysRole();
////            sysRole.setName("ROLE_ADMIN");
////            sysRole.setId("1");
////            roleRepo.save(sysRole);
////
////            SysUser sysUser = new SysUser();
////            sysUser.setUsername("root");
////            sysUser.setPassword("root");
////            sysUser.setId("1");
////            sysUser.setRoles();
////            userRepo.save(sysUser);
////
////            SysUser sysUser2 = new SysUser();
////            sysUser2.setUsername("sang");
////            sysUser2.setPassword("sang");
////            sysUser2.setId("2");
////            userRepo.save(sysUser2);
////
////
////            return "ins success!";
////
////
////        }
//
//
//}
