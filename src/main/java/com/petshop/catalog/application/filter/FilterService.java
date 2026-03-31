package com.petshop.catalog.application.filter;

import com.petshop.catalog.domain.filter.Filter;
import com.petshop.catalog.domain.filter.FilterReadRepository;
import com.petshop.catalog.domain.filter.FilterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FilterService {
    private final FilterReadRepository filterReadRepository;
    private final FilterRepository filterRepository;

    public FilterService(
            FilterReadRepository filterReadRepository,
            FilterRepository filterRepository
    ) {
        this.filterReadRepository = filterReadRepository;
        this.filterRepository = filterRepository;
    }

    public List<FilterView> viewAll() {
        return this.filterReadRepository.viewAll();
    }

    public FilterView create(UUID parentId, UUID objectId) {
        final Filter filter = Filter.create(parentId, objectId);

        this.filterRepository.save(filter);

        return FilterMapper.toView(filter);
    }

    public void delete(UUID id) {
        if (!this.filterRepository.existsById(id)) {
            throw new IllegalArgumentException("Filtro no encontrado");
        }

        this.filterRepository.deleteById(id);
    }
}
