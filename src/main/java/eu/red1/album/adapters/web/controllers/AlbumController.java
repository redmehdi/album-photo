package eu.red1.album.adapters.web.controllers;

import eu.red1.album.adapters.AlbumDtoMapper;
import eu.red1.album.adapters.web.dtos.AlbumDTO;
import eu.red1.album.core.EnriquecerSinAlmacenarUseCase;
import eu.red1.album.core.EnriquecerYAlmacenarUseCase;
import eu.red1.album.core.GetAllAlbumUseCase;
import eu.red1.album.core.service.AlbumService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AlbumController {

  @Autowired
  private EnriquecerYAlmacenarUseCase enriquecerYAlmacenarUseCase;

  @Autowired
  private EnriquecerSinAlmacenarUseCase enriquecerSinAlmacenarUseCase;

  @Autowired
  private GetAllAlbumUseCase getAllAlbumUseCase;


  @GetMapping("enriquecer-y-almacenar/albums")
  public ResponseEntity<List<AlbumDTO>> getAlbums() {
    List<AlbumDTO> albumDTOS = Streamable.of(getAllAlbumUseCase.getAlbums())
        .map(AlbumDtoMapper::mapToAlbumDTO).toList();
    return ResponseEntity.ok(albumDTOS);
  }

  @PostMapping("enriquecer-y-almacenar/albums")
  public ResponseEntity<List<AlbumDTO>> enrichAlbums() {
    List<AlbumDTO> albumDTOS = Streamable.of(enriquecerYAlmacenarUseCase.enrichAlbums())
        .map(AlbumDtoMapper::mapToAlbumDTO).toList();
    return ResponseEntity.ok(albumDTOS);
  }

  @PostMapping("enriquecer-sin-almacenar/albums")
  public ResponseEntity<List<AlbumDTO>> enrichSinAlbums() {
    List<AlbumDTO> albumDTOS = Streamable.of(enriquecerSinAlmacenarUseCase.enrichSinAlbums())
        .map(AlbumDtoMapper::mapToAlbumDTO).toList();
    return ResponseEntity.ok(albumDTOS);
  }
}