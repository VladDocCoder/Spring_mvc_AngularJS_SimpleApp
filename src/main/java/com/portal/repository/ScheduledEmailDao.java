package com.portal.repository;

import com.portal.model.ScheduledEmail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface ScheduledEmailDao extends CrudRepository<ScheduledEmail, Long> {
    @Query("SELECT se FROM ScheduledEmail AS se WHERE se.sent = 'false'")
    Iterable<ScheduledEmail> findNotSent();
}
