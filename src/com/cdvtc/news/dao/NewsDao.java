package com.cdvtc.news.dao;

import com.cdvtc.news.model.News;

import java.util.List;

public interface NewsDao {
    /**
     * 获取所有新闻
     * @return
     */
    List<News> getAllNews();

    /**
     * 获取分类新闻列表
     * @param categoryId 分类编号
     * @return
     */
    List<News> getNewsByCategory(Integer categoryId);

    /**
     * 获取标签新闻列表
     * @param tagId 标签编号
     * @return
     */
    List<News> getNewsByTag(Integer tagId);

    /**
     * 获取置顶新闻（首页展示）
     * @param limit 限制数量
     * @return
     */
    List<News> getStickNews(int limit);

    /**
     * 根据ID获取新闻
     * @param newsId
     * @return
     */
    News getNewsById(int newsId);

    /**
     * 获取新闻的推荐新闻（根据新闻标签）
     * @param newsId
     * @return
     */
    List<News> getRecommendedNews(int newsId);

    /**
     * 获取热点新闻（24小时内发布或评论，前6条）
     * @return
     */
    List<News> getHotNews();

    /**
     * 更新新闻点击计数（+1）
     * @param newsId
     */
    void updateClickCount(int newsId);
}
