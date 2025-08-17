package com.ed.std.engine.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component("AuditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

	/**
	 * Return the current Auditor of the application
	 * @return the current Auditor
	 */
	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of("ASIF_DEVELOPER");
	}

}
