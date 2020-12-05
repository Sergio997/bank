package com.bank.mapper;

import com.bank.dto.request.CardRequest;
import com.bank.dto.response.AuthenticationResponse;
import com.bank.dto.response.CardResponse;
import com.bank.model.Card;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CardMapper {

    Card requestToEntity(CardRequest request);

    Card requestToEntity(CardRequest request, @MappingTarget Card card);

    CardResponse toDtoResponse(Card request);

    AuthenticationResponse toDtoAuthenticationResponse(Card request);

}
