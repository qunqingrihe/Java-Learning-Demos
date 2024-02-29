package CaffeineDemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class StudentService {
    @Cacheable(value = "cache1")
    public String getNameById(int id) {
        log.info("从DB获取数据：id=" + id);
        return new Date().toString();
    }
}

