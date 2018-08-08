/*
 * Copyright (c) 2018. Gavin Kenna
 */

package com.gkenna.tullamoreqa.it;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.boot.test.autoconfigure.orm.jpa.*;

@Configuration
@ComponentScan({"com.gkenna.tullamoreqa.*"})
//@EnableTransactionManagement
//@EnableJpaRepositories("com.gkenna.tullamoreqa.core.api.repositories")
//@DataJpaTest
public class AppConfiguration {
}
