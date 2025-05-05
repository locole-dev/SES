package vn.com.telsoft.websheet.api.wdf.dto;

import lombok.*;
import vn.com.telsoft.websheet.api.wdf.model.Carrier;

import java.sql.Date;
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
public class CarrierDTO {
    private Long id;
    private String name;
    private Long logoId;
    private String logoUrl;
    private int status;
    private String createdDate;

    public CarrierDTO(Carrier carrier) {
        this.id = carrier.getId();
        this.name = carrier.getName();
        this.logoId = carrier.getLogoId();
        this.status = carrier.getStatus();
        this.createdDate = new SimpleDateFormat("MMM dd, yyyy").format(carrier.getCreatedDate());
    }
}