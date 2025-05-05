package vn.com.telsoft.websheet.api.wdf.model;

import lombok.*;

import java.io.Serializable;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 28/2/25
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class CarrierMappings implements Serializable, Cloneable {

    private Long id;
    private Long carrierId;
    private Long smartWatchId;
    private Long logoId;
    private int status;
    private String style;
    private String description;
    private Long backgroundId;
}
