import java.io.File;

/**
 * @ Description   :  以create开头的sql命令
 * @ Author        :  马驰
 * @ CreateDate    :  2019/12/27 10:25
 */
public class Create {

    /**
     * 创建数据库,一个文件夹代表一个数据库
     * @param sql
     */
    public static void createSql(String sql){
        //System.out.println("create语句识别成功");
        //判断是否为数据库创建方法
        boolean b = sql.contains(" database ");
        if(b){
            String dbName = sql.substring(16, sql.length()-1);
            //System.out.println(dbName);
            createDir(dbName);
        }
        //判断是否为创建表格的方法
        else{
            boolean c = sql.contains(" table ");
            if(c){
                //String tName = sql.substring();
            }
        }
    }

    /**
     * 创建文件夹的方法
     * @param dbName
     */
    private static void createDir(String dbName){
        File file = new File(SQLConstant.getPath() + "\\" + dbName);
        if(!file.exists()){
            file.mkdir();
            System.out.println("Query OK");
            Input.get();
        }
        else{
            System.out.println("ERROR: database exists");
            Input.get();
        }
    }
}
