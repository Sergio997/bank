package com.bank.mapper;

import com.bank.dto.response.CardTransactionResponse;
import com.bank.dto.response.PageResponse;
import com.bank.model.CardTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardTransactionMapper {

    CardTransactionResponse toDtoResponse(CardTransaction entity);

    default PageResponse<CardTransactionResponse> toDtoPageResponse(Page<CardTransaction> page) {
        return new PageResponse<>(page.getTotalPages(),
                page.getTotalElements(),
                page.get()
                        .map(this::toDtoResponse)
                        .collect(Collectors.toList()));
    }
}
