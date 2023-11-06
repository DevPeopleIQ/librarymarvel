package com.marvel.developer;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ConectorMarvelAPI {

	@Autowired
	private WebClient webClient;

	/**
	 * @param urlBase Dominio del servicio
	 * @param path Ruta del servicio a consumir
	 * @param publicKey Llave publica
	 * @param privateKey Llave privada
	 * @param ts timeStamp en long
	 * @return String Json de respuesta
	 */
	public String conect(String urlBase, String path, String publicKey, String privateKey, Long ts) {		
		String hash = getMD5(ts+privateKey+publicKey);

		UriComponents uri = UriComponentsBuilder.
				fromHttpUrl(urlBase)
				.path(path)
				.queryParam("ts", ts)
				.queryParam("apikey", publicKey)
				.queryParam("hash", hash)
				.build();

		return webClient.get()
				.uri(uri.toString())
				.retrieve()
				.bodyToMono(String.class)
				.block();
	}

	/**
	 * @param input Cadena a cifrar
	 * @return String Cadena cifrada en MD5
	 */
	public static String getMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);

			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
