package sdnu.machi;

import java.util.List;

/**
 * @ Description   :  对use命令进行解析
 * @ Author        :  马驰
 * @ CreateDate    :  2019/12/27 15:44
 */
public class Use {

    public static void useSql(String sql){
        String dbName = sql.substring(4, sql.length()-1);
        //System.out.println(dbName);
        String path = SQLConstant.getPath();

        List<String> dbList = Utils.getAllDatabase(path);

        //判断使用的数据库是否存在
        boolean b = dbList.contains(dbName);
        if(b){
            SQLConstant.setNowPath(dbName);
            System.out.println("Database changed");
            Input.get();
        }
        else{
            System.out.println("ERROR: 数据库不存在");
            Input.get();
        }
    }
}
