package com.dongl.product.api;


import com.dongl.base.BaseResponse;
import com.dongl.product.output.dto.ProductDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


/**
 * 
 * 
 * 
 * @description: 商品搜索服务接口
 * @author: 97后互联网架构师-余胜军
 * @contact: QQ644064779、微信yushengjun644 www.mayikt.com
 * @date: 2019年1月3日 下午3:03:17
 * @version V1.0
 * @Copyright 该项目“基于SpringCloud2.x构建微服务电商项目”由每特教育|蚂蚁课堂版权所有，未经过允许的情况下，
 *            私自分享视频和源码属于违法行为。
 */
public interface IProductSearchService {

    @GetMapping("/search")
    public BaseResponse<List<ProductDto>> search(String name);
}
