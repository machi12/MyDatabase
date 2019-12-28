/**
 * @ Description   :  Help帮助
 * @ Author        :  马驰
 * @ CreateDate    :  2019/12/27 10:32
 */
public class Help {

    /**
     * 帮助方法,列出当前支持的sql语句
     */
    public static void help(){
        System.out.println("1.create database 数据库名: 用于创建数据库");
        System.out.println("2.show databases: 列出目前已经创建的数据库");
        Input.get();
    }
}
