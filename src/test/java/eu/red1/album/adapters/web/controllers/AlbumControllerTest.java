package eu.red1.album.adapters.web.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import eu.red1.album.adapters.AlbumApiClientPort;
import eu.red1.album.adapters.AlbumRepositoryPort;
import eu.red1.album.core.GetAllAlbumUseCase;
import eu.red1.album.core.model.Album;
import eu.red1.album.core.service.AlbumService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AlbumController.class)
@AutoConfigureMockMvc
public class AlbumControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AlbumService albumService;

  @MockBean
  private AlbumRepositoryPort repositoryPort;

  @MockBean
  private AlbumApiClientPort albumApiClient;

  private List<Album> testAlbums;

  @BeforeEach
  void setUp() {
    // Initialize test data
    Album album1 = new Album(1L, 1L, "Album 1", new ArrayList<>());
    Album album2 = new Album(2L, 2L, "Album 2", new ArrayList<>());
    testAlbums = Arrays.asList(album1, album2);
  }

  @Test
  void testGetAlbums() throws Exception {
    // Mock service behavior
    Mockito.when(albumService.getAlbums()).thenReturn(testAlbums);

    // Perform GET request
    mockMvc.perform(get("/enriquecer-y-almacenar/albums")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.size()").value(testAlbums.size()));
  }

  @Test
  void testGetAlbums_NoData() throws Exception {
    // Mock service behavior returning empty list
    Mockito.when(albumService.getAlbums()).thenReturn(Arrays.asList());

    // Perform GET request
    mockMvc.perform(get("/enriquecer-y-almacenar/albums")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.size()").value(0));
  }
}