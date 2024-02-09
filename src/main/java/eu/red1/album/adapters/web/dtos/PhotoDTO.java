package eu.red1.album.adapters.web.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;
import lombok.Getter;

/**
 * PhotoDTO
 */

public class PhotoDTO {

  private Long id;

  private String title;

  private String url;

  private String thumbnailUrl;

  private Long albumId;

  @Getter
  @JsonBackReference
  private AlbumDTO album;

  public void setAlbum(AlbumDTO album) {
    this.album = album;
  }

  public PhotoDTO id(Long id) {
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

  public PhotoDTO title(String title) {
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

  public PhotoDTO url(String url) {
    this.url = url;
    return this;
  }

  /**
   * Get url
   * @return url
  */
  
  @JsonProperty("url")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public PhotoDTO thumbnailUrl(String thumbnailUrl) {
    this.thumbnailUrl = thumbnailUrl;
    return this;
  }

  /**
   * Get thumbnailUrl
   * @return thumbnailUrl
  */
  
  @JsonProperty("thumbnailUrl")
  public String getThumbnailUrl() {
    return thumbnailUrl;
  }

  public void setThumbnailUrl(String thumbnailUrl) {
    this.thumbnailUrl = thumbnailUrl;
  }

  public PhotoDTO albumId(Long albumId) {
    this.albumId = albumId;
    return this;
  }

  /**
   * Get albumId
   * @return albumId
  */
  
  @JsonProperty("albumId")
  public Long getAlbumId() {
    return albumId;
  }

  public void setAlbumId(Long albumId) {
    this.albumId = albumId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PhotoDTO photoDTO = (PhotoDTO) o;
    return Objects.equals(this.id, photoDTO.id) &&
        Objects.equals(this.title, photoDTO.title) &&
        Objects.equals(this.url, photoDTO.url) &&
        Objects.equals(this.thumbnailUrl, photoDTO.thumbnailUrl) &&
        Objects.equals(this.albumId, photoDTO.albumId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, url, thumbnailUrl, albumId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PhotoDTO {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    thumbnailUrl: ").append(toIndentedString(thumbnailUrl)).append("\n");
    sb.append("    albumId: ").append(toIndentedString(albumId)).append("\n");
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

