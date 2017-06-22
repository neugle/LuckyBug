package com.rain6.luckybug.pipeline;

import com.rain6.luckybug.utils.JDBCUitls;
import org.apache.commons.dbutils.QueryRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
                //logger.info(sql);
                //logger.info("导入成功");
            } else {
                //logger.warn("导入失败");
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            //System.out.println(e.getMessage());
        }
    }

    public void process(List<Map<String, Object>> resultItems) {
        QueryRunner runner = new QueryRunner(JDBCUitls.getDataSource());
        String key = "";
        String value = "";
        for (Map<String, Object> resultItem : resultItems) {
            for (Map.Entry<String, Object> entry : resultItem.entrySet()) {
                key += entry.getKey() + ",";
                value += "?,";
            }
            key = key.substring(0, key.length() - 1);
            value = value.substring(0, value.length() - 1);
            break;
        }

        String sql = "insert into " + this.tableName + "(" + key + ") values(" + value + ")";

        Object params[][] = new Object[resultItems.size()][];

        for (int i = 0; i < resultItems.size(); i++) {
            List<Object> param = new ArrayList<Object>();
            for (Map.Entry<String, Object> entry : resultItems.get(i).entrySet()) {
                param.add(entry.getValue());
            }
            params[i] = param.toArray();
        }

        try {
            int[] result = runner.batch(sql, params);
            if (result.length > 0) {
                //logger.info("导入成功");
            } else {
                //logger.warn("批量导入失败");
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
