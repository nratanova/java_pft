package ru.pft.mantis.appmanager;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by Natalya on 24.03.2017.
 */
public class HttpSession {
    private CloseableHttpClient httpclient;
    private ApplicationManager app;

    public HttpSession (ApplicationManager app) {
        this.app = app;
        //Создается новая сессия по протоколу http
        httpclient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
        //setRedirectStrategy(new LaxRedirectStrategy() - автоматическая обработка перенаправлений
    }

    //Метод, выполняющий логин
    public boolean login (String username, String password) throws IOException {
        //Создается пустой запрос
        HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/login.php");
        //Формирование параметров запроса
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("secure_session", "on"));
        params.add(new BasicNameValuePair("return", "index.php"));
        //Упаковка параметров в заранее созданный запрос
        post.setEntity(new UrlEncodedFormEntity(params));
        //Отправка запроса
        CloseableHttpResponse response = httpclient.execute(post);
        //Получение текста ответа (response)
        String body = getTextForm(response);
        //Проверка, что пользователь зашел и код страницы содержит строчку с именеме пользователя
        return body.contains(String.format("<a href=\"/mantisbt-2.2.1/account_page.php\">%s</a>", username));
    }

    private String getTextForm(CloseableHttpResponse response) throws IOException {
        try {
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();
        }
    }

    //Метод, определяющий какой пользователь вошел в систему
    public boolean isLoggedInAs(String username) throws IOException {
        //Запрос к главной странице
        HttpGet get = new HttpGet(app.getProperty("web.baseUrl") + "/index.php");
        //Выполнение запроса и получение ответа (response)
        CloseableHttpResponse response = httpclient.execute(get);
        //Получение текста ответа
        String body = getTextForm(response);
        //Проверка что в тескте стр есть строчка с именем пользователя
        return body.contains(String.format("<a href=\"/mantisbt-2.2.1/account_page.php\">%s</a>", username));
    }

}
