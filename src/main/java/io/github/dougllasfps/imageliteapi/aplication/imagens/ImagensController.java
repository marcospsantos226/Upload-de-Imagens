package io.github.dougllasfps.imageliteapi.aplication.imagens;

import io.github.dougllasfps.imageliteapi.domain.entity.Image;
import io.github.dougllasfps.imageliteapi.domain.enums.ImageExtension;
import io.github.dougllasfps.imageliteapi.domain.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

//ENDPOINT PARA RECEBIMENTO DE IMAGENS
@RestController //Anotação que o controlador REST precisa para receber requisições
@RequestMapping("/v1/images") //Anotação para dizer qual a url que ele vai escutar
@Slf4j //Faz com que o Lombok adicione um objeto de log para utilizarmos nessa classe
@RequiredArgsConstructor
public class ImagensController {

    private final ImageService service;

    @PostMapping//Quem for acessar a API para salvar uma imagem, fará uma requisição post para v1 images
    //Método que vai receber o upload de imagem
    public ResponseEntity save(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("tags") List<String> tags
            ) throws IOException {
        log.info("Imagem recebida: name: {}, size: {}", file.getOriginalFilename(), file.getSize());
        log.info("Content type: {}", file.getContentType());
        log.info("Media type: {}", MediaType.valueOf(file.getContentType()));


        Image image = Image.builder()
                .name(name)
                .tags(String.join( ", ", tags))
                .size(file.getSize())
                .extension(ImageExtension.valueOf(MediaType.valueOf(file.getContentType())))
                .file(file.getBytes())
                .build();

        service.save(image);

        return ResponseEntity.ok().build();
    }
}
