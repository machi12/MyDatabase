package sdnu.machi;

import java.io.File;
import java.util.List;
import java.util.Scanner;

/**
 * @ Description   :  实现drop指令,目前只实现了drop database 数据库名; 和 drop table 表名;
 * @ Author        :  马驰
 * @ CreateDate    :  2020/1/13 9:01
 */
public class Drop {

    private static String sep;     //分隔符
    private static String dbName;  //数据库名称
    private static String tableName;   //表名

    /**
     * @Description  : 在该方法中对drop命令进行分类,并执行特定的子命令
     * @author       : 马驰
     * @param        : sql 输入的sql语句
     * @return       : void
     */
    public static void dropSql(String sql){
        //得到分隔符
        sep = SQLConstant.getSeparate();

        //System.out.println("machi");
        //如果是删除数据库的操作
        if(sql.contains(" database ")){
            dbName = sql.substring(14, sql.length()-1);
            //删除该数据库
            dropDatabase();
        }
        else if(sql.contains(" table ")){
            tableName = sql.substring(11, sql.length()-1);
            //删除该表
            dropTable();
        }
        else{
            System.out.println("ERROR: 无法识别该指令");
        }

    }

    /**
     * @Description  : 对数据库的删除操作
     * @author       : 马驰
     * @param        :
     * @return       :
     */
    private static void dropDatabase(){
        //获得当前路径
        String path = SQLConstant.getNowPath();
        //判断是否有该数据库
        List<String> dbList = Utils.getAllDatabase(path);

        boolean a = dbList.contains(dbName);
        if(a) {

            boolean b = confirm();
            if (b) {
                String nowPath = path + "\\" + dbName;
                deleteFile(new File(nowPath));
                System.out.println("Query OK");
            }
            else{
                System.out.println("撤回成功,数据库未被删除");
            }
        }
        else{
            System.out.println("ERROR: 该数据库不存在");
        }

        Input.get();
    }

    /**
     * 先根遍历序递归删除文件夹
     *
     * @param dirFile 要被删除的文件或者目录
     * @return 删除成功返回true, 否则返回false
     */
    public static boolean deleteFile(File dirFile) {
        // 如果dir对应的文件不存在，则退出
        if (!dirFile.exists()) {
            return false;
        }

        if (dirFile.isFile()) {
            return dirFile.delete();
        } else {

            for (File file : dirFile.listFiles()) {
                deleteFile(file);
            }
        }

        return dirFile.delete();
    }

    /**
     * @Description  : 确认是否删除
     * @author       : 马驰
     * @param        :
     * @return       : 确认为Yes则返回true,否则返回false
     */
    private static boolean confirm(){
        System.out.println("确认删除: \"Yes\" or \"No\"");
        System.out.print("请输入: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim().toLowerCase();
        if("yes".equals(input)){
            return true;
        }
        else if("no".equals(input)){
            return false;
        }
        else{
            System.out.println("输入的单词无法识别");
            return false;
        }
    }

    /**
     * @Description  : 删除数据表
     * @author       : 马驰
     * @param        :
     * @return       :
     */
    private static void dropTable(){
        //获得当前路径
        String path = SQLConstant.getNowPath();
        //判断是否有该数据表
        List<String> tableList = Utils.getAllTables(path);

        boolean a = tableList.contains(tableName);
        if(a) {

            boolean b = confirm();
            if (b) {
                String nowPath = path + "\\" + tableName + ".txt";
                File file = new File(nowPath);
                file.delete();
                System.out.println("Query OK");
            }
            else{
                System.out.println("撤回成功,数据库未被删除");
            }
        }
        else{
            System.out.println("ERROR: 该数据库不存在");
        }

        Input.get();
    }
}
