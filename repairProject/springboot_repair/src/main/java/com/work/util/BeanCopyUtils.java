package com.work.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Bean对象拷贝工具类
 */
public class BeanCopyUtils {

    /**
     * 单个对象copy
     * @param source 数据来源对象（被拷贝的对象）
     * @param vClass 目标对象的class（返回的对象）
     * @param <V>
     * @return
     */
    public static <V> V copy(Object source,Class<V> vClass){
        //创建目标对象
        V result=null;
        try {
            result=vClass.newInstance();
            //实现属性copy
            BeanUtils.copyProperties(source, result);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * List对象拷贝
     * @param source
     * @param vClass
     * @param <O>
     * @param <V>
     * @return
     */
    public static <O,V> List<V>  copyList(List<O> source, Class<V> vClass){
        List<V> resultList=new ArrayList<>();
        for (O item:source){
            V copy = copy(item, vClass);
            resultList.add(copy);
        }
        return resultList;
    }
}
