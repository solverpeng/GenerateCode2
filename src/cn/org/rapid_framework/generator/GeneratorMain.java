package cn.org.rapid_framework.generator;

/**
 * <pre>
 *      author: solverpeng
 *      blog  : http://solverpeng.com
 *      time  : 2017/2/22
 *      desc  : 代码生成器
 * </pre>
 */
public class GeneratorMain {
    /**
     * 请直接修改以下代码调用不同的方法以执行相关生成任务.
     */
    public static void main(String[] args) throws Exception {
        GeneratorFacade g = new GeneratorFacade();
        //删除生成器的输出目录
        g.deleteOutRootDir();
        //系统表生成
        g.generateByTable("users", "template");    //通过数据库表生成文件,template为模板的根目录
        //打开文件夹
        Runtime.getRuntime().exec("cmd.exe /c start " + GeneratorProperties.getRequiredProperty("outRoot"));
    }
}
