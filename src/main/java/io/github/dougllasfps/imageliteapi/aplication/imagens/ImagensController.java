package io.github.dougllasfps.imageliteapi.aplication.imagens;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//ENDPOINT PARA RECEBIMENTO DE IMAGENS
@RestController //Anotação que o controlador REST precisa para receber requisições
@RequestMapping("/v1/images") //Anotação para dizer qual a url que ele vai escutar
@Slf4j //Faz com que o Lombok adicione um objeto de log para utilizarmos nessa classe
public class ImagensController {

    /**
     *
     * { "name": "", "size": 100} //application/jason
     *
     * mult-part/formdata
     */
    @PostMapping//Quem for acessar a API para salvar uma imagem, fará uma requisição post para v1 images
    //Método que vai receber o upload de imagem
    public ResponseEntity save(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("tags") List<String> tags
            ){
        log.info("Imagem recebida: name: {}, size: {}", file.getOriginalFilename(), file.getSize());
        log.info("Nome definido para a imagem: {}", name);
        log.info("Tags: {}", tags);
        return ResponseEntity.ok().build();//O ResponseEntity é a classe responsável por representar uma resposta HTTP para quem está acessando a API
    }
}
