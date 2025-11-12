package org.basr.pinpoint.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GameFullResponseDto extends GameBaseDto {
    private Long leagueId;
    private Long teamId;
    private Long playerId;
}
