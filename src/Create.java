import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ Description   :  以create开头的sql命令
 * @ Author        :  马驰
 * @ CreateDate    :  2019/12/27 10:25
 */
public class Create {

    private static final String path = SQLConstant.getPath();

    private static String tName = "";

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
        if(index != -1){
            //System.out.println(index);
            tName = sql.substring(13, index) + ".txt";
            tName.trim();
            //System.out.println("创建表格方法已经执行");

            //创建表
            File table = new File(tablePath, tName);
            if (!table.exists()) {

                //创建表,以一个文本文件表示一个表
                try {
                    table.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //提取sql语句中关于表中字段的信息
                //获取分隔符
                String sep = SQLConstant.getSeparate();
                //System.out.println(sql);
                //遍历整个sql语句,如果原文本中存在分隔符,则在分隔符前再加一个分隔符,相当于转义
                String str = sql.replaceAll(sep + "", sep + sep);
                //System.out.println(str);
                //提取出两个括号之间的内容
                Pattern pattern = Pattern.compile("\\(.*\\)");
                Matcher matcher = pattern.matcher(sql);
                String s = "";
                if (matcher.find()) {
                    s = matcher.group(0);
                    //System.out.println(s);
                }
                //分别提取每个字段的内容
                String s1 = s.substring(1, s.length() - 1);
                //System.out.println(s1);
                String[] strings = s1.split(",");
                //分别存放三行的信息
                //String[] strings1 = new String[strings.length];
                //String[] strings2 = new String[strings.length];
                //String[] strings3 = new String[strings.length];
                List<String> list1 = new ArrayList<>();
                List<String> list2 = new ArrayList<>();
                List<String> list3 = new ArrayList<>();
                //第一行存取列名,第二行存取类型,第三行存取剩余的说明信息
                for(String s2: strings){
                    String s3 = s2.trim();
                    String[] strings1 = s3.split(" ");
                    list1.add(strings1[0]);
                    list2.add(strings1[1]);
                    for(int i = 2; i < strings1.length; i++){
                        //list3.add(strings1[i]);
                        if(strings1[i].equals("not") && strings1[i+1].equals("null")){
                            list3.add("not null");
                            i++;
                        }
                        else{
                            list3.add(strings1[i]);
                        }
                    }
                    //System.out.println(s3);
                }
//                System.out.println("------------");
//                for(String a:list1){
//                    System.out.println(a);
//                }
//                System.out.println("-----------------");
//                for(String a:list2){
//                    System.out.println(a);
//                }
//                System.out.println("-----------------");
//                for(String a:list3){
//                    System.out.println(a);
//                }



                //向表中写入字段信息
                writeFile(list1);
                writeFile(list2);
                writeFile(list3);
            }
            else{
                System.out.println("ERROR: 该表已经存在");
            }


            //Input.get();
        }
        else{
            System.out.println("ERROR: 语句有错误");
            //Input.get();
        }

        Input.get();
    }

    /**
     * @Description  : 将字符串数组写入文件
     * @author       : 马驰
     * @param        : s 需要写入的字符串数组
     * @return       : 无
     */
    private static void writeFile(List<String> list){
        //将字符串数组连接成一个字符串
        String str = "";
        //System.out.println(str);
        //获取分隔符
        String sep = SQLConstant.getSeparate();
        //获取当前表的路径
        String path = SQLConstant.getNowPath();
        String nowPath = path + "\\" + tName;
        //拼接字符串
        for(String s1: list){
            str += s1 + sep;
        }
        //System.out.println(str);


        try{
            FileOutputStream fos = new FileOutputStream(
                    new File(nowPath), true);
            str += str + "\r\n";
            fos.write(str.getBytes());
            fos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
