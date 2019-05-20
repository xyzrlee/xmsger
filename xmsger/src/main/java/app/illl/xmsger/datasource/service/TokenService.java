package app.illl.xmsger.datasource.service;

import app.illl.xmsger.datasource.entity.Token;

public interface TokenService {
    Iterable<Token> findAll();
}
