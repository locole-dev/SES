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
public class ApParam implements Serializable, Cloneable {

    private Long id;
    private String name;
    private String value;
    private String type;
    private String description;
}
