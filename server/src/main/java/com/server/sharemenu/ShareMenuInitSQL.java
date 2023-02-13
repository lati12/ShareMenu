package com.server.sharemenu;

import com.server.sharemenu.common.User;
import com.server.sharemenu.repositories.UserRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.DriverManager;
import java.util.Optional;

@Service
public class ShareMenuInitSQL implements CommandLineRunner {

    private final DataSource datasource;

    private final UserRepository userRepository;

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.username}")
    private String dataSourceUser;

    @Value("${spring.datasource.password}")
    private String dataSourcePassword;

    @Value("${spring.sql.init.schema-locations}")
    private String sqlFile;

    public ShareMenuInitSQL(DataSource datasource, UserRepository userRepository) {
        this.datasource = datasource;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Optional<User> user = userRepository.findById(1L);
        if(!user.isPresent())
            ScriptUtils.executeSqlScript(datasource.getConnection(), new ClassPathResource("/sql/initdata.sql"));
    }
}
