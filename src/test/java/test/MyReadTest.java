package test;

import com.alibaba.excel.EasyExcel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.TestFileUtil;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 特殊需求读取测试
 */
@Slf4j
public class MyReadTest {

    @Test
    public void testReadBase() {
        String fileName = TestFileUtil.getPath() + "test" + File.separator + "test.xlsx";
        List<Map<Integer, String>> listMap = EasyExcel.read(fileName).sheet().doReadSync();
        /**
         * 行数:  {列数: 变量名称 }
         */
        LinkedHashMap<String, LinkedHashMap<String, String>> baseVar = TestConstant.baseVar();
        /**
         * 解析完毕后的变量 {varName: value}
         */
        Map<String, String> readValMap = new HashMap<>();
        int lineNum = 1;
        for (Map<Integer, String> data : listMap) {
            // 返回每条数据的键值对 表示所在的列 和所在列的值
            lineNum = lineNum + 1;
//            log.debug("\n读取到数据:, 第{}行: {}", lineNum, JSON.toJSONString(data));
            LinkedHashMap<String, String> line = baseVar.get("L-" + lineNum);
            if (line == null) {
                continue;
            }

            for (Map.Entry<Integer, String> entry : data.entrySet()) {
                Integer columnId = entry.getKey();
                String cellVar = line.get("C-" + columnId);
                if (cellVar == null) {
                    continue;
                }
                if (log.isDebugEnabled()) {
                    log.debug("第{} 行, 第 {} 列, {}={}", lineNum, columnId, cellVar, entry.getValue());
                }
                readValMap.put(cellVar, entry.getValue());
            }

        }

        log.debug("读取到的变量为:{}", readValMap);


    }

    @Test
    public void testReadOthers() {
        String fileName = TestFileUtil.getPath() + "test" + File.separator + "test.xlsx";
        List<Map<Integer, String>> listMap = EasyExcel.read(fileName).sheet(1).doReadSync();
        // L3 C2 农民平均收入
        // L5
        //  C0 名称
        //  C1 规模
        //  C2 频率
        //  C3 发放单位
        //  C4 发放形式


    }
}
