package ru.dubovitsky.memorush.dto.bidirectional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DictionaryItemDto {

    @JsonIgnore
    public Integer id;
    public String ru;
    public String en;
    public String tr;

}
