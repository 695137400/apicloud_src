package com.uzmap.pkg.uzcore;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import com.uzmap.pkg.uzapp.UZFileSystem;
import com.uzmap.pkg.uzcore.external.Enslecb;
import com.uzmap.pkg.uzcore.uzmodule.AppInfo;
import com.uzmap.pkg.uzcore.uzmodule.ApplicationDelegate;
import com.uzmap.pkg.uzsocket.api.UPnsListener;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class b {
    public static boolean a = true;
    private static com.uzmap.pkg.uzcore.b i;
    private com.uzmap.pkg.uzcore.uzmodule.c b;
    private com.uzmap.pkg.uzcore.aa.h c;
    private Application d;
    private List<ApplicationDelegate> e;
    private UZPlatformBridge f;
    private com.uzmap.pkg.uzkit.a.e g;
    private final boolean h;
    private com.uzmap.pkg.uzcore.uzmodule.e j;
    private com.uzmap.pkg.uzcore.uzmodule.e k;

    private b(boolean isFromMainProcess) {
        this.h = isFromMainProcess;
    }

    public static com.uzmap.pkg.uzcore.b a() {
        if (i == null) {
            throw new IllegalAccessError("Application do not createInstance");
        } else {
            return i;
        }
    }

    public static com.uzmap.pkg.uzcore.b a(boolean isFromMainProcess) {
        if (i == null) {
            i = new com.uzmap.pkg.uzcore.b(isFromMainProcess);
        }

        return i;
    }

    public void a(Application context) {
        if (context != this.d) {
            this.d = context;
            a = Enslecb.xsm(context);
            this.t();
            if (this.h) {
                this.u();
                this.a((Context) this.d);
            }

        }
    }

    public Context b() {
        return this.d;
    }

    private void t() {
        com.uzmap.pkg.uzapp.f.a();
        com.uzmap.pkg.uzcore.d.a(this.d);
        UZFileSystem.initialize(this.d);
        UZResourcesIDFinder.init(this.d);
        if (this.h) {
            this.f = UZPlatformBridge.a(this.d);
            this.f.a();
            this.g = com.uzmap.pkg.uzkit.a.e.a(this.d);
        }

    }

    public final void a(bcl callback) {
        if (this.j != null && this.j.z != null) {
            callback.a(true, this.j, null);
        } else {
            (new com.uzmap.pkg.uzcore.b.a(callback)).start();
        }

    }

    private com.uzmap.pkg.uzcore.uzmodule.e u() {
        com.uzmap.pkg.uzcore.uzmodule.e assetEntity = null;
        boolean s = n.b();
        assetEntity = com.uzmap.pkg.uzkit.data.d.a(s);
        this.k = assetEntity;
        this.b(assetEntity != null && assetEntity.O);
        if (com.uzmap.pkg.uzapp.b.o()) {
            com.uzmap.pkg.uzcore.uzmodule.e ls = com.uzmap.pkg.uzkit.data.d.a(assetEntity.r, s);
            if (ls != null) {
                String id = assetEntity.r;
                ls.r = id;
            }

            this.j = ls;
            return ls;
        } else {
            if (!UZCoreUtil.a && assetEntity != null && assetEntity.N) {
                String id = assetEntity.r;
                boolean fromSandbox = UZFileSystem.get().synchronizedAsset();
                if (fromSandbox) {
                    com.uzmap.pkg.uzcore.uzmodule.e ls = com.uzmap.pkg.uzkit.data.d.b(id, s);
                    if (ls != null) {
                        ls.Q = assetEntity.Q = s;
                        assetEntity = ls;
                        ls.r = id;
                    }
                }
            }

            this.j = assetEntity;
            return assetEntity;
        }
    }

    private final void b(boolean su) {
        String p;
        InputStream i;
        try {
            p = com.uzmap.pkg.uzcore.aa.b.c();
            i = this.d.getAssets().open(p);
            String c = UZCoreUtil.readString(i);
            i.close();
            this.b = new com.uzmap.pkg.uzcore.uzmodule.c(c);
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        byte[] c;
        try {
            this.c = new com.uzmap.pkg.uzcore.aa.h();
            p = com.uzmap.pkg.uzcore.aa.b.b();
            i = this.d.getAssets().open(p);
            c = UZCoreUtil.readByte(i);
            i.close();
            this.c.a(c);
        } catch (Exception var7) {
        }

        if (su) {
            try {
                p = com.uzmap.pkg.uzcore.aa.b.d();
                i = this.d.getAssets().open(p);
                c = UZCoreUtil.readByte(i);
                i.close();
                com.uzmap.pkg.uzcore.aa.a.a(c);
            } catch (Exception var6) {
            }
        }

        if (com.uzmap.pkg.uzapp.b.b()) {
            try {
                p = com.uzmap.pkg.uzcore.aa.b.e();
                i = this.d.getAssets().open(p);
                c = UZCoreUtil.readByte(i);
                i.close();
                com.uzmap.pkg.uzcore.aa.a.b(c);
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }

    }

    public final void a(com.uzmap.pkg.uzkit.a.f listener) {
        this.g.a(listener);
    }

    public final void a(Activity activity) {
        this.g.a(activity);
    }

    public final void a(com.uzmap.pkg.uzcore.uzmodule.e wgtInfo) {
        this.g.c(wgtInfo);
    }

    public final void b(com.uzmap.pkg.uzcore.uzmodule.e wgtInfo) {
        this.g.d(wgtInfo);
    }

    public final void a(UZPlatformBridge.a listener) {
        this.f.a(listener);
    }

    public final void a(UPnsListener listener) {
        this.f.a(listener);
    }

    public final void b(UPnsListener listener) {
        this.f.b(listener);
    }

    public final String c() {
        return this.f.c();
    }

    public final String d() {
        return this.f.d();
    }

    public final com.uzmap.pkg.uzcore.uzmodule.c e() {
        if (this.b == null) {
            this.b(false);
        }

        return this.b;
    }

    public com.uzmap.pkg.uzcore.uzmodule.e f() {
        return this.k;
    }

    public final com.uzmap.pkg.uzcore.aa.h g() {
        return this.c;
    }

    public boolean h() {
        return "sdk".equals(com.uzmap.pkg.uzapp.b.r()) || com.uzmap.pkg.uzcore.uzmodule.c.c();
    }

    public boolean i() {
        return com.uzmap.pkg.uzcore.uzmodule.c.d();
    }

    public boolean j() {
        return this.i() && com.uzmap.pkg.uzapp.b.m();
    }

    public boolean k() {
        return com.uzmap.pkg.uzcore.uzmodule.c.e();
    }

    public boolean l() {
        return com.uzmap.pkg.uzcore.uzmodule.c.f();
    }

    public boolean m() {
        return com.uzmap.pkg.uzcore.uzmodule.c.g();
    }

    public boolean n() {
        if (this.i()) {
            return false;
        } else {
            return !this.k() || !com.uzmap.pkg.uzkit.data.a.a().b();
        }
    }

    public boolean o() {
        return com.uzmap.pkg.uzcore.d.a().c();
    }

    public boolean p() {
        if (com.uzmap.pkg.uzapp.b.a()) {
            return false;
        } else if (com.uzmap.pkg.uzapp.b.o()) {
            return this.j != null && this.j.S;
        } else {
            return com.uzmap.pkg.uzcore.d.a().c();
        }
    }

    public final void q() {
        if (this.f != null) {
            this.f.b();
            this.f = null;
        }

        if (this.g != null) {
            this.g.a();
        }

        com.uzmap.pkg.uzcore.c.a();
        com.uzmap.pkg.uzapp.e.a().c();
    }

    public final void r() {
        if (this.g != null) {
            this.g.b(this.j);
        }

    }

    public final void s() {
        if (this.g != null) {
            this.g.a(this.j);
        }

    }

    public final void a(double lat, double log, String widgetId) {
        if (this.g != null) {
            this.g.a(lat, log, widgetId);
        }

    }

    public void a(Context context) {
        this.v();
        AppInfo info = this.j != null ? this.j.h() : null;
        Iterator var4 = this.e.iterator();

        while (var4.hasNext()) {
            ApplicationDelegate delegate = (ApplicationDelegate) var4.next();
            delegate.onApplicationCreate(context);
            delegate.onApplicationCreate(context, info);
        }

    }

    public void b(Activity activity) {
        this.v();
        AppInfo info = this.j != null ? this.j.h() : null;
        Iterator var4 = this.e.iterator();

        while (var4.hasNext()) {
            ApplicationDelegate delegate = (ApplicationDelegate) var4.next();
            delegate.onActivityResume(activity);
            delegate.onActivityResume(activity, info);
        }

    }

    public void c(Activity activity) {
        this.v();
        AppInfo info = this.j != null ? this.j.h() : null;
        Iterator var4 = this.e.iterator();

        while (var4.hasNext()) {
            ApplicationDelegate delegate = (ApplicationDelegate) var4.next();
            delegate.onActivityPause(activity);
            delegate.onActivityPause(activity, info);
        }

    }

    public void d(Activity activity) {
        this.v();
        AppInfo info = this.j != null ? this.j.h() : null;
        Iterator var4 = this.e.iterator();

        while (var4.hasNext()) {
            ApplicationDelegate delegate = (ApplicationDelegate) var4.next();
            delegate.onActivityFinish(activity);
            delegate.onActivityFinish(activity, info);
        }

    }

    private void v() {
        if (this.e == null) {
            this.e = this.e().b();
        }

    }

    public abstract static class bcl {
        private String a;

        public void a(String startUrl) {
            this.a = startUrl;
        }

        public String a() {
            return this.a;
        }

        abstract void a(boolean var1, com.uzmap.pkg.uzcore.uzmodule.e var2, String var3);
    }

    private class a extends Thread {
        final bcl a;

        public a(bcl callback) {
            super("doParser");
            this.a = callback;
        }

        public void run() {
            this.a();
        }

        private void a() {
            boolean success = false;
            com.uzmap.pkg.uzcore.uzmodule.e entity = null;
            String msg = null;
            com.uzmap.pkg.uzcore.uzmodule.e as = u();
            if (as != null && as.z != null) {
                success = true;
                entity = as;
            } else if (k != null && com.uzmap.pkg.uzapp.b.o()) {
                msg = "调试路径下未找到id为：\n" + k.r + "\n的项目\n" + "请确认本项目config文件中id是否与服务器端一致";
            } else if (!com.uzmap.pkg.uzcore.b.a) {
                msg = "应用签名被篡改";
            } else {
                msg = "无法解析config文件";
            }

            if (this.a != null) {
                this.a.a(success, entity, msg);
            }

        }
    }
}