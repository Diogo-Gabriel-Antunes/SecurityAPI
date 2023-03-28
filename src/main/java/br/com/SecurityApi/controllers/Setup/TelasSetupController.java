package br.com.SecurityApi.controllers.Setup;


import br.com.SecurityApi.service.setup.SetupTelasService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("setup")
public class TelasSetupController {

    @Autowired
    private SetupTelasService setupTelasService;
    @GetMapping("telas")
    public ResponseEntity setupTelas(){
        List telas = setupTelasService.create();
        return ResponseEntity.ok(telas);
    }
}
