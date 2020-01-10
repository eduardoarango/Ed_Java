/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.gob.reniec.geopadronn.service.impl;

import java.net.URI;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import pe.gob.reniec.geopadronn.utils.Mensaje;
import pe.gob.reniec.geopadronn.entity.Usuario;
import pe.gob.reniec.geopadronn.service.IUsuarioService;
import pe.gob.reniec.geopadronn.utils.AplicacionConst;

@Service
@Component
public class UsuarioService implements IUsuarioService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        int timeout = 5000;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout)
                .build();
        CloseableHttpClient client = HttpClientBuilder
                .create()
                .setDefaultRequestConfig(config)
                .build();
        return new HttpComponentsClientHttpRequestFactory(client);
    }

    @Override
    public Mensaje identificar(Usuario usuario) {
        try {
            RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            HttpEntity<Usuario> request;
            request = new HttpEntity<>(usuario, httpHeaders);
            ResponseEntity<Mensaje> response = restTemplate.postForEntity(URI.create(AplicacionConst.REST_SERVICE_URI + "api/identificar"), request, Mensaje.class);
            return response.getBody();
        } catch (Exception er) {
            logger.error(er.getMessage());
            return null;
        }
    }

    @Override
    public Mensaje identificar_dni(Usuario usuario) {
        try {
            RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
            ResponseEntity<Mensaje> response = restTemplate.postForEntity(AplicacionConst.REST_SERVICE_URI + "api/identificar_dni", usuario, Mensaje.class);
            return response.getBody();
        } catch (HttpStatusCodeException e) {
            logger.error(e.getResponseBodyAsString());

            return null;

        } catch (RestClientException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Mensaje identificar_cemp(Usuario usuario) {
        try {
            RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
            ResponseEntity<Mensaje> response = restTemplate.postForEntity(AplicacionConst.REST_SERVICE_URI + "api/identificar_cemp", usuario, Mensaje.class);
            return response.getBody();
        } catch (Exception er) {
            logger.error(er.getMessage());
            return null;
        }
    }

    @Override
    public Mensaje cerrar_sesion(Usuario usuario) {
        try {
            RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<Mensaje> response = restTemplate.postForEntity(AplicacionConst.REST_SERVICE_URI + "api/cerrar_sesion", usuario, Mensaje.class);
            return response.getBody();
        } catch (HttpStatusCodeException e) {
            logger.error("Error Cerrar sesion " + e.getResponseBodyAsString());
            return null;
        } catch (RestClientException e) {
            logger.error(e.getMessage());
            return null;
        }

    }

    @Override
    public Mensaje modificar_clave(Usuario usuario) {
        try {
            RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<Mensaje> response = restTemplate.postForEntity(AplicacionConst.REST_SERVICE_URI + "api/modificar_clave", usuario, Mensaje.class);
            return response.getBody();
        } catch (HttpStatusCodeException e) {
            logger.error("Error HTTP Status: " + e.getResponseBodyAsString());
            return null;
        } catch (RestClientException e) {
            logger.error("Error RestClien: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Mensaje obtener_grupo_perfiles(Usuario usuario) {
        try {
            RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
            ResponseEntity<Mensaje> response = restTemplate.postForEntity(URI.create(AplicacionConst.REST_SERVICE_URI + "api/obtener_grupo_perfiles"), usuario, Mensaje.class);
            return response.getBody();
        } catch (HttpStatusCodeException e) {
            logger.error("Error HTTP Status: " + e.getResponseBodyAsString());
            return null;
        } catch (RestClientException e) {
            logger.error("Error RestClien: " + e.getMessage());
            return null;
        }
    }

}
