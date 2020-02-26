package test;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Ryan Miao
 * @date 2020/2/26 15:19
 */
@Data
public class MajorEconomic {
    @ExcelProperty(index = 0)
    private Integer seq;
    @ExcelProperty(index = 1)
    private String villageName;
    @ExcelProperty(index = 2)
    private String type;

    @ExcelProperty(index = 3)
    private String name;
    @ExcelProperty(index = 4)
    private String head;
    @ExcelProperty(index = 5)
    private String businessScope;
    @ExcelProperty(index = 6)
    private Integer annualSalesMoney;
    @ExcelProperty(index = 7)
    private String address;
    @ExcelProperty(index = 8)
    private String employeeNum;
    @ExcelProperty(index = 9)
    private String remark;
}
