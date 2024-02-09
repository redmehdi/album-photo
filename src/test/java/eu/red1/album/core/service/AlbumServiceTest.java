package eu.red1.album.core.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import eu.red1.album.adapters.AlbumApiClientPort;
import eu.red1.album.adapters.AlbumRepositoryPort;
import eu.red1.album.core.model.Album;
import eu.red1.album.core.model.Photo;
import eu.red1.album.core.service.AlbumService;
import java.util.Collections;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlbumServiceTest {

  private AlbumApiClientPort albumApiClient;
  private AlbumRepositoryPort repositoryPort;
  private AlbumService albumService;

  @BeforeEach
  void setUp() {
    albumApiClient = mock(AlbumApiClientPort.class);
    repositoryPort = mock(AlbumRepositoryPort.class);
    albumService = new AlbumService(repositoryPort, albumApiClient);
  }

  @Test
  void testGetEnrichedData_AlbumsAndPhotosMatch() {
    // Mock data for albums and photos
    Album album1 = new Album(1L, 3L, "Album 1", new ArrayList<>());
    Album album2 = new Album(2L, 4L, "Album 2", new ArrayList<>());
    Photo photo1 = new Photo(1L, "Title 1", "https://example.com/photo1.jpg", "", album1, 3L);
    Photo photo2 = new Photo(2L, "Title 2", "https://example.com/photo2.jpg", "", album1, 3L);
    Photo photo3 = new Photo(3L, "Title 3", "https://example.com/photo3.jpg", "", album1, 3L);
    List<Album> albums = Arrays.asList(album1, album2);
    List<Photo> photos = Arrays.asList(photo1, photo2, photo3);
    album1.setPhotos(photos);
    album2.getPhotos().add(photo1);

    // Stub external API calls
    when(repositoryPort.findAll()).thenReturn(albums);

    // Invoke service method
    List<Album> enrichedAlbums = albumService.getAlbums();

    // Assertions
    Assertions.assertEquals(2, enrichedAlbums.size());
    Assertions.assertEquals(3, enrichedAlbums.get(0).getPhotos().size());
    Assertions.assertEquals(1, enrichedAlbums.get(1).getPhotos().size());
  }

  @Test
  void testGetEnrichedData_AlbumsWithoutPhotos() {
    // Mock data for albums without photos
    Album album1 = new Album(1L, 3L, "Album 1", new ArrayList<>());
    Album album2 = new Album(2L, 4L, "Album 2", new ArrayList<>());

    // Stub external API calls
    when(repositoryPort.findAll()).thenReturn(Arrays.asList(album1, album2));

    // Invoke service method
    List<Album> enrichedAlbums = albumService.getAlbums();

    // Assertions
    Assertions.assertEquals(2, enrichedAlbums.size());
    Assertions.assertTrue(enrichedAlbums.get(0).getPhotos().isEmpty());
    Assertions.assertTrue(enrichedAlbums.get(1).getPhotos().isEmpty());
  }

  @Test
  void testGetEnrichedData_EmptyAlbumsAndPhotos() {
    // Mock data for empty albums and photos
    List<Album> albums = new ArrayList<>();
    List<Photo> photos = new ArrayList<>();

    // Stub external API calls
    when(repositoryPort.findAll()).thenReturn(albums);

    // Invoke service method
    List<Album> enrichedAlbums = albumService.getAlbums();

    // Assertions
    Assertions.assertTrue(enrichedAlbums.isEmpty());
  }

  @Test
  void testGetEnrichedData_NullAlbumsAndPhotos() {
    // Stub external API calls returning null
    when(repositoryPort.findAll()).thenReturn(Collections.emptyList());

    // Invoke service method
    List<Album> enrichedAlbums = albumService.getAlbums();

    // Assertions
    Assertions.assertTrue(enrichedAlbums.isEmpty());
  }

  @Test
  void testGetEnrichedData_NullPhotosInAlbums() {
    // Mock data for albums with null photos
    Album album1 = new Album(1L, 3L, "Album 1", null);
    Album album2 = new Album(2L, 4L, "Album 2", null);

    // Stub external API calls
    when(repositoryPort.findAll()).thenReturn(Arrays.asList(album1, album2));

    // Invoke service method
    List<Album> enrichedAlbums = albumService.getAlbums();

    // Assertions
    Assertions.assertEquals(2, enrichedAlbums.size());
    Assertions.assertNull(enrichedAlbums.get(0).getPhotos());
    Assertions.assertNull(enrichedAlbums.get(1).getPhotos());
  }
}
