import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: SayHello
 * @Date: 2023/3/3 9:40
 * @Introduction: 页面静态化技术：【模板+数据=文本】
 */
public class FreemarkerTest {
    public static void main(String[] args) throws IOException, TemplateException {
        //【模板+数据=文本】
        //1、获取模板
        Configuration cfg = new Configuration(Configuration.getVersion());
        cfg.setDirectoryForTemplateLoading(new File("E:\\freemarker"));
        cfg.setDefaultEncoding("utf-8");
        Template template = cfg.getTemplate("test.ftl");

        //2、添加数据
        Map<String, String> data = new HashMap();
        data.put("username", "jack");
        data.put("password", "rose");

        //3、创建静态页面，输出数据
        Writer out = new FileWriter(new File("E:\\freemarker\\test.html"));
        template.process(data, out);

        //4、关闭流
        out.close();
    }
}
