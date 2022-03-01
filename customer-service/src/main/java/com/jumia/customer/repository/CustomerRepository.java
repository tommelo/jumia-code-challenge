package com.jumia.customer.repository;

import com.jumia.customer.entity.CustomerEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer>, JpaSpecificationExecutor<CustomerEntity> {

    /**
     * Pattern applied when searching for phone number
     * containing a specific dial code.
     * E.g.:
     * <pre>{@code}
     * SELECT phone FROM customer WHERE phone LIKE '(237)%'
     * </pre>
     */
    static String PHONE_LIKE_PATTERN = "(%s)%%";

    /**
     * Creates a specification to search phone numbers containing the given
     * set of dial codes.
     *
     * E.g.:
     *
     * The given set of dial codes: {@code ["237", "251"]} will produce a specification
     * to select entries like:
     * <pre>{@code
     * SELECT * FROM customer WHERE phone LIKE '(237)%' OR phone LIKE '(251)%'
     * }</pre>
     *
     * @param dialCodes The set of dial codes
     * @return spec The search specification
     */
    static Specification<CustomerEntity> phoneNumberContainingDialCode(Set<String> dialCodes) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (String code : dialCodes) {
                String value = String.format(PHONE_LIKE_PATTERN, code);
                Path<String> path = root.get(CustomerEntity.PHONE_COLUMN_NAME);
                Predicate predicate = builder.like(path, value);
                predicates.add(predicate);
            }
            return predicates.isEmpty() ? builder.and() : builder.or(predicates.toArray(new Predicate[0]));
        };
    }

}