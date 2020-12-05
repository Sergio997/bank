package com.bank.repo;

import com.bank.model.CardTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CardTransactionRepo extends JpaRepository<CardTransaction, Long> {
    Page<CardTransaction> findAllByCard_Id(Long cardId, Pageable pageable);
}
