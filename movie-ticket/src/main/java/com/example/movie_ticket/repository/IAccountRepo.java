package com.example.movie_ticket.repository;

import com.example.movie_ticket.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IAccountRepo extends JpaRepository<Account,Long> {
    Account findByUsername(String username);

    @Query(nativeQuery = true, value = "SELECT * FROM `accounts` a join customer c on a.id = c.account_id where c.email = :email")
    Account findByEmail(@Param("email") String email);

    @Query(nativeQuery = true, value = "SELECT * FROM `accounts` a join customer c on a.id = c.account_id where c.phone_number = :phone")
    Account findByPhone(@Param("phone") String phone);
}
