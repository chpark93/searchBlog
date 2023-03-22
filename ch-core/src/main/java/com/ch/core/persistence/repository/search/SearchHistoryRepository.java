package com.ch.core.persistence.repository.search;

import com.ch.core.persistence.domain.search.SearchHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchHistoryRepository extends JpaRepository<SearchHistoryEntity, Long> {

}
