package sdnu.machi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

    public static void insertSql(String sql){
        int index = sql.indexOf("(");
        String name = sql.substring(12, index);
        String tableName = name.trim();
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
                List<String> columnName = getColumnName(nowPath);
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

                //String s = "";      //最终要存储的字符串对象

//                System.out.println(s1);
//                System.out.println(s2);


                String sep = SQLConstant.getSeparate();
                int len = columnName.size();
                String s = "";      //最终要存储的字符串对象
                for(int i = 0; i < len; i++){
                    String s3 = columnName.get(i);
                    int flag = 0;
                    for(int j = 0; j < key.length; j++){
                        if(key[j].equals(s3)){
                            s += value[j] + sep;
                            flag = 1;
                        }
                    }
                    if(flag == 0){
                        s += "null" + sep;
                    }
                }
                System.out.println(s);
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
     * @param        : path 表的路径
     * @return       : List 所有列名组成的列表
     */
    private static List<String> getColumnName(String path){
        List<String> list = new ArrayList<>();

        //对文件进行读取
        try {
            File file = new File(path);
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            //StringBuilder sb = new StringBuilder(); //定义一个字符串缓存
            String s = "";

            //只读取第一行,第一行存放的是列名
            if((s = bufferedReader.readLine()) != null){
                String sep = SQLConstant.getSeparate();     //获取分隔符
                String[] strings = s.split(sep);
                for(String s1:strings){
                    list.add(s1);
                }
            }

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

}
