package test.read;

import com.alibaba.excel.EasyExcel;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.TestFileUtil;

import java.io.File;
import java.util.*;

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
        if (listMap.size() < 4) {
            log.error("数据错误, 至少4行");
            return;
        }

        Map<Integer, String> l2 = listMap.get(1);
        String income = l2.get(2);
        if (income != null) {
            log.info("村民人均收入: {}", income);
        }
        int lineNum = 1;
        LinkedHashMap<String, LinkedHashMap<String, String>> varMap = TestConstant.othersVar();
        LinkedHashMap<String, String> incomeAndExpendVar = varMap.get("L5-end");
        // 4 收入和支出
        List<Map<String, String>> incomeList = new ArrayList<>();
        List<Map<String, String>> expendList = new ArrayList<>();

        // 5 本村在振兴方面的亮点
        List<String> fiveSpot = new ArrayList<>();
        // 6 需求
        Map<String, String> sixNeeds = new HashMap<>();
        // 7 特产 特色
        List<String> feature = new ArrayList<>();

        for (Map<Integer, String> data : listMap) {
            lineNum = lineNum + 1;
            // 567 取出 10-13列
            String str = data.get(10);
            if (str != null) {
                fiveSpot.add(str);
            }

            String sixKey = data.get(11);
            String sixVal = data.get(12);
            if (sixKey != null) {
                sixVal = sixVal == null ? "" : sixVal;
                sixNeeds.put(sixKey, sixVal);
            }

            String featureStr = data.get(13);
            if (featureStr != null) {
                feature.add(featureStr);
            }

            // 收入和支出  取出0 -9 列
            if (lineNum > 4) {
                Map<String, String> incomeItem = new HashMap<>();
                Map<String, String> expendItem = new HashMap<>();
                for (int i = 0; i < 5; i++) {
                    String cellVar = incomeAndExpendVar.get("C-" + i);
                    String cellVal = data.get(i);
                    if (cellVal != null) {
                        incomeItem.put(cellVar, cellVal);
                    }
                }
                for (int i = 5; i < 10; i++) {
                    String cellVar = incomeAndExpendVar.get("C-" + i);
                    String cellVal = data.get(i);
                    if (cellVal != null) {
                        expendItem.put(cellVar, cellVal);
                    }
                }

                incomeList.add(incomeItem);
                expendList.add(expendItem);
            }

        }

        System.out.println("income============");
        System.out.println(incomeList);

        System.out.println("expend=============");
        System.out.println(expendList);

        System.out.println("five=============");
        System.out.println(fiveSpot);

        System.out.println("six==============");
        System.out.println(sixNeeds);

        System.out.println("feature===========");
        System.out.println(feature);



    }

    @Test
    public void testMajorPeople() {
        String fileName = TestFileUtil.getPath() + "test" + File.separator + "test.xlsx";
        EasyExcel.read(fileName, MajorPerson.class, new MajorPersonListener()).sheet(3)
                .headRowNumber(2)
                .doRead();
    }

    @Test
    public void testMajorEconomic() {
        String fileName = TestFileUtil.getPath() + "test" + File.separator + "test.xlsx";
        EasyExcel.read(fileName, MajorEconomic.class, new MajorEconomicListener()).sheet(2)
                .headRowNumber(2)
                .doRead();
    }
    @Test
    public void testMajorPerson() {
        String fileName = TestFileUtil.getPath() + "test" + File.separator + "test.xlsx";
        EasyExcel.read(fileName, MajorPerson.class, new MajorPersonListener()).sheet(3)
                .headRowNumber(2)
                .doRead();
    }
}
