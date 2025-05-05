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
public class Logo implements Serializable, Cloneable {

    private Long id;
    private String data;
    private int type;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM dd, yyyy")
    private Timestamp createdDate;
}
