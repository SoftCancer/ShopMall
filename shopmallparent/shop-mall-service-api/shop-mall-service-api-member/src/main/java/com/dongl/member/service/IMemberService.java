package com.dongl.member.service;

import com.dongl.entity.AppEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/1 15:01
 * @Version: 1.0
 */

public interface IMemberService {

    @GetMapping("/memberToWeiXin")
   public AppEntity memberToWeiXin();
}
