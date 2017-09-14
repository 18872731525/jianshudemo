package com.example.developlibrary.utils.jsontool;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.orhanobut.logger.Logger;

import java.util.List;


/**
 * json解析类
 *
 * @author wl
 */
public class FastjsonUtils {

    /**
     * 解析获取实体对象
     *
     * @param json
     * @param c    实体类
     * @return
     */
    public static <T> T getBeanObject(String json, Class<T> c) {

        try {
            return JSON.parseObject(json, c);
        } catch (Exception e) {
            // TODO: handle exception
            Logger.e("解析异常：" + e);
        }
        return null;

    }
    /**
     * 解析获取实体对象
     *
     * @param json
     * @param c    实体类
     * @return
     */
    public static <T> T getBeanObject(String json,TypeReference<T> c) {

        try {
            return JSON.parseObject(json, c);
        } catch (Exception e) {
            // TODO: handle exception
            Logger.e("解析异常：" + e);
        }
        return null;

    }
    /**
     * 解析获取实体对象List
     *
     * @param json
     * @param c    实体类
     * @return
     */
    public static <T> List<T> getBeanList(String json, Class<T> c) {
        try {
            return JSON.parseArray(json, c);
        } catch (Exception e) {
            // TODO: handle exception
            Logger.e("解析出错：" + e.getMessage());
        }
        return null;
    }

    /**
     * 嵌套json字符串解析 将一个json解析成多个json,或者值
     *
     * @param json
     * @param key
     * @return
     */
    public static String getJsonString(String json, String key) {
        return JSON.parseObject(json).getString(key);
    }

    /**
     * json字符串解析 将一个json解析成JSONObject,根据key获取Integer
     *
     * @param json
     * @param key
     * @return
     */
    public static Integer getJsonInteger(String json, String key) {
        return JSON.parseObject(json).getInteger(key);
    }

    /**
     * 获得code ,分析得到返回数据的有效性，0表示成功，其它表示操作失败,-1表示请求出错
     *
     * @param json
     * @return
     * @paramkey
     */
    public static Integer getCode(String json) {
        if (TextUtils.isEmpty(json)) {
            return -1;
        }
        int code = -1;
        try {
            code = getJsonInteger(json, "code");
        } catch (Exception e) {
            // TODO: handle exception
            Logger.e("解析异常" + e);
            return code;
        }
        return code;
    }

    /**
     * 从请求返回的数据中得到具体的json字符串
     *
     * @param json
     * @return
     * @paramkey
     */
    public static String getDto(String json) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        String dto = null;
        if (getCode(json) == 0) {
            try {
                dto = getJsonString(json, "dto");
            } catch (Exception e) {
                // TODO: handle exception
                Logger.e("" + e);
                return null;
            }
        }
        return dto;
    }

    /**
     * 错误码描述信息 当code不为0，才需要返回,描述了一些错误的信息提示
     *
     * @param json
     * @return
     */
    public static String getSummary(String json) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        if (getCode(json) != 0) {
            try {
                return getJsonString(json, "summary");
            } catch (Exception e) {
                // TODO: handle exception
                Logger.e("" + e);
            }
            return null;
        }
        return null;
    }

    /**
     * 将JavaBean序列化为JSON文本
     *
     * @param object
     * @return
     */
    public static String toJSONString(Object object) {
        if (object == null) {
            return null;
        }
        String json = JSON.toJSONString(object);
        return json;
    }

    /**
     * 将json解析成jsonArray
     *
     * @return
     * @paramobject
     */
    public static JSONArray praseJsonArray(String json) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        try {
            JSONArray jsonArray = JSON.parseArray(json);
            return jsonArray;
        } catch (Exception e) {
            // TODO: handle exception
            Logger.e("jsonArray  Exception");
            return null;
        }
    }

    /**
     * 解析混搭的json数据（用于享游列表）
     *
     * @param json
     * @return
     */
//	public static List<MutableBean> parseMutableBeanList(String json) {
//		String dto = getDto(json);
//		if (!TextUtils.isEmpty(dto)) {
//			List<MutableBean> list;
//			try {
//				JSONArray jsonArray = JSON.parseArray(dto);
//				list = new ArrayList<MutableBean>();
//				for (Object object : jsonArray) {
//					MutableBean bean = new MutableBean();
//					JSONObject jb = (JSONObject) object;
//					DataType type = DataType.valueOf(jb.getString("type"));
//					bean.setType(type);
//					JBean data;
//					switch (type) {
//					case activity:
//						data = jb.getObject("data", Market.class);
//						break;
//					case advertise:
//						data = jb.getObject("data", Market.class);
//						break;
//					case theme:
//						data = jb.getObject("data", Theme.class);
//						break;
//					case spot:
//						data = jb.getObject("data", SpotDetail.class);
//						break;
//					case goods:
//						data = jb.getObject("data", ListGoodsDTO.class);
//						break;
//					case ask:
//						data = jb.getObject("data", SimpleQuestion.class);
//						break;
//					case reviews:
//						data = jb.getObject("data", Review.class);
//						break;
//					case share:
//						data = jb.getObject("data", Share.class);
//						break;
//					case game:
//						data = jb.getObject("data", Game.class);
//						break;
//					default:
//						continue;
//					}
//					bean.setData(data);
//					list.add(bean);
//				}
//			} catch (Exception e) {
//				// TODO: handle exception
//				Logger.e("parse MutableBean Exception" + e);
//				return null;
//			}
//			return list;
//		} else {
//			return null;
//		}
//
//	}
//	
//	
//	
//	/**
//	 * 解析混搭的json数据（用于首页列表）
//	 * 
//	 * @param json
//	 * @return
//	 */
//	public static List<MutableListBean> parseMutableListBeans(String json) {
//		String dto = getDto(json);
//		if (!TextUtils.isEmpty(dto)) {
//			List<MutableListBean> list ;
//			try {
//				JSONArray jsonArray = JSON.parseArray(dto);
//				list = new ArrayList<MutableListBean>();
//				for (Object object : jsonArray) {
//					JSONObject jb = (JSONObject) object;
//					DataType type = DataType.valueOf(jb.getString("type"));
//					MutableListBean bean = new MutableListBean();
//					bean.setType(type);
//					switch (type) {
//					case goods:
//						bean.setDatas(FastjsonUtils.getBeanList(jb.getString("data"),
//								ListGoodsDTO.class));
//						break;
//					case ask:
//						bean.setDatas(FastjsonUtils.getBeanList(jb.getString("data"),
//								SimpleQuestion.class));
//						break;
//					case reviews:
//						bean.setDatas(FastjsonUtils.getBeanList(jb.getString("data"),
//								Review.class));
//						break;
//					case share:
//						bean.setDatas(FastjsonUtils.getBeanList(jb.getString("data"),
//								Share.class));
//						break;
//					case tag:
//						bean.setDatas(FastjsonUtils.getBeanList(jb.getString("data"),
//								Tag.class));
//						break;
//					case game:
//						bean.setDatas(FastjsonUtils.getBeanList(jb.getString("data"),
//								Game.class));
//						break;
//					default:
//						continue;
//					}
//
//					list.add(bean);
//				}
//			} catch (Exception e) {
//				// TODO: handle exception
//				Logger.e("parse MutableListBean Exception" + e);
//				return null;
//			}
//			return list;
//		} else {
//			return null;
//		}
//
//	}
}
