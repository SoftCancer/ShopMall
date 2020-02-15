package com.dongl.product.es.reposiory;

import com.dongl.product.es.entity.ProductEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface ProductReposiory extends ElasticsearchRepository<ProductEntity, Long> {

}
 