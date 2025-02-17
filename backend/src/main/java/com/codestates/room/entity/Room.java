package com.codestates.room.entity;

import com.codestates.common.history.RoomHistory;
import com.codestates.favorite.Favorite;
import com.codestates.member.entity.MemberRoom;
import com.codestates.common.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Room extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @Column(nullable = false)
    private Long adminMemberId;

    @Column
    private String adminNickname;

    @Column(nullable = false)
    private String title;

    @Column
    private String info;

    @Column
    private String imageUrl;

    @Column
    private String password;

    @Column(nullable = false)
    private boolean isPrivate;

    @Column(nullable = false)
    private int memberMaxCount;

    @Column
    private int memberCurrentCount;

    @Column
    private int favoriteCount;

    @OneToMany(mappedBy = "room")
    private List<MemberRoom> memberRoomList = new ArrayList<>();


    @OneToMany(mappedBy = "room" , cascade = CascadeType.ALL) //ALL
    private List<RoomTag> roomTagList = new ArrayList<>();

    @OneToMany(mappedBy = "room")
    private List<RoomHistory> roomHistoryList = new ArrayList<>(); //history

    public List<RoomTag> setRoomTagList(List<RoomTag> roomTagList) {
        this.roomTagList = roomTagList;
        return roomTagList;
    }

    //리팩토링 1차
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Favorite> favoriteRoomList;
}



