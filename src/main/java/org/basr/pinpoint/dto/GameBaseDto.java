package org.basr.pinpoint.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameBaseDto {
    private Long id;
    private LocalDate datePlayed;
    private Integer pinfall;
    private Integer gameNumber;
}
