package sdnu.machi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ Description   :  实现describe命令
 * @ Author        :  马驰
 * @ CreateDate    :  2019/12/31 17:24
 */
public class Describe {

    public static void describeSql(String sql){

        //得到分隔符
        String sep = SQLConstant.getSeparate();

        //获得当前路径,到数据库文件夹
        String path = SQLConstant.getNowPath();
        String tableName = sql.substring(9, sql.length()-1);

        //当前表的完整路径
        String nowPath = path + "\\" + tableName + ".txt";
        List<String> list = getTableDescribe(nowPath);

        //对获取的list进行处理并打印
        List<List<String>> lists = new ArrayList<>();
        String[] s1 = list.get(0).split(sep);
        String[] s2 = list.get(1).split(sep);
        String[] s3 = list.get(2).split(sep);
        for(int i = 0; i < s1.length; i++){
            List<String> list1 = new ArrayList<>();
            list1.add(s1[i]);
            list1.add(s2[i]);
            list1.add(s3[i]);
            lists.add(list1);
        }
        List<String> list4 = new ArrayList<>();
        list4.add("Filed");
        list4.add("Type");
        list4.add("Extra");

        System.out.println(TableGenerator.generateTable(list4, lists));


        Input.get();
    }

    /**
     * @Description  : 从文本文件(表)的前三行读取表的描述信息
     * @author       : 马驰
     * @param        : path 当前表的路径
     * @return       : List<String> 将表的描述信息封装到List中
     */
    private static List<String> getTableDescribe(String path){

        List<String> list = new ArrayList<>();

        //对文件进行读取
        try {
            File file = new File(path);
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            //StringBuilder sb = new StringBuilder(); //定义一个字符串缓存
            String s = "";
            while((s = bufferedReader.readLine()) != null){
                list.add(s);
                //System.out.println(s);
            }
            bufferedReader.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return list;
    }
}
