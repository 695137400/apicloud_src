package com.uzmap.pkg.ui.b.dd;

import com.uzmap.pkg.ui.b.o;
import com.uzmap.pkg.ui.b.p;

public class g extends com.uzmap.pkg.ui.b.d {
    private int a;

    public void a(o error) throws o {
        if (error != null && error.a != null) {
            int statusCode = error.a.a;
            if (statusCode == 301 || statusCode == 302) {
                p.a("onRedirectResponse", error);
                return;
            }
        }

        super.a(error);
    }

    public void a(int time) {
        this.a = time * 1000;
    }

    public int a() {
        return this.a > 0 ? this.a : 30000;
    }
}
