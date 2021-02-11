package com.example.moduleA

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource


@Configuration
@ComponentScan(basePackages = ["com.example.moduleA"])
@PropertySource("classpath:moduleA-application.properties")
@EntityScan("com.example.moduleA")
@EnableJpaRepositories(basePackages = ["com.example.moduleA"],
        entityManagerFactoryRef = "moduleAEntityManager",
        transactionManagerRef = "moduleATransactionManager"
)
class ConfigurationModuleA internal constructor(val env: Environment) {

    @Bean(name = ["moduleADataSource"])
    @ConfigurationProperties(prefix = "module-a.spring.datasource")
    fun moduleADataSource(): DataSource = DataSourceBuilder.create().build()

    @Bean(name = ["moduleAEntityManager"])
    //@ConfigurationProperties(prefix = "module-a.spring.jpa")
    fun moduleAEntityManager(@Qualifier("moduleADataSource")
                             dataSource: DataSource): LocalContainerEntityManagerFactoryBean {
        val em = LocalContainerEntityManagerFactoryBean()
        em.dataSource = dataSource
        em.setPackagesToScan("com.example.moduleA")
        val vendorAdapter = HibernateJpaVendorAdapter()
        em.jpaVendorAdapter = vendorAdapter
        val properties = hashMapOf(
                "hibernate.dialect" to env.getProperty("module-a.spring.jpa.properties.hibernate.dialect"),
                "hibernate.default_schema" to env.getProperty("module-a.spring.jpa.properties.hibernate.default_schema"))
        em.setJpaPropertyMap(properties)
        return em
    }

    @Bean(name = ["moduleATransactionManager"])
    fun moduleATransactionManager(@Qualifier("moduleAEntityManager")
                                  entityManagerFactory: EntityManagerFactory): PlatformTransactionManager =
            JpaTransactionManager(entityManagerFactory)
}