//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.uzmap.pkg.a.b.b1;

import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class d implements X509TrustManager {
    private X509TrustManager a;

    public d(KeyStore ksP12) throws Exception {
        TrustManagerFactory trustfactory = TrustManagerFactory.getInstance("X509");
        trustfactory.init(ksP12);
        TrustManager[] trustMgrs = trustfactory.getTrustManagers();
        if (trustMgrs.length == 0) {
            throw new NoSuchAlgorithmException("no trust manager found");
        } else {
            this.a = (X509TrustManager)trustMgrs[0];
        }
    }

    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        this.a.checkClientTrusted(chain, authType);
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) {
        try {
            if (chain != null && chain.length == 1) {
                chain[0].checkValidity();
            } else {
                this.a.checkServerTrusted(chain, authType);
            }
        } catch (Exception var4) {
        }

    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
