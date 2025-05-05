package vn.com.telsoft.websheet.api.wdf.dto;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Project: WDF
 * Author: LOCT
 * Created on: 23/4/25
 */

@Data
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class DictionaryDataDto {
    private Map<String, String> vi;
    private Map<String, String> eng;

    public DictionaryDataDto() {
        this.vi = new HashMap<>();
        this.eng = new HashMap<>();
    }

    public Map<String, String> getVi() {
        return vi;
    }

    public void setVi(Map<String, String> vi) {
        this.vi = vi;
    }

    public Map<String, String> getEng() {
        return eng;
    }

    public void setEng(Map<String, String> eng) {
        this.eng = eng;
    }

    public void addVi(String key, String value) {
        vi.put(key, value);
    }

    public void addEng(String key, String value) {
        eng.put(key, value);
    }
}
