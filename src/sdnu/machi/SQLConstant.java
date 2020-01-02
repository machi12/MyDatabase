package sdnu.machi;

/**
 * @ Description   :  与数据库相关的常量
 * @ Author        :  马驰
 * @ CreateDate    :  2019/12/27 10:27
 */
public class SQLConstant {

    //数据库的根路径
    private static final String path = "E:\\MyDatabase";

    //数据库的当前路径
    private static String nowPath = path;

    //自定义的分隔符
    private static final String separate = "~";

    public static String getPath(){
        return path;
    }

    /**
     * @Description  ： 返回当前路径
     * @author       : 马驰
     * @param        : 无
     * @return       : 无
     */
    public static String getNowPath(){
        return nowPath;
    }

    /**
     * @Description  ： 设置当前路径
     * @author       : 马驰
     * @param        : name 数据库名
     * @return       : 无
     */
    public static void setNowPath(String name){
        nowPath = nowPath + "\\" + name;
    }

    public static String getSeparate(){
        return separate;
    }

}
