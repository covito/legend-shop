package com.legendshop.spi.cache;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.cache.annotation.Caching;

@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD})
@Caching(evict={@org.springframework.cache.annotation.CacheEvict(value={"Sort"}, key="#sort.sortId")})
public @interface SortUpdate
{
}