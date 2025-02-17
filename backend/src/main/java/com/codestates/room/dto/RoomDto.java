package com.codestates.room.dto;

import com.codestates.tag.dto.TagDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class RoomDto {


    @Getter @Setter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Post{
        @JsonProperty("admin_member_id")
        private long adminMemberId;
        private String title;
        private String info;
        @JsonProperty("image_url")
        private String imageUrl;
        private int MemberMaxCount;
        @JsonProperty("is_private")
        private boolean isPrivate;
        private String password;
        private List<String> tags;
    }


    @Getter @Setter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class PostResponseDto {
        private long roomId;
        private String title;
        private String info;
        @JsonProperty("admin_member_id")
        private long adminMemberId;
        @JsonProperty("image_url")
        private String imageUrl;
        private int MemberMaxCount;
        private int MemberCurrentCount;
        @JsonProperty("is_private")
        private boolean isPrivate;
        private String password;
        private int favoriteCount;
        private List<TagDto.TagResponseDto> tags; //태그네임 DTO 에서 변경
    }



    @Getter @Setter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Patch{
        private long roomId;
        @JsonProperty("admin_member_id")
        private long adminMemberId;
        private String title;
        private String info;
        private int MemberMaxCount;
        @JsonProperty("is_private")
        private boolean isPrivate;
        private String password;
        private List<TagDto.TagResponseDto> tags;
    }



    //Todo: 방수정 응답
    @Getter @Setter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class PatchResponseDto {
        private long roomId;
        private String title;
        private String info;
        @JsonProperty("admin_nickname")
        private String adminNickname;
        @JsonProperty("image_url")
        private String imageUrl;
        private int MemberMaxCount;
        private int MemberCurrentCount;
        @JsonProperty("is_private")
        private boolean isPrivate;
        private String password;
        private int favoriteCount;
        @JsonProperty("is_favorite")
        private boolean isFavorite;
        private List<TagDto.TagResponseDto> tags;
    }



    @Getter @Setter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class PatchAdmin {
        @Positive
        private long roomId;
        @Positive @JsonProperty("new_admin_id")
        private long newAdminId;
    }



    @Getter @Setter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class PatchAdminResponseDto {
        @Positive
        private long roomId;
        @JsonProperty("admin_member_id")
        private long adminMemberId;
        @JsonProperty("admin_nickname")
        private String adminNickname;
        @JsonProperty("image_url")
        private String imageUrl;
    }



    @Getter @Setter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class GetRoomResponseDtos {
        @Positive
        private long roomId;
        @NotBlank
        private String title;
        private String info;
        @JsonProperty("image_url")
        private String imageUrl;
        private int MemberMaxCount;
        private int MemberCurrentCount;
        @JsonProperty("is_private")
        private boolean isPrivate;
        private String password;
        private int favoriteCount;
        @JsonProperty("is_favorite")
        private boolean isFavorite;
        @JsonProperty("created_at")
        private LocalDateTime createdAt;
        private List<TagDto.TagResponseDto> tags;
    }



    @Getter @Setter @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class SearchResponseDto {
        private long roomId;
        private String title;
        private String info;
        @JsonProperty("image_url")
        private String imageUrl;
        private int MemberMaxCount;
        private int MemberCurrentCount;
        @JsonProperty("is_private")
        private boolean isPrivate;
        private String password;
        private int favoriteCount;
        @JsonProperty("is_favorite")
        private boolean isFavorite;
        @JsonProperty("created_at")
        private LocalDateTime createdAt;
        private List<TagDto.TagResponseDto> tags;
    }


    @Getter @Setter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class CheckTitle {
        private String title;
    }
}
