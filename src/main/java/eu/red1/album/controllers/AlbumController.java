package eu.red1.album.controllers;

import eu.red1.album.service.AlbumService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enriquecer-y-almacenar/albums")
public class AlbumController {

  @Autowired
  private AlbumService albumService;

  @GetMapping
  public ResponseEntity<List<AlbumDTO>> getAlbums() {
    List<AlbumDTO> albums = albumService.getAlbums();
    return ResponseEntity.ok(albums);
  }

  @PostMapping
  public ResponseEntity<List<AlbumDTO>> enrichAlbums() {
    List<AlbumDTO> enrichedAlbums = albumService.enrichAlbums();
    return ResponseEntity.ok(enrichedAlbums);
  }
}

