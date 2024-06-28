package com.mavericks.mavericksHub.dtos.responses;

import com.mavericks.mavericksHub.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GetLikersResponse {
    private List<User> usersList;
}
