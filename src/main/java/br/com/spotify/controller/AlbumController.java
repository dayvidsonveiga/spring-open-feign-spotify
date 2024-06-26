package br.com.spotify.controller;

import br.com.spotify.client.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spotify/api")
public class AlbumController {

    private final AuthSpotifyClient authSpotifyClient;
    private final AlbumSpotifyClient albumSpotifyClient;

    public AlbumController(AuthSpotifyClient authSpotifyClient,
                           AlbumSpotifyClient albumSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
        this.albumSpotifyClient = albumSpotifyClient;
    }

    @GetMapping("/albums")
    public ResponseEntity<List<Album>> helloWorld() {
        var request = new LoginRequest(
                "client_credentials",
                "49156b24fd6f4986b9b8c15f732cbdfb",
                "39957098675d478e8dc61da7c680e79d"
        );

        var token = authSpotifyClient.login(request).getAccessToken();

        AlbumResponse response = albumSpotifyClient.getReleases("Bearer " + token);

        return ResponseEntity.ok(response.getAlbums().getItems());
    }
}
