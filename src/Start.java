import java.util.Scanner;

/**
 * @ Description   :  自定义数据库的主类
 * @ Author        :  马驰
 * @ CreateDate    :  2019/12/21 13:17
 */
public class Start {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String input = "";
        //一个完整的语句输入默认以;结尾
        do{
            System.out.print(">>");
            input += " " + scanner.nextLine();
            //解决;后有多个空格不能结束的问题
            input = input.replaceFirst("(\\s+)$", "");
            //System.out.println(input);
        }while(!input.endsWith(";"));

        System.out.println(input);
        //格式化字符串
        String sql = Format.sqlFromat(input);
        System.out.println(sql);
    }
}
