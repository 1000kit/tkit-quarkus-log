package org.tkit.quarkus.log.cdi.context;

import java.io.Serializable;

/**
 * @author msomora
 */
public class RequestData implements Serializable {
    
    private String principal;
    
    public String getPrincipal() {
        return principal;
    }
    
    public void setPrincipal(String principal) {
        this.principal = principal;
    }
}
