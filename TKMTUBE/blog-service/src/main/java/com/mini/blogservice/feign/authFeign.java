// package com.mini.blogservice.feign;

// import org.springframework.cloud.openfeign.FeignClient;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;

// import com.mini.blogservice.models.userDTO;

// @FeignClient("authentication")
// public interface authFeign {
    
//     @GetMapping("auth/getUser/{username}")
//     public userDTO returnUser(@PathVariable("username") String username);

// }



// @GetMapping("auth/extractUsername")
//     public String getUsernameMap(@RequestParam("token") String token)