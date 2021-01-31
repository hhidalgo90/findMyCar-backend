package cl.gpsCar.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.gpsCar.service.IGpsService;

@RestController
public class GpsCarRestController {
	
	@Autowired
	IGpsService service;

	  @GetMapping("obtenerDistanciaEntrePuntos/{coordenadas}")
	  public boolean obtenerDistanciaEntrePuntos(@PathVariable("coordenadas")String coordenadas) {
	    return service.obtenerDistanciaEntrePuntos(coordenadas);
	  }
	  
	  @PostMapping("enviarMensajePush/{token}")
	  public String enviarMensajePush(@PathVariable("token")String token) {
			try {
				return service.enviarMensajePush(token);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "";
	  }
}
