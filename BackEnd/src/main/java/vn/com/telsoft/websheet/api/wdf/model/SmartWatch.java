package vn.com.telsoft.websheet.api.wdf.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

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
public class SmartWatch implements Serializable, Cloneable {

    private Long id;
    private String name;
    private int status;
    private Long logoId;
    private Long dictionaryKey;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM dd, yyyy")
    private Timestamp createdDate;
}
