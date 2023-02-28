package org.example;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Main {

    public static void main(String[] args) {

        String url = "http://94.198.50.185:7081/api/users";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<String> response = restTemplate.getForEntity("http://94.198.50.185:7081/api/users", String.class);
        headers.add("Cookie", response.getHeaders().get("Set-Cookie").get(0));
        System.out.println(response);

//        Получение всех пользователей - …/api/users ( GET )
//        Добавление пользователя - …/api/users ( POST )
//        Изменение пользователя - …/api/users ( PUT )
//        Удаление пользователя - …/api/users /{id} ( DELETE )

        // Сохранять юзера
        User user = new User(3L, "James", "Brown", (byte) 3);
        HttpEntity<User> userHttp = new HttpEntity<>(user, headers);
        response = restTemplate.exchange(url, HttpMethod.POST, userHttp, String.class);
        System.out.println(response);

        // Изменить юзера
        user = new User(3L, "Thomas", "Shelby", (byte) 3);
        userHttp = new HttpEntity<>(user, headers);
        response = restTemplate.exchange(url, HttpMethod.PUT, userHttp, String.class);
        System.out.println(response);

        // Изменить юзера
        url = url + "/3";
        userHttp = new HttpEntity<>(headers);
        response = restTemplate.exchange(url, HttpMethod.DELETE, userHttp, String.class);
        System.out.println(response);
    }
}