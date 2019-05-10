package com.recycling.DB.repository;
import com.recycling.recycling.production.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountsRepository extends JpaRepository<UserAccount, Integer> {
}

