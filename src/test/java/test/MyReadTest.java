package test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.TestFileUtil;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 特殊需求读取测试
 */
@Slf4j
public class MyReadTest {

    @Test
    public void testReadAll() {
        String fileName = TestFileUtil.getPath() + "test" + File.separator + "test.xlsx";
          // 我们可以直接全部取出excel的数据， 然后自己自定义处理就好。 针对excel数据不大的时候可以这么干， 大数据也不会复杂了
        // 这里 也可以不指定class，返回一个list，然后读取第一个sheet 同步读取会自动finish
        List<Map<Integer, String>> listMap = EasyExcel.read(fileName).sheet().doReadSync();
        for (Map<Integer, String> data : listMap) {
            // 返回每条数据的键值对 表示所在的列 和所在列的值
            log.info("读取到数据:{}", JSON.toJSONString(data));
        }

        // 如果数据量特别大， 则可以一行行去获取，参见 @demo.TestRead.noModleRead

    }
}
