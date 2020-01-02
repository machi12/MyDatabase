package sdnu.machi;

/**
 * @ Description   :  格式化输入,去掉输入中多余的空格以及别的与内容无关的符号
 * @ Author        :  马驰
 * @ CreateDate    :  2019/12/26 16:10
 */
public class Format {

    public static String sqlFromat(String input){
        String sql = "";
        //去掉最后的;和空格
        //sql = input.replaceAll(";", "");
        //去掉字符串前后两端的空格
        sql = input.trim();
        //将字符串都转化为小写
        String string = sql.toLowerCase();
        //通过正则表达式将多个空格转换为一个空格
        String str = string.replaceAll("\\s{2,}", " ");
        //去掉;前的空格
        String s = str.replaceFirst("( ;)$", ";");
        //System.out.println(s);
        return s;
    }
}
