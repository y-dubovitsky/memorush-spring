package ru.dubovitsky.memorush.dto.bidirectional;

import lombok.*;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DictionaryDto {

    public String name;
    public Set<DictionaryItemDto> dictionaryItems;

}