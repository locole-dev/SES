package vn.com.telsoft.websheet.api.wdf.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.com.telsoft.websheet.api.wdf.model.Carrier;

import java.text.SimpleDateFormat;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 6/3/25
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarrierMappingsDTO {
    private Long id;
    private Long carrierId;
    private Long smartWatchId;
    private Long logoId;
    private Long carrierLogoId;
    private Long backgroundId;
    private int status;
    private String style;
    private String description;
    private String logoWatchUrl;
    private String smartWatchName;
    private String backgroundUrl;
}