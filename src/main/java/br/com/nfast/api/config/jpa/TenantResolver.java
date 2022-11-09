package br.com.nfast.api.config.jpa;

import br.com.nfast.api.web.TenantContext;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class TenantResolver implements CurrentTenantIdentifierResolver {

    @Override
    public String resolveCurrentTenantIdentifier() {
        return TenantContext.get();
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }

}
