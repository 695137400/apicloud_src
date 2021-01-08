//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b.c1;

import com.uzmap.pkg.a.b.c;
import com.uzmap.pkg.a.b.j;
import com.uzmap.pkg.a.b.p;
import com.uzmap.pkg.a.b.d1.e;
import com.uzmap.pkg.a.b.d1.h;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

public class g implements f {
    private final g.a a;
    private final SSLSocketFactory b;
    private final c c;

    public g(c cookies) {
        this((g.a)null, cookies);
    }

    public g(g.a urlRewriter, c cookies) {
        this(urlRewriter, cookies, (SSLSocketFactory)null);
    }

    public g(g.a urlRewriter, c cookies, SSLSocketFactory sslSocketFactory) {
        this.a = urlRewriter;
        this.b = sslSocketFactory;
        this.c = cookies;
    }

    public e a(j<?> request, Map<String, String> additionalHeaders) throws IOException, com.uzmap.pkg.a.b.a1.a {
        request.onPreExecute();
        String url = request.getUrl();
        HashMap<String, String> map = new HashMap();
        map.putAll(request.getHeaders());
        map.putAll(additionalHeaders);
        if (this.a != null) {
            String rewritten = this.a.rewriteUrl(url);
            if (rewritten == null) {
                throw new IOException("URL blocked by rewriter: " + url);
            }

            url = rewritten;
        }

        p.a("HurlStack PerformRequest ----------- " + request.getMethod() + "," + url);
        URL parsedUrl = new URL(url);
        HttpURLConnection connection = this.a(parsedUrl, request);
        Iterator var8 = map.keySet().iterator();

        String cookie;
        while(var8.hasNext()) {
            cookie = (String)var8.next();
            connection.addRequestProperty(cookie, (String)map.get(cookie));
        }

        cookie = this.a(url);
        if (cookie != null) {
            connection.addRequestProperty("Cookie", cookie);
        }

        request.setConnection(connection);
        a(connection, request);
        int responseCode = connection.getResponseCode();
        if (responseCode == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        } else {
            e response = new e(connection.getResponseCode(), connection.getResponseMessage());
            response.a(a(connection));
            Iterator var11 = connection.getHeaderFields().entrySet().iterator();

            while(var11.hasNext()) {
                Entry<String, List<String>> header = (Entry)var11.next();
                if (header.getKey() != null) {
                    response.a((String)header.getKey(), (String)((List)header.getValue()).get(0));
                }
            }

            this.a(request.getUrl(), response.a());
            return response;
        }
    }

    private static com.uzmap.pkg.a.b.d.a.a a(HttpURLConnection connection) {
        h entity = new h();

        InputStream inputStream;
        try {
            inputStream = connection.getInputStream();
        } catch (IOException var4) {
            inputStream = connection.getErrorStream();
        }

        entity.a(inputStream);
        entity.a(connection.getContentLength());
        entity.b(connection.getContentEncoding());
        entity.a(connection.getContentType());
        return entity;
    }

    protected HttpURLConnection a(URL url) throws IOException {
        return (HttpURLConnection)url.openConnection();
    }

    private HttpURLConnection a(URL url, j<?> request) throws IOException {
        HttpURLConnection connection = this.a(url);
        int timeoutMs = request.getTimeoutMs();
        connection.setConnectTimeout(timeoutMs);
        connection.setReadTimeout(timeoutMs);
        connection.setUseCaches(false);
        connection.setDoInput(true);
        if ("https".equals(url.getProtocol())) {
            SSLSocketFactory sslSocketFactory = request.getSslSocketFactory();
            if (sslSocketFactory == null) {
                sslSocketFactory = this.b;
            }

            if (sslSocketFactory != null) {
                ((HttpsURLConnection)connection).setSSLSocketFactory(sslSocketFactory);
            }

            ((HttpsURLConnection)connection).setHostnameVerifier(com.uzmap.pkg.a.b.b1.a.a);
        }

        return connection;
    }

    private String a(String url) {
        return this.c != null ? this.c.a(url) : null;
    }

    private void a(String url, Map<String, String> headers) {
        if (this.c != null) {
            this.c.a(url, headers);
        }

    }

    static void a(HttpURLConnection connection, j<?> request) throws IOException, com.uzmap.pkg.a.b.a1.a {
        switch(request.getMethod()) {
            case -1:
                byte[] postBody = request.getPostBody();
                if (postBody != null) {
                    connection.setDoOutput(true);
                    connection.setRequestMethod("POST");
                    connection.addRequestProperty("Content-Type", request.getPostBodyContentType());
                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                    out.write(postBody);
                    out.close();
                }
                break;
            case 0:
                connection.setRequestMethod("GET");
                break;
            case 1:
                connection.setRequestMethod("POST");
                b(connection, request);
                break;
            case 2:
                connection.setRequestMethod("PUT");
                b(connection, request);
                break;
            case 3:
                connection.setRequestMethod("DELETE");
                break;
            case 4:
                connection.setRequestMethod("HEAD");
                break;
            case 5:
                connection.setRequestMethod("OPTIONS");
                break;
            case 6:
                connection.setRequestMethod("TRACE");
                break;
            case 7:
                connection.setRequestMethod("PATCH");
                b(connection, request);
                break;
            default:
                throw new IllegalStateException("Unknown method type.");
        }

    }

    private static void b(HttpURLConnection connection, j<?> request) throws IOException, com.uzmap.pkg.a.b.a1.a {
        if (!request.isEmpty()) {
            byte[] body = request.getBody();
            String contentType;
            DataOutputStream out;
            if (body != null) {
                connection.setDoOutput(true);
                contentType = request.getBodyContentType();
                connection.addRequestProperty("Content-Type", contentType);
                out = new DataOutputStream(connection.getOutputStream());
                out.write(body);
                out.close();
            } else {
                connection.setDoOutput(true);
                connection.setChunkedStreamingMode(32768);
                contentType = request.getBodyContentType();
                connection.addRequestProperty("Content-Type", contentType);
                out = new DataOutputStream(connection.getOutputStream());
                request.writeTo(out);
                out.close();
            }

        }
    }

    public interface a {
        String rewriteUrl(String var1);
    }
}
