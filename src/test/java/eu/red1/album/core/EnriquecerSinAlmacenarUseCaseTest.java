package eu.red1.album.core;

import eu.red1.album.AlbumsDataset;
import eu.red1.album.adapters.AlbumApiClientPort;
import eu.red1.album.adapters.AlbumRepositoryPort;
import eu.red1.album.core.model.Album;
import eu.red1.album.core.model.Photo;
import eu.red1.album.core.service.AlbumService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EnriquecerSinAlmacenarUseCaseTest {


  private AlbumRepositoryPort repositoryPort;
  private AlbumApiClientPort apiClient;
  private AlbumService albumService;

  @Before
  public void setUp() {
    repositoryPort = mock(AlbumRepositoryPort.class);
    apiClient = mock(AlbumApiClientPort.class);
    albumService = new AlbumService(repositoryPort, apiClient);
  }

  @Test
  public void testEnrichAlbums_Success() {
    // Arrange
    List<Album> mockAlbums = createSampleAlbums();
    List<Photo> mockPhotos = createSamplePhotos();

    mockGetAlbums(mockAlbums);
    mockGetPhotos(mockPhotos);
    when(repositoryPort.saveAll(anyList())).thenReturn(mockAlbums);

    // Act
    List<Album> result = albumService.enrichAlbums();

    // Assert
    assertNotNull(result);
    assertFalse(result.isEmpty());
    // Add more assertions based on your expectations for the result
    verify(repositoryPort, times(1)).saveAll(anyList());
  }

  private void mockGetPhotos(List<Photo> mockPhotos) {
    when(apiClient.getPhotos()).thenReturn(mockPhotos);
  }

  private void mockGetAlbums(List<Album> mockAlbums) {
    when(apiClient.getAlbums()).thenReturn(mockAlbums);
  }

  @Test
  public void testEnrichAlbums_EmptyInput() {
    // Arrange
    mockGetAlbums(new ArrayList<>());
    mockGetPhotos(new ArrayList<>());

    // Act
    List<Album> result = albumService.enrichAlbums();

    // Assert
    assertNotNull(result);
    assertTrue(result.isEmpty());
    verify(repositoryPort, never()).saveAll(anyList());
  }

  @Test
  public void testEnrichAlbums_NullInput() {
    // Arrange
    mockGetAlbums(null);
    mockGetPhotos(null);

    // Act
    List<Album> result = albumService.enrichAlbums();

    // Assert
    assertNotNull(result);
    assertTrue(result.isEmpty());
    verify(repositoryPort, never()).saveAll(anyList());
    verify(apiClient, never()).getPhotos();

  }

  // Helper method to create sample albums for testing
  private List<Album> createSampleAlbums() {
    return List.of(AlbumsDataset.getAlbums()[0],AlbumsDataset.getAlbums()[1],AlbumsDataset.getAlbums()[3]);
  }

  // Helper method to create sample photos for testing
  private List<Photo> createSamplePhotos() {
    // Create and add sample photos to the list
    return List.of(AlbumsDataset.getPhotos()[0],AlbumsDataset.getPhotos()[1],AlbumsDataset.getPhotos()[3]);
  }
}
