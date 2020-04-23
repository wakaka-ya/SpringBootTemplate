package com.wakaka.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * layui表格数据格式转换
 * @author zhangfy
 *
 */
public class LayuiTableUtil {
	/**
	 * 
	 * @param list 查询的结果集（进行分页）
	 * @param count 查询的总条数
	 */
	public static <T> String getTableJson(List<T> list,Integer count){
		return assembleJson(count,list);
	}
	/**
	 * 
	 * @param list 查询的结果集(不进行分页)
	 * @param page 页码
	 * @param limit 每页显示的条数
	 * @return
	 */
	public static <T> String getLimitTableJson(List<T> list,Integer page, Integer limit){
		List<T> tempList = new ArrayList<T>();
		int totalCount = 0;
		if(list != null && list.size() > 0){
			int startIndex = (page-1) * limit;
			totalCount = list.size();
			if(limit > totalCount){
				tempList.addAll(list.subList(startIndex, totalCount));
			}else{
				if((totalCount-startIndex)>limit){
					tempList.addAll(list.subList(startIndex, startIndex+limit));
				}else{
					tempList.addAll(list.subList(startIndex, totalCount));
				}
			}
		}
		return assembleJson(totalCount,tempList);
	}
	private static <T> String assembleJson(Integer count, List<T> list){
		List<Map<String, Object>> json = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", count);
		map.put("data", list);
		json.add(map);
		String tableJson = JsonUtils.objectToJson(json);
		return tableJson.substring(1, tableJson.length()-1);
	}
}
