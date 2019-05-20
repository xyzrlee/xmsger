package app.illl.xmsger.datasource.service;

import java.util.List;

public interface TwitterKeywordService {
    List<String> getKeywordByUsername(String username);
}
