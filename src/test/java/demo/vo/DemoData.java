package demo.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class DemoData {
    @ExcelProperty(value = "字符串", index = 0)
    private String string;
    @ExcelProperty(value = "日期", index = 1)
    private Date date;
    @ExcelProperty(value = "小数", index = 2)
    private Double doubleData;
}
