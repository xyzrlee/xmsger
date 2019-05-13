package app.illl.xmsger.datasource.service;

import app.illl.xmsger.datasource.entity.Token;
import app.illl.xmsger.datasource.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public List<Token> findAll() {
        return (List<Token>) tokenRepository.findAll();
    }
}
