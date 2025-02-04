package com.acat.handleBlogData.service.esService.repository;

import com.acat.handleBlogData.domain.FqUserImplData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FqImplRepository extends ElasticsearchRepository<FqUserImplData, String> {
}