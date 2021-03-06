package com.uzmap.pkg.ui.b.cc;

import java.util.Map;

public class e {
    public static com.uzmap.pkg.ui.b.a.a1 a(com.uzmap.pkg.ui.b.i response) {
        long now = System.currentTimeMillis();
        Map<String, String> headers = response.c;
        long serverDate = 0L;
        long lastModified = 0L;
        long serverExpires = 0L;
        long softExpire = 0L;
        long finalExpire = 0L;
        long maxAge = 0L;
        long staleWhileRevalidate = 0L;
        boolean hasCacheControl = false;
        boolean mustRevalidate = false;
        String serverEtag = null;
        String headerValue = headers.get("Date");
        if (headerValue != null) {
            serverDate = a(headerValue);
        }

        headerValue = headers.get("Cache-Control");
        if (headerValue != null) {
            hasCacheControl = true;
            String[] tokens = headerValue.split(",");

            for (int i = 0; i < tokens.length; ++i) {
                String token = tokens[i].trim();
                if (token.equalsIgnoreCase("no-cache") || token.equalsIgnoreCase("no-store")) {
                    return null;
                }

                if (token.startsWith("max-age=")) {
                    try {
                        maxAge = Long.parseLong(token.substring(8));
                    } catch (Exception var27) {
                    }
                } else if (token.startsWith("stale-while-revalidate=")) {
                    try {
                        staleWhileRevalidate = Long.parseLong(token.substring(23));
                    } catch (Exception var26) {
                    }
                } else if (token.equalsIgnoreCase("must-revalidate") || token.equalsIgnoreCase("proxy-revalidate")) {
                    mustRevalidate = true;
                }
            }
        }

        headerValue = headers.get("Expires");
        if (headerValue != null) {
            serverExpires = a(headerValue);
        }

        headerValue = headers.get("Last-Modified");
        if (headerValue != null) {
            lastModified = a(headerValue);
        }

        serverEtag = headers.get("ETag");
        if (hasCacheControl) {
            softExpire = now + maxAge * 1000L;
            finalExpire = mustRevalidate ? softExpire : softExpire + staleWhileRevalidate * 1000L;
        } else if (serverDate > 0L && serverExpires >= serverDate) {
            softExpire = now + (serverExpires - serverDate);
            finalExpire = softExpire;
        }

        com.uzmap.pkg.ui.b.a.a1 entry = new com.uzmap.pkg.ui.b.a.a1();
        entry.a = response.b;
        entry.b = serverEtag;
        entry.f = softExpire;
        entry.e = finalExpire;
        entry.c = serverDate;
        entry.d = lastModified;
        entry.g = headers;
        return entry;
    }

    public static long a(String dateStr) {
        try {
            return com.uzmap.pkg.ui.b.e.b(dateStr).getTime();
        } catch (Exception var2) {
            return 0L;
        }
    }

    public static String a(Map<String, String> headers, String defaultCharset) {
        String contentType = headers.get("Content-Type");
        if (contentType != null) {
            String[] params = contentType.split(";");

            for (int i = 1; i < params.length; ++i) {
                String[] pair = params[i].trim().split("=");
                if (pair.length == 2 && pair[0].equalsIgnoreCase("charset")) {
                    return pair[1];
                }
            }
        }

        return defaultCharset;
    }

    public static String a(Map<String, String> headers) {
        return a(headers, "UTF-8");
    }
}
