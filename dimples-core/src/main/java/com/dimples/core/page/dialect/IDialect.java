package com.dimples.core.page.dialect;

import com.dimples.core.page.metadata.Page;

/**
 * @author zhongyj <1126834403@qq.com><br/>
 * @date 2020/5/19
 */
public interface IDialect {

    /**
     * 组装分页语句
     *
     * @param originalSql 原始语句
     * @param page        分页对象
     * @return 分页SQL
     */
    String buildPaginationSql(String originalSql, Page<?> page);

}

