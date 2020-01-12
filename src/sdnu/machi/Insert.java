package sdnu.machi;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ Description   :  实现insert命令
 * @ Author        :  马驰
 * @ CreateDate    :  2019/12/31 22:19
 */
public class Insert {

    private static String tableName;

    public static void insertSql(String sql){
        int index = sql.indexOf("(");
        String name = sql.substring(12, index);
        tableName = name.trim();
        //System.out.println(tableName);

        //判断是否有该表
        String path = SQLConstant.getNowPath();
        List<String> list = Utils.getAllTables(path);
        boolean b = list.contains(tableName);
        if(b) {

            //得到当前路径
            String nowPath = path + "\\" + tableName + ".txt";


            //获取insert命令中两个括号中的内容
            List<String> list1 = new ArrayList<>();
            Pattern pattern = Pattern.compile("\\(.*?\\)");
            Matcher matcher = pattern.matcher(sql);
            while (matcher.find()) {
                list1.add(matcher.group());
            }
//            System.out.println(list1.size());
//            for(String s:list1){
//                System.out.println(s);
//            }

            //判断语句是否正确,根据()的数据
            if (list.size() != 2) {
                System.out.println("ERROR: 语句有错误");
                Input.get();
            } else {
                List<String> columnName = getColumnName(nowPath, 1);
                List<String> type = getColumnName(nowPath, 2);
//                for(String s: columnName){
//                    System.out.println(s);
//                }

                String s1 = list1.get(0).substring(1, list1.get(0).length()-1).trim();      //第一个括号,字段
                String s2 = list1.get(1).substring(1, list1.get(1).length()-1).trim();      //第二个括号,值

                //对字符串进行转义
                //String s11 = transMean(s1);
                String s22 = transMean(s2);

                String[] key = s1.split(",");
                String[] value = s22.split(",");

                for(int i = 0; i < key.length; i++){
                    key[i] = key[i].trim();
                }

                for(int i = 0; i < value.length; i++){
                    value[i] = value[i].trim();
                }

                //String s = "";      //最终要存储的字符串对象

//                System.out.println(s1);
//                System.out.println(s2);

//                for(String k: key){
//                    System.out.println(k);
//                }
//
//                for(String k: value){
//                    System.out.println(k);
//                }


                String sep = SQLConstant.getSeparate();
                int len = columnName.size();
                String s = "";      //最终要存储的字符串对象
                int flag1 = 0;
                for(int i = 0; i < len; i++){
                    String s3 = columnName.get(i);
                    int flag = 0;
                    for(int j = 0; j < key.length; j++){
                        if(key[j].equals(s3)){
                            //System.out.println(type.get(i) + "   " + value[j]);
                            String s4 = check(type.get(i), value[j]);
                            if(s4 == null){
                                flag1 = 1;
                                break;
                            }
                            else if(s4 == "machi"){
                                s += value[j] + sep;
                                flag = 1;
                            }
                            else {
                                s += s4 + sep;
                                flag = 1;
                            }
                        }
                    }
                    if(flag == 0){
                        s += "null" + sep;
                    }
                    if(flag1 == 1){
                        break;
                    }
                }
                //System.out.println(s);
                //System.out.println(flag1);
                if(flag1 == 0) {
                    writeFile(s);
                    System.out.println("Query OK");
                }
                Input.get();
            }
        }
        else{
            System.out.println("ERROR: 该表不存在");
            Input.get();
        }

    }

    /**
     * @Description  : 获取该表的所有列名
     * @author       : 马驰
     * @param        : path 表的路径; i 需要读出第i行
     * @return       : List 所有列名组成的列表
     */
    public static List<String> getColumnName(String path, int i){
        List<String> list = new ArrayList<>();

        //对文件进行读取
        try {
            File file = new File(path);
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            //StringBuilder sb = new StringBuilder(); //定义一个字符串缓存
            String s = "";

            int index = 0;

            //读取第i行
            while((s = bufferedReader.readLine()) != null){
                index++;
                if(index == i) {
                    String sep = SQLConstant.getSeparate();     //获取分隔符
                    String[] strings = s.split(sep);
                    for(String s1:strings){
                        list.add(s1);
                    }
                    return list;
                    //System.out.println(s);
                }
            }

//            //只读取第一行,第一行存放的是列名
//            if((s = bufferedReader.readLine()) != null){
//                String sep = SQLConstant.getSeparate();     //获取分隔符
//                String[] strings = s.split(sep);
//                for(String s1:strings){
//                    list.add(s1);
//                }
//            }

            bufferedReader.close();
        }catch(IOException e){
            e.printStackTrace();
        }


        return list;
    }

    /**
     * @Description  : 对传入的字符串进行转义,如果字符串中有分隔符,则需要在分隔符前再加一个分隔符
     * @author       : 马驰
     * @param        : str  需要转义的字符串
     * @return       : String 转义后的字符串
     */
    private static String transMean(String str){
        String sep = SQLConstant.getSeparate();     //分隔符
        String s = str.replaceAll(sep, sep + sep);

        return s;
    }

    /**
     * @Description  : 将字符串写入数据库文件中
     * @author       : 马驰
     * @param        : s 需要写入的字符串,代表数据中的一行
     * @return       : 无
     */
    private static void writeFile(String s){
        //获取分隔符
        String sep = SQLConstant.getSeparate();
        //获取当前表的路径
        String path = SQLConstant.getNowPath();
        String nowPath = path + "\\" + tableName + ".txt";

        //System.out.println();
        //System.out.println(str);
        //System.out.println(str);


        try{
            FileOutputStream fos = new FileOutputStream(
                    new File(nowPath), true);
            s += "\r\n";
            fos.write(s.getBytes());
            fos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * @Description  : 检查输入值的类型与之前定义字段的类型是否匹配
     * @author       : 马驰
     * @param        : type 该字段的数据类型; str 需要检查的字符串
     * @return       : String 检查完后要返回的字符串,例如char类型的要去掉""
     */
    private static String check(String type, String str){
        boolean b = type.contains("char");
        //boolean c = type.contains("varchar");
        //System.out.println(b);

        if(b){
            Pattern pattern = Pattern.compile("\".*\"");
            Matcher matcher = pattern.matcher(str);
            if(matcher.find()){
                //System.out.println("this is machi");
                String s = str.replaceAll("\"", "");
                //System.out.println(s);
                return s;
            }
            else{
                System.out.println("ERROR: 输入值的格式错误");
                return null;
            }
        }
        else{
            return "machi";
        }

    }

}
