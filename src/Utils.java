import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @ Description   :  数据库中的工具类
 * @ Author        :  马驰
 * @ CreateDate    :  2019/12/27 10:48
 */
public class Utils {

    /**
     * @Description  ： 获取所有数据库名称,查看路径下所有文件夹
     * @author       : 马驰
     * @param        : path 路径
     * @return       :
     * @date         : 2019/12/27 10:52
     */
    public static List<String> getAllDatabase(String path){
        List<String> list = new ArrayList<>();
        File file = new File(path);
        File[] fileList = file.listFiles();
        for(int i = 0; i < fileList.length; i++){
            if(fileList[i].isDirectory()){
                list.add(fileList[i].getName());
            }
        }
        return list;
    }

//    public static void showPrint(String name){
//        System.out.println();
//    }

    /**
     * @Description  ： 判断输入语句的括号是否匹配
     * @author       : 马驰
     * @param        : sql 输入的sql语句
     * @return       : 是否匹配
     */
    public static boolean bracketMatch(String sql){
        //建立堆栈
        Stack<String> stack = new Stack<>();

        //将语句转为char,方便匹配
        char[] chars = sql.toCharArray();
        for(int i = 0; i < chars.length; i++){
            if(chars[i] == '('){
                stack.push("(");
            }
            else if(chars[i] == ')'){
                if(stack.empty()){
                    return false;
                }
                else{
                    stack.pop();
                }
            }
        }
        return true;
    }

    /**
     * @Description  ： 获取当前数据库下的所有表
     * @author       : 马驰
     * @param        : nowPath 当前路劲
     * @return       : 所有表名组成的列表
     */
    public static List<String> getAllTables(String nowPath){
        List<String> list = new ArrayList<>();
        File file = new File(nowPath);
        File[] fileList = file.listFiles();
        for(int i = 0; i < fileList.length; i++){
            if(fileList[i].isFile()){
                String name = fileList[i].getName();
                int index = name.lastIndexOf(".");
                String tableName = name.substring(0, index);
                list.add(tableName);
            }
        }
        return list;
    }

}
