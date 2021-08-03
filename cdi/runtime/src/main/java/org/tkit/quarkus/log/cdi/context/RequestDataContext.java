package org.tkit.quarkus.log.cdi.context;

/**
 * @author msomora
 */
public class RequestDataContext {
    
    private static ThreadLocal<RequestData> requestDataThreadLocal = new ThreadLocal<>();
    
    public static RequestData get() {
        if (requestDataThreadLocal != null) {
            return requestDataThreadLocal.get();
        }
    
        return null;
    }
    
    public static void set(RequestData data) {
        requestDataThreadLocal.set(data);
    }
    
    public static void clear() {
        if (get() != null) {
            requestDataThreadLocal.remove();
        }
    }
}
