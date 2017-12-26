package www.wuluyang.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

//数据源1
@MapperScan(basePackages="www.wuluyang.metting",sqlSessionFactoryRef="moneySqlSessionFactory")//@MapperScan扫描包范围
public class money {
	@Bean(name="moneyDataSource")
	@ConfigurationProperties(prefix="spring.datasource.money")
	public DataSource testDataSource(){
		return DataSourceBuilder.create().build();
	}
	
	public SqlSessionFactory moneySqlSession(@Qualifier("moneyDataSource") DataSource dataSource) throws Exception{
		SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		return bean.getObject();
	}
	
	@Bean(name="moneyTransactionManager")
	public DataSourceTransactionManager moneyTransactionManager(@Qualifier("moneyDataSource") DataSource dataSource){
		return new DataSourceTransactionManager(dataSource);
	}
	@Bean(name="moneySqlSessionTemplate")
	public SqlSessionTemplate moneySqlSessionTemplate
	(@Qualifier("moneySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception{
		return new SqlSessionTemplate(sqlSessionFactory);
		
	}
}
