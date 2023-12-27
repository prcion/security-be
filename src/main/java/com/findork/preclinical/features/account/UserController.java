package com.findork.preclinical.features.account;

import com.findork.preclinical.aop.Audit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    @GetMapping("/{userId}/{companyId}/{siteId}")
    @Audit(message = "message here")
    public String getCurrentUser(@PathVariable String userId, @PathVariable String companyId, @PathVariable String siteId) {
//        System.out.println(user);
        return "Congratulation User you can access this api";
    }
}
