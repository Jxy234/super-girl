package com.cdvtc.news.dao;

import com.cdvtc.news.model.Category;

import java.util.List;

/**
 * 访问新闻分类表接口
 */
public interface CategoryDao {
    List<Category> getAllCategories();
}
