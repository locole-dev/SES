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
public class Token implements Serializable, Cloneable {

    private Long id;
    private Long userId;
    private String token;
}
