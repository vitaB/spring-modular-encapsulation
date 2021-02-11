package com.example.moduleB

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource


@Configuration
@ComponentScan(basePackages = ["com.example.moduleB"])
@PropertySource("classpath:moduleB-application.properties")
@EntityScan("com.example.moduleB")
@EnableJpaRepositories(basePackages = ["com.example.moduleB"],
        entityManagerFactoryRef = "moduleBEntityManager",
        transactionManagerRef = "moduleBTransactionManager"
)
class ConfigurationModuleB {

    @Bean(name = ["moduleBDataSource"])
    @ConfigurationProperties(prefix = "module-b.spring.datasource")
    fun moduleBDataSource(): DataSource = DataSourceBuilder.create().build()

    @Bean(name = ["moduleBEntityManager"])
    fun moduleBEntityManager(@Qualifier("moduleBJPAProperties")
                             properties: JpaProperties,
                             @Qualifier("moduleBDataSource")
                             dataSource: DataSource): LocalContainerEntityManagerFactoryBean {
        val em = LocalContainerEntityManagerFactoryBean()
        em.dataSource = dataSource
        em.setPackagesToScan("com.example.moduleB")
        val vendorAdapter = HibernateJpaVendorAdapter()
        em.jpaVendorAdapter = vendorAdapter
        em.setJpaPropertyMap(properties.properties)
        return em
    }

    @Bean(name = ["moduleBJPAProperties"])
    @ConfigurationProperties(prefix = "module-b.spring.jpa")
    fun moduleBJPAProperties(): JpaProperties = JpaProperties()

    @Bean(name = ["moduleBTransactionManager"])
    fun moduleBTransactionManager(@Qualifier("moduleBEntityManager")
                                  entityManagerFactory: EntityManagerFactory): PlatformTransactionManager =
            JpaTransactionManager(entityManagerFactory)
}