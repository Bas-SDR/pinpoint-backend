package org.basr.pinpoint.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRequestDto {
    @NotNull
    @Positive
    private Long userId;
    @NotNull
    @Positive
    private Long teamId;
}
