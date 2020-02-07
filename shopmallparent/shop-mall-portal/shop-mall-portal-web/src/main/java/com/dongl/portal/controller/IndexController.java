package com.dongl.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *   @Controller和@RestController的区别 ：
 *  1.如果只是使用@RestController注解Controller，则Controller中的方法无法返回jsp页面，
 *  配置的视图解析器InternalResourceViewResolver不起作用，返回的内容就是Return 后面的内容。
 *  2.如果需要返回到指定页面，则需要用 @Controller配合视图解析器InternalResourceViewResolver才行。
 * @author: YaoGuangXun
 * @date: 2020/2/7 23:35
 * @Version: 1.0
 */
@Controller
public class IndexController {

    private static final String MB_INDEX_FTL = "index";

    @RequestMapping("/index")
    public String index(){
        return MB_INDEX_FTL;
    }
}
