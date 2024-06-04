
package uz.app;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor@NoArgsConstructor
public class Currency {


    private String Ccy;
    private String CcyNm_EN;
    private String CcyNm_RU;
    private String CcyNm_UZ;
    private String CcyNm_UZC;
    private String Code;
    private String Date;
    private String Diff;
    private Long id;
    private String Nominal;
    private String Rate;


}
