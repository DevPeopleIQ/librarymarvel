package com.marvel.developer;

import java.time.Instant;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DeveloperApplicationTests {
	
	@Autowired
	private ConectorMarvelAPI conectorMarvelAPI;

	@Test
	void contextLoads() {
		String urlBase = "http://gateway.marvel.com";
		String pathCharacter = "/v1/public/characters";
		String publicKey = "1749d73b612fcbaf299a5a0d2688fa42";
		String privateKey = "4a966eecc2191d5a2c34f08406f2cb36c7e8cebb";
		Long ts = new Date().getTime();
		
		String a = conectorMarvelAPI.conect(urlBase, pathCharacter, publicKey, privateKey, ts);
		
		try {
			JSONObject jo = new JSONObject(a);
			
			Assertions.assertEquals(200L, jo.optLong("code"));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
