package com.rt.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: 常量
 * @Description: 常量
 * @author: <a href="edeis@163.com">edeis</a>
 * @version: V1.0.0
 * @date: 2018-3-22
 */
public class Constant {

    public String CURRENTUSER = "_currentUser";
	/** 超级管理员ID */
	public static final int SUPER_ADMIN = 1;
    public static final String TOKEN_CACHE="token_cache:";

    public static final String PAGE_SIZE="pageSize";
    public static final String PAGE_NUM="pageNum";
    public static final String ORDER_PROP = "prop";
    public static final String ORDER_TYPE = "order";
    public static final String ORDER_DESC = "desc";
    public static final String ORDER_ASC = "asc";

    public static final String CUSTOM_KEYGENERATOR = "customKeyGenerator";
    public static final Map<String,Boolean> ORDER_MAP = new HashMap<String,Boolean>();
    static {
        ORDER_MAP.put("ascending",true);
        ORDER_MAP.put("descending",false);
    }

    /**
     * 菜单类型
     *
     * @author chenshun
     * @email sunlightcs@gmail.com
     * @date 2016年11月15日 下午1:24:29
     */
    public enum MenuType {
        /**
         * 按钮
         */
        BUTTON(2),
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 定时任务状态
     * 
     * @author chenshun
     * @email sunlightcs@gmail.com
     * @date 2016年12月3日 上午12:07:22
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
    	NORMAL(0),
        /**
         * 暂停
         */
    	PAUSE(1);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }

}
