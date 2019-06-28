package com.employee.util;

/**
 * @Author: Shin
 * @Date: 2019/6/26 14:16
 * @Blog: ideashin.com
 */
public class CheckUtil {
    private DBHelper dbHelper;
    public CheckUtil(){
        dbHelper = new DBHelper();
    }

    /**
     * 检查id是否存在
     * @param tableName
     * @param pkname
     * @param parameter
     * @return
     */
    public boolean isExists(String tableName, String pkname , Object parameter) {
        String sql = "select count(*) from" +tableName +  "where" + pkname + "=" + parameter;
        int i = dbHelper.getCount(sql);
        if (i <= 0) {
            return false;
        }
        return true;
    }

    /**
     * 检查是否为数字
     * @param number
     * @return
     */
    public boolean isPoNumber(String number) {
        for (int i = 0; i < number.length(); i++) {
            if (!Character.isDigit(number.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
