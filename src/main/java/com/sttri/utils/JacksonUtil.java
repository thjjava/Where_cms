package com.sttri.utils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.sttri.bean.QueryJSON;

@SuppressWarnings("deprecation")
public class JacksonUtil {
	
	static ObjectMapper objectMapper = null;
	static{
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
        //设置输入时忽略JSON字符串中存在而Java对象实际没有的属性  
		objectMapper.getDeserializationConfig().set(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);  
	}
	/**
	 * 将java对象转换成json字符串
	 * @param obj 准备转换的对象
	 * @return json字符串
	 * @throws Exception 
	 */
	public static String objectToJson(Object obj) throws Exception {
		try {
			String json =objectMapper.writeValueAsString(obj);
			return json;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}		
	}
	
	
	/**
	 * 将json字符串转换成java对象
	 * @param json 准备转换的json字符串
	 * @param cls  准备转换的类
	 * @return 
	 * @throws Exception 
	 */
	public static Object jsonToObject(String json, Class<?> cls) throws Exception {
		try {
		Object vo = objectMapper.readValue(json, cls);
		return vo;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}	
	}
	
	/**
	 * 将json字符串转换成Map
	 * @param json 准备转换的json字符串
	 * @return 
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static Map<Object, Object> jsonToMap(String json) {
		Map<Object, Object> map = new HashMap<Object, Object>();
	    try {
	    	map = objectMapper.readValue(json, Map.class);
	        /*System.out.println(map.size());
	        Set<String> key = maps.keySet();
	        Iterator<String> iter = key.iterator();
	        while (iter.hasNext()) {
	            String field = iter.next();
	            System.out.println(field + ":" + maps.get(field));

	        }*/
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return map;
	}
	
	/**
	 * 将Map转换成json
	 * @param map 准备转换的json字符串
	 * @return 
	 * @throws Exception 
	 */
	 public static String mapToJson(Map<String, Object> map) {
		 String json ="";
		  try {  
			json = objectMapper.writeValueAsString(map);  
		  } catch (Exception e) {  
			  e.printStackTrace();  
		  } 
		  return json;
	} 
	 
	/**
	 * 将json字符串转换成List
	 * @param json 准备转换的json字符串
	 * @return 
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static List<Object> jsonToList(String json) {
		List<Object> list=null;
	    try {
	        list = objectMapper.readValue(json, List.class);
	       /* System.out.println(list.size());
	        for (int i = 0; i < list.size(); i++) {
	        	System.out.println(list.get(i));
	            Map<String, Object> map = list.get(i);
	            Set<String> set = map.keySet();
	            for (Iterator<String> it = set.iterator();it.hasNext();) {
	                String key = it.next();
	                System.out.println(key + ":" + map.get(key));
	            }

	        }*/

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return list;
	}
	
	/**
	 * 将list转换成json
	 * @param json 准备转换的json字符串
	 * @return 
	 * @throws Exception 
	 */
	public static String listToJson(List<Object> list){
		String json="";
		try {
			json = objectMapper.writeValueAsString(list);  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * 将json字符串转换成Array
	 * @param json 准备转换的json字符串
	 * @return 
	 * @throws Exception 
	 */
	public static Object[] jsonToArray(String json) {

//	    String json = "[{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"},"+
//	            "{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}]";
		Object[] arr = null;
	    try {
	    	arr = objectMapper.readValue(json, Object[].class);
	        System.out.println(arr.length);
	        for (int i = 0; i < arr.length; i++) {
	            System.out.println(arr[i]);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return arr;
	}
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//json2list 
		 String json = "[{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"},"+

	                "{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}]";
		 jsonToList(json);
		
		 
		  /*//json2map 
		  String json = "{\"success\":true,\"A\":{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"},"+
				 	                "\"B\":{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}}";
		  jsonToMap(json);*/
		
		/*//json2array
		 	String json = "[{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"},"+
	            "{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}]";
			jsonToArray(json);*/

			
	 	/*//map2json
	 	Map<String, Object> map = new HashMap<String, Object>();
		Map<String, String> sMap = new HashMap<String, String>();
		sMap.put("a", "1");
		sMap.put("b", "2");
		map.put("test", sMap);
		System.out.println(mapToJson(map));*/
		
		//object2json
		
		QueryJSON queryJSON = new QueryJSON();
		queryJSON.setTotal(2l);
		queryJSON.setRows(jsonToList(json));
		System.out.println(objectToJson(queryJSON));
		
		
	}

}
