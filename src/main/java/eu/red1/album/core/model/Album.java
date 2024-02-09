package eu.red1.album.core.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AlbumDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {

  private Long userId;

  private Long id;

  private String title;

  private List<Photo> photos = new ArrayList<>();
}
