package test;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Ryan Miao
 * @date 2020/2/26 15:19
 */
@Data
public class MajorPerson {
    @ExcelProperty(index = 0)
    private Integer seq;
    @ExcelProperty(index = 1)
    private String villageName;
    @ExcelProperty(index = 2)
    private String professionType;

    @ExcelProperty(index = 3)
    private String name;
    @ExcelProperty(index = 4)
    private String sex;
    @ExcelProperty(index = 5)
    private Integer age;
    @ExcelProperty(index = 6)
    private String units;
    @ExcelProperty(index = 7)
    private String post;
    @ExcelProperty(index = 8)
    private String remark;
}
