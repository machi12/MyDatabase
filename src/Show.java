import java.util.ArrayList;
import java.util.List;

/**
 * @ Description   :  以show为开头的命令
 * @ Author        :  马驰
 * @ CreateDate    :  2019/12/27 10:36
 */
public class Show {

    public static void showSql(String sql){

        //列出所有数据库
        boolean b = sql.endsWith(" databases;");
        if(b){
            String path = SQLConstant.getPath();
            List<String> dbList = Utils.getAllDatabase(path);
//            for(int i = 0; i < dbList.size(); i++){
//                System.out.println(dbList.get(i));
//            }
            List<String> db = new ArrayList<>();
            db.add("Database");
            List<List<String>> list = new ArrayList<>();
            for(int i = 0; i < dbList.size(); i++){
                List<String> ls = new ArrayList<>();
                ls.add(dbList.get(i));
                list.add(ls);
            }
            System.out.println(TableGenerator.generateTable(db, list));
            Input.get();
        }

        //列出所有该数据库下所有的表格
        else{
            boolean c = sql.endsWith(" table;");
            if(c){
                String nowPath = SQLConstant.getNowPath();
                List<String> tableList = Utils.getAllTables(nowPath);
                List<String> list = new ArrayList<>();

                //获取数据库名
                int index = nowPath.lastIndexOf("\\");
//                System.out.println(nowPath);
//                System.out.println(index);
//                System.out.println(nowPath.length());
                String dbName = nowPath.substring(index+1, nowPath.length());
                System.out.println(dbName);
                list.add(dbName);

                List<List<String>> tList = new ArrayList<>();
                for(String t:tableList){
                    List<String> list1 = new ArrayList<>();
                    list1.add(t);
                    tList.add(list1);
                }

                System.out.println(TableGenerator.generateTable(list, tList));
                Input.get();
            }
        }
    }


}
