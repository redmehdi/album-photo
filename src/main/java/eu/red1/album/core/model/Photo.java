package eu.red1.album.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Photo {

  private Long id;

  private String title;

  private String url;

  private String thumbnailUrl;

  private Album album;

  private Long albumId;
}
