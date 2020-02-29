package test.read;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.LinkedHashMap;

/**
 * @author Ryan Miao
 * @date 2020/2/25 10:19
 */
public class TestConstant {

    public static LinkedHashMap<String, LinkedHashMap<String, String>> baseVar() {
        LinkedHashMap ret = loadConf();

        LinkedHashMap<String, LinkedHashMap<String, String>> base =
                (LinkedHashMap<String, LinkedHashMap<String, String>>) ret.get("base");
        return base;
    }
    public static LinkedHashMap<String, LinkedHashMap<String, String>> othersVar() {
        LinkedHashMap ret = loadConf();

        LinkedHashMap<String, LinkedHashMap<String, String>> base =
                (LinkedHashMap<String, LinkedHashMap<String, String>>) ret.get("others");
        return base;
    }

    private static LinkedHashMap loadConf() {
        Yaml yaml = new Yaml();
        InputStream resource = TestConstant.class.getClassLoader()
                .getResourceAsStream("test/test-conf.yml");
        LinkedHashMap ret = yaml.load(resource);
        System.out.println(ret);
        return ret;
    }


    public static void main(String[] args) {
        LinkedHashMap<String, LinkedHashMap<String, String>> base = baseVar();

        for (int i = 0; i < 40; i++) {
            LinkedHashMap<String, String> cell = base.get("L-" + i);
            if (cell != null) {
                for (int j = 0; j < 4; j++) {
                    String s = cell.get("C-" + j);
                    if (s!=null)
                        System.out.println("第 " + i + " 行 第 " + j + " 列的变量为" + s);
                }
            }
        }


    }
}
