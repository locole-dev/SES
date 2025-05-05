package vn.com.telsoft.websheet.api.wdf.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.telsoft.websheet.api.wdf.model.SmartWatch;

import java.text.SimpleDateFormat;

/**
 * Project: WDF
 * Author: LOCVT
 * Created on: 22/4/25
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SmartWatchDtoV2 {
    private Long id;
    private String name;
    private Long logoId;
    private String logoUrl;
    private int status;
    private String createdDate;
    private Long dictionaryKey;
    private Boolean isDisabled;

    public SmartWatchDtoV2(SmartWatch smartWatch) {
        this.id = smartWatch.getId();
        this.name = smartWatch.getName();
        this.logoId = smartWatch.getLogoId();
        this.status = smartWatch.getStatus();
        this.createdDate = new SimpleDateFormat("MMM dd, yyyy").format(smartWatch.getCreatedDate());
        this.dictionaryKey = smartWatch.getDictionaryKey();
        this.isDisabled = false;
    }
}