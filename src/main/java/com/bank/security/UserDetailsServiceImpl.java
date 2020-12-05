package com.bank.security;

import com.bank.model.Card;
import com.bank.repo.CardRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("UserDetailsServerImpl")
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CardRepo cardRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Card card = cardRepo.findByNumberCard(email)
                .orElseThrow(() -> new UsernameNotFoundException("Card not found"));
        return SecurityUser.fromUser(card);
    }

}
