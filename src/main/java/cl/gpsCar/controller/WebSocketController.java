package cl.gpsCar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import cl.gpsCar.service.IGpsService;

@Controller
public class WebSocketController {

	Logger logger = LoggerFactory.getLogger(WebSocketController.class);
    private final SimpMessagingTemplate template;
    
    @Autowired
    private IGpsService service;

    @Autowired
    WebSocketController(SimpMessagingTemplate template){
        this.template = template;
    }

    @MessageMapping("/send/message")
    @SendTo("/message")
    public boolean sendMessage(String message){
    	logger.info("A DEBUG Message" + message);
    	System.out.println(message);
    	boolean respuestaServicio = false;
    	respuestaServicio = service.obtenerDistanciaEntrePuntos(message);
        return respuestaServicio;
    }
}
