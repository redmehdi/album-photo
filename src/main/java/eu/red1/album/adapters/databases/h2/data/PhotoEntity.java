package eu.red1.album.adapters.databases.h2.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class PhotoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String url;
  private String thumbnailUrl;

  @ManyToOne
  @JoinColumn(name = "album_id")
  private AlbumEntity album;

  public PhotoEntity(Long id, String title, String url, String thumbnailUrl, AlbumEntity album) {
    this.id = id;
    this.title = title;
    this.url = url;
    this.thumbnailUrl = thumbnailUrl;
    this.album = album;
  }

  public PhotoEntity() {
    // Default constructor
  }
}