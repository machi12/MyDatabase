package sdnu.machi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ Description   :  实现select语句
 * @ Author        :  马驰
 * @ CreateDate    :  2020/1/11 19:25
 */
public class Select {
    
    /**
     * @Description  : 实现select查询的主函数,目前只实现了select * from 表名;
     * @author       : 马驰
     * @param        : sql 输入的sql语句
     * @return       : void
     */
    public static void selectSql(String sql){

        //得到分隔符
        String sep = SQLConstant.getSeparate();

        //获得当前路径,到数据库文件夹
        String path = SQLConstant.getNowPath();
        String tableName = sql.substring(14, sql.length()-1);

        //当前表的完整路径
        String nowPath = path + "\\" + tableName + ".txt";

        //获得表中的数据
        List<String> list = getTableDescribe(nowPath);

        String restrict1 = sql.substring(7, 8);

        boolean b = tableName.contains(" ");


        if(!b && restrict1.equals("*")) {


            List<String> columnName = Insert.getColumnName(nowPath, 1);     //获得列名

            //处理表格中的数据,转化为List<List<String>>
            List<List<String>> lists = new ArrayList<>();
            int i = 0;
            for (String s : list) {
                List<String> list1 = new ArrayList<>();
                String[] strings = list.get(i++).split(sep);
                for (String s1 : strings) {
                    list1.add(s1);
                }
                lists.add(list1);
            }

            System.out.println(TableGenerator.generateTable(columnName, lists));

            System.out.println("Query OK");

        }
        else{
            System.out.println("ERROR: 目前的select命令不支持这种方式的查询");
        }


        Input.get();

//        for(String s:list){
//            System.out.println(s);
//        }
    }

    /**
     * @Description  : 读出数据库中表中第3行中的数据,因为前三行存放的是表的控制信息,三行之后存放的是表的控制信息
     * @author       : 马驰
     * @param        : path 数据表的路径
     * @return       : List 由数据行组成的列表
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
            int index = 1;
            while((s = bufferedReader.readLine()) != null){
                index++;
                if(index > 4){
                    list.add(s);
                }
                //System.out.println(s);
            }
            bufferedReader.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        return list;
    }
}
