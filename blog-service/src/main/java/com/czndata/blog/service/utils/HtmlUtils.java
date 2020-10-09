package com.czndata.blog.service.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.List;

public class HtmlUtils {
    public static List<String> extractDirectory(String html){
        Document document = Jsoup.parse(html);
        return document.select("h2").eachText();
    }

    public static void main(String[] args) {

        String s = "### Centos 安装jdk\n" +
                "1. 查看yum包含的jdk版本\n" +
                "\n" +
                "`$ yum list java*`\n" +
                "2. 这里我们下载jdk1.8\n" +
                "\n" +
                "`$ yum install java-1.8.0-openjdk-devel.x86_64`\n" +
                "3. 配置全局变量\n" +
                "\n" +
                "注意点：JAVA_HOME需要修改，查看/usr/lib/jvm路径下的java-1.8.0-openjdk名称略有不同\n" +
                "\n" +
                "`$ vim ~/.bashrc`\n" +
                "```\n" +
                "export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk\n" +
                "export CLASSPATH=.:$JAVA_HOME/jre/lib/rt.jar:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar\n" +
                "export PATH=$PATH:$JAVA_HOME/bin\n" +
                "```\n" +
                "4. 全局变量生效\n" +
                "\n" +
                "`$ source ~/.bashrc`\n" +
                "5. java 版本查看\n" +
                "\n" +
                "`$ java -version`\n" +
                " \n" +
                "\n" +
                "Windows Java环境配置\n" +
                "1. 自行安装windows jdk\n" +
                "\n" +
                "2. JAVA_HOME 环境变量配置 例如C:\\Program Files\\Java\\jdk1.8.0_191\n" +
                "\n" +
                "3. Path 环境变量新增%JAVA_HOME%\\bin,%JAVA_HOME%\\jre\\bin";
        HtmlUtils.extractDirectory(MarkdownUtils.markdown2Html(s)).stream().forEach(System.out::println);
        System.out.println(HtmlUtils.extractDirectory(MarkdownUtils.markdown2Html(s)));
    }
}
