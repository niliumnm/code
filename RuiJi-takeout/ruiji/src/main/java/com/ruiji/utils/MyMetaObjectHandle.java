package com.ruiji.utils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ruiji.utils.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 自定义元数据对象处理器
 *
 * @author shenyi
 */
@Component
@Slf4j
public class MyMetaObjectHandle implements MetaObjectHandler {

    /**
     * 插入操作自动填充
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段自动填充 [insert]...");
        log.info("insertFill当前线程为："+metaObject.toString());
        System.out.println(Thread.currentThread().getName());
        metaObject.setValue("createTime", new Date(System.currentTimeMillis()));
        metaObject.setValue("updateTime", new Date(System.currentTimeMillis()));
        metaObject.setValue("createUser", BaseContext.getCurrentId());
        metaObject.setValue("updateUser", BaseContext.getCurrentId());
    }

    /**
     * 更新操作自动填充
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段自动填充 [update]...");
        log.info(metaObject.toString());
        System.out.println("updateFill当前线程为："+Thread.currentThread().getName());
        metaObject.setValue("updateTime", new Date(System.currentTimeMillis()));
        metaObject.setValue("updateUser", BaseContext.getCurrentId());
    }
}
