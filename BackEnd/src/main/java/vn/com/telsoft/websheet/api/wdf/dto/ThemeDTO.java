package vn.com.telsoft.websheet.api.wdf.dto;

import lombok.*;

/**
 * Project: WDF
 * Author: LAMLT
 * Created on: 6/3/25
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThemeDTO {
    private int status;
    private String style;
    private String logoUrl;
    private String backgroundUrl;
}