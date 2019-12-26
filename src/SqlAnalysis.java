import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ Description   :  实现一个sql解析器,目前支持create
 * @ Author        :  马驰
 * @ CreateDate    :  2019/12/26 17:03
 */
public class SqlAnalysis {

    //private static String[] str;
    private static final String path = "E:\\MyDatabase";
    //下面是目前支持的sql语句类型
    private static final String create = "create";
    private static final String help = "help";


    public static void analysis(String sql){
        //str = sql.split(" ");
        String start = "";
        //正则表达式的匹配规则
        String regex = "^[a-z]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sql);
        //获取匹配值
        while(matcher.find()){
            start = matcher.group();
        }

        //根据第一个单词判断该语句的作用
        switch (start){
            case create:
                createSql(sql);
                break;
            case help:
                help();
                break;
            default:
                System.out.println("输入的命令无法识别,可以输入help查看目前支持的sql语句");
                break;
        }
        //System.out.println(start);
    }

    /**
     * 帮助方法,列出当前支持的sql语句
     */
    private static void help(){
        System.out.println("1.create: 用于创建数据库");
    }

    /**
     * 创建数据库,一个文件夹代表一个数据库
     * @param sql
     */
    private static void createSql(String sql){
        //System.out.println("create语句识别成功");
        //判断是否为数据库创建方法
        boolean b = sql.contains(" database ");
        if(b){
            String dbName = sql.substring(16, sql.length()-1);
            //System.out.println(dbName);
            createDir(dbName);
        }
    }

    /**
     * 创建文件夹的方法
     * @param dbName
     */
    private static void createDir(String dbName){
        File file = new File(path + "\\" + dbName);
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
