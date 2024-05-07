package com.cdvtc.news.dao;

import com.cdvtc.news.model.Tag;

import java.util.List;

public interface TagDao {
    /**
     * 获取所有标签
     * @return
     */
    List<Tag> getAllTags();
}
