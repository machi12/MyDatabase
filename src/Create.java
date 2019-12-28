import java.io.File;
import java.io.IOException;

/**
 * @ Description   :  以create开头的sql命令
 * @ Author        :  马驰
 * @ CreateDate    :  2019/12/27 10:25
 */
public class Create {

    private static final String path = SQLConstant.getPath();

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
                //System.out.println(tName);
                //判断输入语句的括号是否匹配
                if(Utils.bracketMatch(sql)) {
                    createTable(sql);
                }
                else{
                    System.out.println("ERROR: 语句有错误");
                    Input.get();
                }
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

    /**
     * @Description  ：创建表格的方法,使用txt文件存储表
     * @author       : 马驰
     * @param        : sql 输入的sql语句
     * @return       : 无
     */
    private static void createTable(String sql){
        //获取当前数据库所在路径
        String tablePath = SQLConstant.getNowPath();

        //得到表名
        int index = sql.indexOf("(");
        String tName = sql.substring(13, index) + ".txt";
        tName.trim();
        //System.out.println("创建表格方法已经执行");

        //创建表
        File table = new File(tablePath, tName);
        if(!table.exists()){
            try{
                table.createNewFile();
            }catch(IOException e){
                e.printStackTrace();
            }
            Input.get();
        }
        else{
            System.out.println("ERROR: 该表已经存在");
            Input.get();
        }
    }
}
