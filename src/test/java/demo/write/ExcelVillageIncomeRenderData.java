package demo.write;

import lombok.Data;

/**
 * @author Ryan Miao
 * @date 2020/3/2 18:06
 */
@Data
public class ExcelVillageIncomeRenderData {

    /**
     * 村民收入
     */
    private String villageIncomeName;
    private String villageIncomeScale;
    private String villageIncomeRate;
    private String villageIncomeProvideUnit;
    private String villageIncomeProvideType;
}
