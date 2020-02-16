package com.dongl.product.service.impl;

import com.dongl.base.BaseApiService;
import com.dongl.base.BaseResponse;
import com.dongl.product.api.IProductSearchService;
import com.dongl.product.es.entity.ProductEntity;
import com.dongl.product.es.reposiory.ProductReposiory;
import com.dongl.product.output.dto.ProductDto;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/2/14 14:12
 * @Version: 1.0
 */
@RestController
public class ProductSearchServiceImpl extends BaseApiService<List<ProductDto>> implements IProductSearchService {
    @Autowired
    private ProductReposiory productReposiory;

    @Override
    public BaseResponse<List<ProductDto>> search(String name) {
//        if (StringUtils.isBlank(name)){
//            return setResultError("名称不能为空！");
//        }
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        // 模拟查询
        builder.must(QueryBuilders.fuzzyQuery("name", name));
        Pageable pageable = new QPageRequest(0, 5);
        Page<ProductEntity> page = productReposiory.search(builder, pageable);
        List<ProductEntity> content = page.getContent();
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        List<ProductDto> mapAsList = mapperFactory.getMapperFacade().mapAsList(content, ProductDto.class);
        return setResultSuccess(mapAsList);
    }

    @GetMapping("/es")
    public String getES(){
        return "elasticsearch";

    }
}
