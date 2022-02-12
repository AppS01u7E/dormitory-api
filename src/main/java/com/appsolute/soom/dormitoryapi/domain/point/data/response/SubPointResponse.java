package com.appsolute.soom.dormitoryapi.domain.point.data.response;

import com.appsolute.soom.dormitoryapi.domain.point.data.type.PointType;
import com.fasterxml.jackson.annotation.JsonProperty;

public record SubPointResponse(
        @JsonProperty("accountUUID") String accountUUID,
        @JsonProperty("updatedRewardPoint") Integer rewardPoint,
        @JsonProperty("updatedPenaltyPoint") Integer penaltyPoint,
        @JsonProperty("type") PointType type
) {
}
