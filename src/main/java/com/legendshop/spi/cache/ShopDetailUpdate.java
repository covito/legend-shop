package com.legendshop.spi.cache;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.cache.annotation.Caching;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD})
@Caching(evict={@org.springframework.cache.annotation.CacheEvict(value={"ShopDetailView"}, key="#shopDetail.userName"), @org.springframework.cache.annotation.CacheEvict(value={"ShopDetailView"}, key="'DM_' + #shopDetail.domainName")})
public @interface ShopDetailUpdate
{
}