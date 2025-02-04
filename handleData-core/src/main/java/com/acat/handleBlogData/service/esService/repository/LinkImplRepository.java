package com.acat.handleBlogData.service.esService.repository;

import com.acat.handleBlogData.domain.LinkUserImplData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkImplRepository extends ElasticsearchRepository<LinkUserImplData, String> {
}
