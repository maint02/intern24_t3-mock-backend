package com.itsol.train.mock.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import java.io.*;

@Slf4j
public class SQLBuilder {
    public static final String SQL_MODULE_EMPLOYEE = "employee";
    public static final String SQL_MODULE_DEMO = "demo";

    public static String readSqlFromFile(String module, String sqlFileName) {
        ClassPathResource classPathResource = new ClassPathResource("sql" + File.separator + module + File.separator + sqlFileName + ".sql");
        StringBuilder sqlQuery = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(classPathResource.getFile()))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                sqlQuery.append(line + "\n");
            }
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return sqlQuery.toString();
    }

}
