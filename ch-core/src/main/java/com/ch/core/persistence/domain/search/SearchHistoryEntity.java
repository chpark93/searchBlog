package com.ch.core.persistence.domain.search;

import com.ch.core.persistence.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Objects;

/**
 * TABLE search_history
 */
@Getter
@ToString
@Entity
@NoArgsConstructor
@Table(name="search_history", indexes = @Index(
        name = "idx_keyword", columnList = "keyword"
))
public class SearchHistoryEntity extends BaseTimeEntity {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "search_type", nullable = false)
    private String searchType;

    @Column(name = "keyword", nullable = false)
    private String keyword;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SearchHistoryEntity searchHistory = (SearchHistoryEntity) o;
        return id != null && Objects.equals(id, searchHistory.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Builder
    public SearchHistoryEntity(String searchType, String keyword) {
        this.searchType = searchType;
        this.keyword = keyword;
    }
}