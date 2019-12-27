import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

    public static void showPrint(String name){
        System.out.println();
    }
}
