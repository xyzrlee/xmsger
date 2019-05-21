package app.illl.xmsger.datasource.service;

import app.illl.xmsger.datasource.entity.Token;
import app.illl.xmsger.datasource.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    public Iterable<Token> findAll() {
        return tokenRepository.findAll();
    }

}
