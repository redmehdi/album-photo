package eu.red1.album;

import eu.red1.album.core.model.Album;
import eu.red1.album.core.model.Photo;
import java.util.ArrayList;

public final class AlbumsDataset {

  public static Album[] getAlbums() {
    return new Album[]{
        new Album(),
        new Album(1L, 2L, "title1", new ArrayList<>()),
        new Album(2L, 3L, "title2", new ArrayList<>()),
        new Album(3L, 4L, "title3", new ArrayList<>()),
        new Album(4L, 5L, "title4", new ArrayList<>()),
        new Album(5L, 6L, "title5", new ArrayList<>())};
  }

  public static Photo[] getPhotos() {
    return new Photo[]{
        new Photo(),
        new Photo(1L,"title1","url1", "thumbnail1",null, 1L),
        new Photo(2L,"title1","url2", "thumbnail2",null, 2L),
        new Photo(3L,"title1","url3", "thumbnail3",null, 3L),
        new Photo(4L,"title1","url4", "thumbnail4",null, 4L),
        new Photo(5L,"title1","url5", "thumbnail5",null, 5L)};
  }

}
