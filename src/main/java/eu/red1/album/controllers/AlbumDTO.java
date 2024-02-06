package eu.red1.album.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * AlbumDTO
 */

public class AlbumDTO {

  private Long userId;

  private Long id;

  private String title;

  private List<PhotoDTO> photos = new ArrayList<>();
  public AlbumDTO userId(Long userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
   */

  @JsonProperty("userId")
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public AlbumDTO id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */

  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public AlbumDTO title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
   */

  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public AlbumDTO photos(List<PhotoDTO> photos) {
    this.photos = photos;
    return this;
  }

  public AlbumDTO addPhotosItem(PhotoDTO photosItem) {
    if (this.photos == null) {
      this.photos = new ArrayList<>();
    }
    this.photos.add(photosItem);
    return this;
  }

  /**
   * Get photos
   * @return photos
   */
  @JsonProperty("photos")
  public List< PhotoDTO> getPhotos() {
    return photos;
  }

  public void setPhotos(List<PhotoDTO> photos) {
    this.photos = photos;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AlbumDTO albumDTO = (AlbumDTO) o;
    return Objects.equals(this.userId, albumDTO.userId) &&
        Objects.equals(this.id, albumDTO.id) &&
        Objects.equals(this.title, albumDTO.title) &&
        Objects.equals(this.photos, albumDTO.photos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, id, title, photos);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AlbumDTO {\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    photos: ").append(toIndentedString(photos)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

