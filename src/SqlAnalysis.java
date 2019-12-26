import java.util.ArrayList;
import java.util.List;

/**
 * @ Description   :  实现一个sql解析器,目前支持create
 * @ Author        :  马驰
 * @ CreateDate    :  2019/12/26 17:03
 */
public class SqlAnalysis {

    private static String[] str;

    public static void analysis(String sql){
        str = sql.split(" ");
    }
}
