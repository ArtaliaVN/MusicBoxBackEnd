package com.example.Artalia.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "song_entity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SongEntity{
    @Id
    private int id;

    private String songname;

    private String artistname;

    private String imageurl = "null";

    private String imageid = "null";

    private String audiourl = "null";

    private String audioid = "null";

    private int userid;
}
