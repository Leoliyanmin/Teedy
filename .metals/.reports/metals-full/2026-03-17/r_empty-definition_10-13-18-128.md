error id: file://<HOME>/Documents/SE%20Lab/Teedy/docs-web-common/src/main/java/com/sismics/security/AnonymousPrincipal.java:_empty_/IPrincipal#
file://<HOME>/Documents/SE%20Lab/Teedy/docs-web-common/src/main/java/com/sismics/security/AnonymousPrincipal.java
empty definition using pc, found symbol in pc: _empty_/IPrincipal#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 231
uri: file://<HOME>/Documents/SE%20Lab/Teedy/docs-web-common/src/main/java/com/sismics/security/AnonymousPrincipal.java
text:
```scala
package com.sismics.security;

import com.google.common.collect.Sets;
import org.joda.time.DateTimeZone;

import java.util.Set;

/**
 * Anonymous principal.
 * 
 * @author jtremeaux
 */
public class AnonymousPrincipal implements IP@@rincipal {
    public static final String ANONYMOUS = "anonymous";
    
    /**
     * User timezone.
     */
    private DateTimeZone dateTimeZone;
    
    /**
     * Constructor of AnonymousPrincipal.
     */
    public AnonymousPrincipal() {
        // NOP
    }
    
    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getName() {
        return ANONYMOUS;
    }

    @Override
    public boolean isAnonymous() {
        return true;
    }

    @Override
    public DateTimeZone getDateTimeZone() {
        return dateTimeZone;
    }

    @Override
    public String getEmail() {
        return null;
    }
    
    public void setDateTimeZone(DateTimeZone dateTimeZone) {
        this.dateTimeZone = dateTimeZone;
    }

    @Override
    public Set<String> getGroupIdSet() {
        return Sets.newHashSet();
    }

    @Override
    public boolean isGuest() {
        return false;
    }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/IPrincipal#