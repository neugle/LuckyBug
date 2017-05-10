package com.rain6.luckybug.pipeline;

import com.rain6.luckybug.utils.JDBCUitls;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by Rain6 on 2017/4/20.
 */

/***
 * 管道输出到rdbms
 */
public class RdbmsPipeline implements Pipeline {
    private static final Logger logger = LoggerFactory.getLogger(RdbmsPipeline.class);
    //入表名称
    private String tableName;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void process(Map<String, Object> resultItems) {
        QueryRunner runner = new QueryRunner(JDBCUitls.getDataSource());
        String key = "";
        String value = "";
        for (Map.Entry<String, Object> entry : resultItems.entrySet()) {
            key += entry.getKey() + ",";
            value += "'" + entry.getValue() + "',";
        }
        key = key.substring(0, key.length() - 1);
        value = value.substring(0, value.length() - 1);

        String sql = "insert into " + this.tableName + "(" + key + ") values(" + value + ")";

        try {
            int result = runner.update(sql);
            if (result > 0) {
                logger.info(sql);
                logger.info("导入成功");
            } else {
                logger.warn("导入失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
