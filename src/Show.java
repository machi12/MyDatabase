import java.util.ArrayList;
import java.util.List;

/**
 * @ Description   :  以show为开头的命令
 * @ Author        :  马驰
 * @ CreateDate    :  2019/12/27 10:36
 */
public class Show {

    public static void showSql(String sql){
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
        }
    }


}
